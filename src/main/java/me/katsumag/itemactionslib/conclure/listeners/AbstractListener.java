package me.katsumag.itemactionslib.conclure.listeners;

import com.google.common.collect.*;
import me.katsumag.itemactionslib.Item;
import me.katsumag.itemactionslib.conclure.Action;
import me.katsumag.itemactionslib.conclure.event.ListenableEvent;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.UUID;

public abstract class AbstractListener<T extends ListenableEvent> implements Listener {

    protected Multimap<UUID, Action<T>> actions = HashMultimap.create();

    public void addAction(Action<T> action, UUID id) {
        actions.put(id, action);
    }

    public void removeActions(UUID id) {actions.removeAll(id);}

}
