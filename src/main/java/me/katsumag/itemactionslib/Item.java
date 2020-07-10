package me.katsumag.itemactionslib;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class Item {

    private final ItemStack item;


    public Item(ItemStack item) {
        this.item = item;
    }

    public <T extends PlayerEvent> void addAction(final ActionType<T> type, final ItemAction<T> action) {
        new ItemActionsManager().registerItemAction(action);
    }

    public ItemStack getItemStack() {
        return item;
    }

}
