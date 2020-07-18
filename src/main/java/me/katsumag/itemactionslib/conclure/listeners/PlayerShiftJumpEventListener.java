package me.katsumag.itemactionslib.conclure.listeners;

import me.katsumag.itemactionslib.conclure.event.PlayerJumpEvent;
import me.katsumag.itemactionslib.conclure.event.PlayerShiftJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

public class PlayerShiftJumpEventListener extends AbstractListener<PlayerShiftJumpEvent> {

    @EventHandler
    public void onJump(PlayerJumpEvent e) {
        if (! e.getPlayer().isSneaking()) return;

        PlayerShiftJumpEvent event = new PlayerShiftJumpEvent(e.getPlayer());
        Bukkit.getPluginManager().callEvent(event);
        actions.forEach((uuid, action) -> action.perform(event));

    }

}
