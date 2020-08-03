package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.ShiftLeftClickAirEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ShiftLeftClickAirEventListener extends AbstractListener<ShiftLeftClickAirEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!e.hasItem()) {
            return;
        }
        if (e.getAction() == Action.LEFT_CLICK_AIR && e.getPlayer().isSneaking()) {
            ShiftLeftClickAirEvent event = new ShiftLeftClickAirEvent(e.getPlayer());
            performActions(event, e.getItem());
        }
    }

}
