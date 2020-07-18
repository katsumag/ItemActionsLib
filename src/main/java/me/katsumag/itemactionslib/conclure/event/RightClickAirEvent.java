package me.katsumag.itemactionslib.conclure.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class RightClickAirEvent extends PlayerEvent implements ListenableEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public RightClickAirEvent(Player who) {
        super(who);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}