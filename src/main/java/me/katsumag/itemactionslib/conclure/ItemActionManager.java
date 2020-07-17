package me.katsumag.itemactionslib.conclure;

import com.google.common.collect.Maps;
import me.katsumag.itemactionslib.conclure.event.ListenableEvent;
import me.katsumag.itemactionslib.conclure.listeners.AbstractListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class ItemActionManager {

    private final Map<ActionType<?>, AbstractListener<?>> manager = Maps.newHashMap();
    private final JavaPlugin plugin;

    public ItemActionManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public <T extends ListenableEvent> void register(ActionType<T> type, AbstractListener<T> listener) {
        if (manager.get(type) == null) {
            throw new IllegalStateException("Value for ActionType is already registered.");
        }
        manager.put(type, listener);
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);

    }

    //This can we little wack as we don't know if the type is the same from the map.
    @SuppressWarnings("unchecked")
    <T extends ListenableEvent> void addAction(ActionType<T> type, Action<T> action) {
        ((AbstractListener<T>)manager.get(type)).addAction(action);
    }

    public Item newItem(ItemStack itemStack) {
        return new Item(this, itemStack);
    }

    public Item newItem(Material material) {
        Utils.notNull(material);
        return this.newItem(new ItemStack(material));
    }
}
