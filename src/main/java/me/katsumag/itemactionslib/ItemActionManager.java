package me.katsumag.itemactionslib;

import com.google.common.collect.Maps;
import me.katsumag.itemactionslib.event.ListenableEvent;
import me.katsumag.itemactionslib.listeners.AbstractListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

public class ItemActionManager {

    private final Map<ActionType<?>, AbstractListener<?>> manager = Maps.newHashMap();
    private final JavaPlugin plugin;

    public ItemActionManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public <T extends ListenableEvent> void register(ActionType<T> type, AbstractListener<T> listener) {
        if (this.manager.get(type) == null) {
            throw new IllegalStateException("Value for ActionType is already registered.");
        }
        this.manager.put(type, listener);
        this.plugin.getServer().getPluginManager().registerEvents(listener, this.plugin);

    }

    public Item newItem(ItemStack itemStack) {
        Utils.notNull(itemStack);
        return new Item(this, itemStack);
    }

    public Item newItem(Material material) {
        Utils.notNull(material);
        return this.newItem(new ItemStack(material));
    }

    @SuppressWarnings("unchecked")
    <T extends ListenableEvent> void addAction(ActionType<T> type, Action<T> action, UUID uuid) {
        Utils.notNull(type);
        ((AbstractListener<T>)this.manager.get(type)).addAction(action, uuid);
    }

    <T extends ListenableEvent> void addAction(ActionType<T> type, Action<T> action, Item item) {
        this.addAction(type, action, item.getUniqueId());
    }

    <T extends ListenableEvent> void removeActions(ActionType<T> type, UUID uuid) {
        Utils.notNull(type);
        this.manager.get(type).clearActions(uuid);
    }

    <T extends ListenableEvent> void removeActions(ActionType<T> type, Item item) {
        this.removeActions(type, item.getUniqueId());
    }

    void clearActions(UUID uuid) {
        this.manager.values().forEach($ -> $.clearActions(uuid));
    }

    void clearActions(Item item) {
        this.clearActions(item.getUniqueId());
    }
}
