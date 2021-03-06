package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.RightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class RightClickEventListener extends AbstractListener<RightClickEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!e.hasItem()) {
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            RightClickEvent event = new RightClickEvent(e.getPlayer());
            performActions(event, e.getItem());
        }
    }

}
