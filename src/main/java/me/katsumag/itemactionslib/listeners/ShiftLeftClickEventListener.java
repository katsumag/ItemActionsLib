package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.ShiftLeftClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ShiftLeftClickEventListener extends AbstractListener<ShiftLeftClickEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR && e.getPlayer().isSneaking() || e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().isSneaking()) {
            ShiftLeftClickEvent event = new ShiftLeftClickEvent(e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            actions.forEach((uuid, action) -> action.perform(event));
        }
    }

}
