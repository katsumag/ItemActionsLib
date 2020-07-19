package me.katsumag.itemactionslib;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import me.katsumag.itemactionslib.event.ListenableEvent;
import me.katsumag.itemactionslib.listeners.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
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
        return new ActionItem() {

            private final UUID uuid = UUID.randomUUID();

            public <T extends ListenableEvent> ActionItem addAction(ActionType<T> type, Action<T> action) {
                manager.addAction(type, action, this.uuid);
                return this;
            }

            public <T extends ListenableEvent> void removeActions(ActionType<T> type) {
                manager.removeActions(type, this.uuid);
            }

            public void clearActions() {
                manager.clearActions(this.uuid);
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
    private <T extends ListenableEvent> void addAction(ActionType<T> type, Action<T> action, UUID uuid) {
        Utils.notNull(type);
        ((AbstractListener<T>)this.actionsMap.get(type)).addAction(action, uuid);
    }

    private <T extends ListenableEvent> void removeActions(ActionType<T> type, UUID uuid) {
        Utils.notNull(type);
        this.actionsMap.get(type).clearActions(uuid);
    }

    private void clearActions(UUID uuid) {
        this.actionsMap.values().forEach($ -> $.clearActions(uuid));
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
