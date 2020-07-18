package me.katsumag.itemactionslib.conclure.listeners;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import me.katsumag.itemactionslib.conclure.Action;
import me.katsumag.itemactionslib.conclure.ActionType;
import me.katsumag.itemactionslib.conclure.Item;
import me.katsumag.itemactionslib.conclure.event.ListenableEvent;
import org.bukkit.event.Listener;

import java.util.UUID;

public abstract class AbstractListener<T extends ListenableEvent> implements Listener {

    protected Multimap<UUID, Action<T>> actions = HashMultimap.create();

    public void addAction(Action<T> action, Item item) {
        actions.put(item.getId(), action);
    }

    public void addAction(Action<T> action, UUID id) {
        actions.put(id, action);
    }

    public void clearActions(Item item) { actions.removeAll( item.getId() ); }

    public void clearActions(UUID id) { actions.removeAll( id ); }

    public void removeActions(ActionType<T> type, Item item) {
        actions.entries().removeIf(uuidActionEntry -> uuidActionEntry.getKey() == item.getId() && uuidActionEntry.getValue().getClass().isInstance(type));
    }

    public void removeActions(ActionType<T> type, UUID id) {
        actions.entries().removeIf(uuidActionEntry -> uuidActionEntry.getKey() == id && uuidActionEntry.getValue().getClass().isInstance(type));
    }

}
