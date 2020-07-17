package me.katsumag.itemactionslib.events.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import java.util.UUID;

public class PlayerJumpEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final UUID player;

    public PlayerJumpEvent(final Player p) {
        super(p);
        this.player = p.getUniqueId();
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public UUID getPlayerUUID() {
        return this.player;
    }
}
