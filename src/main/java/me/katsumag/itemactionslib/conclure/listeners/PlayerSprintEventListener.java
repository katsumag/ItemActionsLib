package me.katsumag.itemactionslib.conclure.listeners;

import me.katsumag.itemactionslib.conclure.event.PlayerShiftJumpEvent;
import me.katsumag.itemactionslib.conclure.event.PlayerSprintEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class PlayerSprintEventListener extends AbstractListener<PlayerSprintEvent> {

    @EventHandler
    public void onSprint(PlayerToggleSprintEvent e) {
        if (! e.isSprinting()) return;

        PlayerSprintEvent event = new PlayerSprintEvent(e.getPlayer());
        Bukkit.getPluginManager().callEvent(event);
        actions.forEach((uuid, action) -> action.perform(event));

    }

}
