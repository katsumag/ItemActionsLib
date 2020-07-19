package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.LeftClickBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class LeftClickBlockListener extends AbstractListener<LeftClickBlockEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.hasBlock() && e.getAction() == Action.LEFT_CLICK_BLOCK) {
            LeftClickBlockEvent event = new LeftClickBlockEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            actions.forEach((uuid, action) -> action.perform(event));
        }
    }
}
