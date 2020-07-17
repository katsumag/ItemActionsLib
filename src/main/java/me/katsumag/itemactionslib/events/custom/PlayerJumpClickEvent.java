package me.katsumag.itemactionslib.events.custom;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerEvent;

import java.util.UUID;

public class PlayerJumpClickEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final UUID player;
    private final Action action;

    public PlayerJumpClickEvent(final Player p, final Action action) {
        super(p);
        this.player = p.getUniqueId();
        this.action = action;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public String getEventName() {
        return "PlayerJumpClickEvent";
    }

    public UUID getPlayerUUID() {
        return this.player;
    }

    public Action getAction() { return action; }

}
