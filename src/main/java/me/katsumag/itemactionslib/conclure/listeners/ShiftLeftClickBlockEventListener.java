package me.katsumag.itemactionslib.conclure.listeners;

import me.katsumag.itemactionslib.conclure.event.ShiftLeftClickBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ShiftLeftClickBlockEventListener extends AbstractListener<ShiftLeftClickBlockEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.hasBlock() && e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().isSneaking()) {
            ShiftLeftClickBlockEvent event = new ShiftLeftClickBlockEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            actions.forEach((uuid, action) -> action.perform(event));
        }
    }

}
