package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.RegisteredListener;

import java.util.stream.Stream;

public final class ClickEventListener extends AbstractListener<ClickEvent> {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!e.hasItem()) {
            return;
        }
        ClickEvent event = new ClickEvent(e.getPlayer());
        performActions(event, e.getItem());
    }
}
