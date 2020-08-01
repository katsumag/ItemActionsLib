package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.RightClickAirEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class RightClickAirEventListener extends AbstractListener<RightClickAirEvent>{

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            RightClickAirEvent event = new RightClickAirEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            getActions().forEach((uuid, action) -> action.perform(event));
        }
    }

}
