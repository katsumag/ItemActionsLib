package me.katsumag.itemactionslib.conclure.listeners;

import me.katsumag.itemactionslib.conclure.event.LeftClickBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeftClickBlockListener extends AbstractListener<LeftClickBlockEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.hasBlock() && e.getAction() == Action.LEFT_CLICK_BLOCK) {
            LeftClickBlockEvent event = new LeftClickBlockEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            actions.forEach(action -> action.perform(event));
        }
    }
}
