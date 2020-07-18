package me.katsumag.itemactionslib.conclure.listeners;

import me.katsumag.itemactionslib.conclure.event.LeftClickAirEvent;
import me.katsumag.itemactionslib.conclure.event.RightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickEventListener extends AbstractListener<RightClickEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            RightClickEvent event = new RightClickEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            actions.forEach((uuid, action) -> action.perform(event));
        }
    }

}
