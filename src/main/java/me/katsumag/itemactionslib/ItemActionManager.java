package me.katsumag.itemactionslib;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import me.katsumag.itemactionslib.event.ListenableEvent;
import me.katsumag.itemactionslib.listeners.*;
import me.katsumag.itemactionslib.nbt.ItemNBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public final class ItemActionManager {

    private final Map<ActionType<?>, AbstractListener<?>> actionsMap;
    private final JavaPlugin plugin;

    private static boolean isAlreadyInvoked = false;
    private final ItemActionManager manager;

    private ItemActionManager(JavaPlugin plugin) {
        Utils.notNull(plugin);
        if (isAlreadyInvoked) throw new IllegalStateException("ItemActionManager has already been invoked.");
        else isAlreadyInvoked = true;

        this.manager = this;
        this.plugin = plugin;

        final Map<ActionType<?>, AbstractListener<?>> map = Maps.newHashMap();
        Stream.of(ActionType.values()).forEach($ -> {
            map.put($, $.getProvider());
            this.plugin.getServer().getPluginManager().registerEvents($.getProvider(), this.plugin);
        });
        this.actionsMap = ImmutableMap.copyOf(map);
    }

    /**
     * Creates a new instance of an ActionItem.
     *
     * @param itemStack - ItemStack this ActionItem should be based of.
     * @return New instance of an ActionItem.
     */
    public ActionItem newItem(ItemStack itemStack) {
        Utils.notNull(itemStack);
        final UUID id = UUID.randomUUID();
        ItemNBT.setNBTTag(itemStack, "ItemActionsLib", id.toString());
        return new ActionItem() {

            private final UUID uuid = id;

            public <T extends ListenableEvent> ActionItem addAction(ActionType<T> type, Action<T> action) {
                manager.addAction(type, action, this);
                return this;
            }

            public <T extends ListenableEvent> void removeActions(ActionType<T> type) {
                manager.removeActions(type, this);
            }

            public void clearActions() {
                manager.clearActions(this);
            }

            @Override
            public ItemStack get() {
                return itemStack;
            }

            public UUID getUniqueId() {
                return this.uuid;
            }
        };
    }

    /**
     * Creates a new instance of an ActionItem.
     *
     * @param material - Material this ActionItem should be based of.
     * @return New instance of an ActionItem.
     */
    public ActionItem newItem(Material material) {
        Utils.notNull(material);
        return this.newItem(new ItemStack(material));
    }

    @SuppressWarnings("unchecked")
    private <T extends ListenableEvent> void addAction(ActionType<T> type, Action<T> action, ActionItem item) {
        Utils.notNull(type);
        ((AbstractListener<T>)this.actionsMap.get(type)).addAction(action, item);
    }

    private <T extends ListenableEvent> void removeActions(ActionType<T> type, ActionItem item) {
        Utils.notNull(type);
        this.actionsMap.get(type).clearActions(item);
    }

    private void clearActions(ActionItem item) {
        this.actionsMap.values().forEach($ -> $.clearActions(item));
    }

    /**
     *
     * @param id
     * @return Optional<ActionItem>, contains the ItemAction if it can find one, or empty if not
     */

    public Optional<ActionItem> getByUUID(UUID id) {

        for (AbstractListener<?> abstractListener : actionsMap.values()) {
            for (ActionItem actionItem : abstractListener.getActions().keys()) {
                if (actionItem.getUniqueId() == id) return Optional.of(actionItem);
            }
        }
        return Optional.empty();
    }

    /**
     *
     * @param item
     * @return Optional<ActionItem>, contains the ItemAction if it can find one, or empty if not
     */

    public Optional<ActionItem> getByItemStack(ItemStack item) {

        for (AbstractListener<?> abstractListener : actionsMap.values()) {
            for (ActionItem actionItem : abstractListener.getActions().keys()) {
                if (actionItem.getUniqueId().equals(UUID.fromString(ItemNBT.getNBTTag(item, "ItemActionsLib")))) return Optional.of(actionItem);
            }
        }
        return Optional.empty();
    }

    /**
     * Creates a new instance of ItemActionManager.
     *
     * @param plugin - The owning plugin that created the ItemActionManager.
     * @return New instance of ItemActionManager.
     */
    public static ItemActionManager create(JavaPlugin plugin) {
        return new ItemActionManager(plugin);
    }
}
