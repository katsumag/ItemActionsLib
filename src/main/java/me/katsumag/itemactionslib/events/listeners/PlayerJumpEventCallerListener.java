package me.katsumag.itemactionslib.events.listeners;

import me.katsumag.itemactionslib.events.custom.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

public class PlayerJumpEventCallerListener implements Listener {

    @EventHandler
    public void onMove(PlayerStatisticIncrementEvent e) {
        if (e.getStatistic() != Statistic.JUMP) return;

        Bukkit.getServer().getPluginManager().callEvent(new PlayerJumpEvent(e.getPlayer()));

    }

}
