package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ClickEventListener extends AbstractListener<ClickEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        ClickEvent event = new ClickEvent(e.getPlayer());
        Bukkit.getPluginManager().callEvent(event);
        getActions().forEach((uuid, action) -> action.perform(event));
    }

}
