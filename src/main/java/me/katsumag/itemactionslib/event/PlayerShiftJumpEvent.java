package me.katsumag.itemactionslib.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

// Does not relate involve an ItemStack
@Deprecated
public class PlayerShiftJumpEvent extends PlayerEvent implements ListenableEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public PlayerShiftJumpEvent(Player who) {
        super(who);
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}
