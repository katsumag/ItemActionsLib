package me.katsumag.itemactionslib.events.listeners.callers;

import me.katsumag.itemactionslib.Utils;
import me.katsumag.itemactionslib.events.custom.PlayerJumpClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerJumpClickEventCallerListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        if (e.getAction() == Action.PHYSICAL || ! Utils.isJumping(e.getPlayer())) return;

        Bukkit.getServer().getPluginManager().callEvent(new PlayerJumpClickEvent(e.getPlayer(), e.getAction()));

    }

}
