package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

public final class PlayerJumpEventListener extends AbstractListener<PlayerJumpEvent> {

    @EventHandler
    public void onStatisticIncrement(PlayerStatisticIncrementEvent e) {
        if (!(e.getStatistic() == Statistic.JUMP)) return;

        PlayerJumpEvent event = new PlayerJumpEvent(e.getPlayer());
        Bukkit.getPluginManager().callEvent(event);
        actions.forEach((uuid, action) -> action.perform(event));

    }

}
