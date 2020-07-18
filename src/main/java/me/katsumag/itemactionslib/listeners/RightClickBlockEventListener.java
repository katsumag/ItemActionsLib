package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.RightClickBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickBlockEventListener extends AbstractListener<RightClickBlockEvent>{

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.hasBlock() && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            RightClickBlockEvent event = new RightClickBlockEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            actions.forEach((uuid, action) -> action.perform(event));
        }
    }

}
