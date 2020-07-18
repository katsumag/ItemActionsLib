package me.katsumag.itemactionslib;

import me.katsumag.itemactionslib.event.ListenableEvent;

@FunctionalInterface
public interface Action<T extends ListenableEvent> {

    void perform(T t);
}
