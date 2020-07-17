package me.katsumag.itemactionslib.conclure.listeners;

import com.google.common.collect.Lists;
import me.katsumag.itemactionslib.conclure.Action;
import me.katsumag.itemactionslib.conclure.event.ListenableEvent;
import org.bukkit.event.Listener;

import java.util.List;

public abstract class AbstractListener<T extends ListenableEvent> implements Listener {

    protected List<Action<T>> actions;

    public AbstractListener() {
        actions = Lists.newLinkedList();
    }

    public void addAction(Action<T> action) {
        actions.add(action);
    }

}
