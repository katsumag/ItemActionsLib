package me.katsumag.itemactionslib.listeners;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import me.katsumag.itemactionslib.Action;
import me.katsumag.itemactionslib.ActionItem;
import me.katsumag.itemactionslib.Utils;
import me.katsumag.itemactionslib.event.ListenableEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractListener<T extends ListenableEvent> implements Listener {

    private final Multimap<ActionItem, Action<T>> actions = HashMultimap.create();

    public void addAction(Action<T> action, ActionItem item) {
        Utils.notNull(Action.class, action);
        Utils.notNull(ActionItem.class, item);
        this.actions.put(item, action);
    }

    public void clearActions(ActionItem item) {
        Utils.notNull(ActionItem.class, item);
        this.actions.removeAll(item);
    }

    public ImmutableMultimap<ActionItem, Action<T>> getActions() {
        return ImmutableMultimap.copyOf(actions);
    }

    public void performActions(T event, ItemStack itemStack) {
        Utils.notNull(ItemStack.class, itemStack);
        if (event instanceof Event) {
            Bukkit.getPluginManager().callEvent((Event)event);
            actions.forEach((actionItem, action) -> {
                if (actionItem.isSimilar(itemStack)) {
                    action.perform(event);
                }
            });
        }
    }
}
