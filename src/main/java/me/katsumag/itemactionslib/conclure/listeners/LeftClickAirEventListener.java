package me.katsumag.itemactionslib.conclure.listeners;

import me.katsumag.itemactionslib.conclure.event.LeftClickAirEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeftClickAirEventListener extends AbstractListener<LeftClickAirEvent>{

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR) {
            LeftClickAirEvent event = new LeftClickAirEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            actions.forEach(action -> action.perform(event));
        }
    }

}
