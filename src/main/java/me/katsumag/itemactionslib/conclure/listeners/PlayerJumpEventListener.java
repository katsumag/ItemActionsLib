package me.katsumag.itemactionslib.conclure.listeners;

import me.katsumag.itemactionslib.conclure.event.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

public class PlayerJumpEventListener extends AbstractListener<PlayerJumpEvent> {

    @EventHandler
    public void onStatisticIncrement(PlayerStatisticIncrementEvent e) {
        if (!(e.getStatistic() == Statistic.JUMP)) return;

        PlayerJumpEvent event = new PlayerJumpEvent(e.getPlayer());
        Bukkit.getPluginManager().callEvent(event);
        actions.forEach((uuid, action) -> action.perform(event));

    }

}
