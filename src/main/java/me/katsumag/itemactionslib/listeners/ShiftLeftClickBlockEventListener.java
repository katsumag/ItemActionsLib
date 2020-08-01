package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.ShiftLeftClickBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ShiftLeftClickBlockEventListener extends AbstractListener<ShiftLeftClickBlockEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.hasBlock() && e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().isSneaking()) {
            ShiftLeftClickBlockEvent event = new ShiftLeftClickBlockEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            getActions().forEach((uuid, action) -> action.perform(event));
        }
    }

}
