package me.katsumag.itemactionslib;

import org.bukkit.event.player.PlayerEvent;

@FunctionalInterface
public interface ItemAction<T extends PlayerEvent> {

    void execute(final T event);

}
