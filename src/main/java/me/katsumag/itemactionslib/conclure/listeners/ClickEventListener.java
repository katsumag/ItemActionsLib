package me.katsumag.itemactionslib.conclure.listeners;

import me.katsumag.itemactionslib.conclure.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickEventListener extends AbstractListener<ClickEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        ClickEvent event = new ClickEvent(e.getPlayer());
        Bukkit.getPluginManager().callEvent(event);
        actions.forEach((uuid, action) -> action.perform(event));
    }

}