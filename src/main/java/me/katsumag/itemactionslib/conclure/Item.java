package me.katsumag.itemactionslib.conclure;

import me.katsumag.itemactionslib.conclure.event.ListenableEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class Item implements Supplier<ItemStack> {

    private final ItemStack itemStack;
    private final ItemActionManager manager;

    Item(ItemActionManager manager, ItemStack itemStack) {
        Utils.notNull(manager);
        this.manager = manager;
        Utils.notNull(itemStack);
        this.itemStack = itemStack;
    }

    public <T extends ListenableEvent> Item addAction(ActionType<T> type, Action<T> action) {
        manager.addAction(type, action);
        return this;
    }

    @Override
    public ItemStack get() {
        return itemStack;
    }
}
