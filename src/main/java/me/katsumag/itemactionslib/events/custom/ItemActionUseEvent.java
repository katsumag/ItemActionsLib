package me.katsumag.itemactionslib.events.custom;

import me.katsumag.itemactionslib.ActionType;
import me.katsumag.itemactionslib.Item;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;


public class ItemActionUseEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final ActionType actionType;
    private final Item item;
    private boolean cancelled = false;
    private final UUID player;

    public ItemActionUseEvent(final ActionType type, final Item item, final UUID player) {
        this.actionType = type;
        this.item = item;
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public Item getItem() {
        return this.item;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.player);
    }

    public UUID getPlayerUUID() {
        return this.player;
    }

}
