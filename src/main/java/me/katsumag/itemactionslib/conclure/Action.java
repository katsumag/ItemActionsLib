package me.katsumag.itemactionslib.conclure;

import me.katsumag.itemactionslib.conclure.event.ListenableEvent;

@FunctionalInterface
public interface Action<T extends ListenableEvent> {

    void perform(T t);
}
