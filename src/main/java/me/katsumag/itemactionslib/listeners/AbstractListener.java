package me.katsumag.itemactionslib.listeners;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import me.katsumag.itemactionslib.Action;
import me.katsumag.itemactionslib.ActionItem;
import me.katsumag.itemactionslib.Utils;
import me.katsumag.itemactionslib.event.ListenableEvent;
import org.bukkit.event.Listener;

public abstract class AbstractListener<T extends ListenableEvent> implements Listener {

    private final Multimap<ActionItem, Action<T>> _actions = HashMultimap.create();

    public void addAction(Action<T> action, ActionItem item) {
        Utils.notNull(action, item.getUniqueId());
        this._actions.put(item, action);
    }

    public void clearActions(ActionItem item) {
        Utils.notNull(item);
        this._actions.removeAll(item);
    }

    public ImmutableMultimap<ActionItem, Action<T>> getActions() {
        return ImmutableMultimap.copyOf(_actions);
    }
}
