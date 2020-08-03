package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.ShiftRightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ShiftRightClickEventListener extends AbstractListener<ShiftRightClickEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!e.hasItem()) {
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getPlayer().isSneaking() || e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().isSneaking()) {
            ShiftRightClickEvent event = new ShiftRightClickEvent(e.getPlayer());
            performActions(event, e.getItem());
        }
    }

}
