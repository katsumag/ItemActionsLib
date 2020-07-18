package me.katsumag.itemactionslib.conclure.listeners;

import me.katsumag.itemactionslib.conclure.event.LeftClickAirEvent;
import me.katsumag.itemactionslib.conclure.event.LeftClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeftClickEventListener extends AbstractListener<LeftClickEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            LeftClickEvent event = new LeftClickEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            actions.forEach((uuid, action) -> action.perform(event));
        }
    }

}
