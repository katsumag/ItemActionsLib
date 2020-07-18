package me.katsumag.itemactionslib;

import me.katsumag.itemactionslib.event.ListenableEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.function.Supplier;

public class Item implements Supplier<ItemStack> {

    private final ItemStack itemStack;
    private final ItemActionManager manager;
    private final UUID id = UUID.randomUUID();

    Item(ItemActionManager manager, ItemStack itemStack) {
        Utils.notNull(manager);
        this.manager = manager;
        Utils.notNull(itemStack);
        this.itemStack = itemStack;
    }

    public <T extends ListenableEvent> Item addAction(ActionType<T> type, Action<T> action) {
        manager.addAction(type, action, id);
        return this;
    }

    public <T extends ListenableEvent> void removeActions(ActionType<T> type) {
        manager.removeActions(type, this);
    }

    public void clearActions(Item item) {
        manager.clearActions(item);
    }

    public void clearActions(UUID id) {
        manager.clearActions(id);
    }

    @Override
    public ItemStack get() {
        return itemStack;
    }

    public UUID getId() {
        return id;
    }
}
