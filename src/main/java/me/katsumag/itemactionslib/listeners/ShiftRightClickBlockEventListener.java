package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.ShiftRightClickBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ShiftRightClickBlockEventListener extends AbstractListener<ShiftRightClickBlockEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().isSneaking()) {
            ShiftRightClickBlockEvent event = new ShiftRightClickBlockEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            getActions().forEach((uuid, action) -> action.perform(event));
        }
    }

}
