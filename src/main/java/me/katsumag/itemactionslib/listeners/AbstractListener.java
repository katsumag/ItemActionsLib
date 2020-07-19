package me.katsumag.itemactionslib.listeners;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import me.katsumag.itemactionslib.Action;
import me.katsumag.itemactionslib.Utils;
import me.katsumag.itemactionslib.event.ListenableEvent;
import org.bukkit.event.Listener;

import java.util.UUID;

public abstract class AbstractListener<T extends ListenableEvent> implements Listener {

    protected Multimap<UUID, Action<T>> actions = HashMultimap.create();

    public void addAction(Action<T> action, UUID uuid) {
        Utils.notNull(action, uuid);
        this.actions.put(uuid, action);
    }

    public void clearActions(UUID uuid) {
        Utils.notNull(uuid);
        this.actions.removeAll(uuid);
    }
}
