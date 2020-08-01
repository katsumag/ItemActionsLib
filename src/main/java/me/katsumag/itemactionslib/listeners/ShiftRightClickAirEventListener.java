package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.ShiftRightClickAirEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ShiftRightClickAirEventListener extends AbstractListener<ShiftRightClickAirEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getPlayer().isSneaking()) {
            ShiftRightClickAirEvent event = new ShiftRightClickAirEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            getActions().forEach((uuid, action) -> action.perform(event));
        }
    }

}
