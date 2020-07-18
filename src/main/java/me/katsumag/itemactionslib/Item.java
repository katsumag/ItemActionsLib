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
        Utils.notNull(manager, itemStack);
        this.manager = manager;
        this.itemStack = itemStack;
    }

    public <T extends ListenableEvent> Item addAction(ActionType<T> type, Action<T> action) {
        this.manager.addAction(type, action, id);
        return this;
    }

    public <T extends ListenableEvent> void removeActions(ActionType<T> type) {
        this.manager.removeActions(type, this);
    }

    public <T extends ListenableEvent> void clearActions() {
        this.manager.clearActions(this);
    }

    @Override
    public ItemStack get() {
        return this.itemStack;
    }

    public UUID getUniqueId() {
        return this.id;
    }
}
