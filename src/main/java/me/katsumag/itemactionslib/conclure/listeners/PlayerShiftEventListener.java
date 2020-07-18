package me.katsumag.itemactionslib.conclure.listeners;

import me.katsumag.itemactionslib.conclure.event.PlayerShiftEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

public class PlayerShiftEventListener extends AbstractListener<PlayerShiftEvent> {

    @EventHandler
    public void onShift(PlayerShiftEvent e) {

        PlayerShiftEvent event = new PlayerShiftEvent(e.getPlayer());
        Bukkit.getPluginManager().callEvent(event);
        actions.forEach((uuid, action) -> action.perform(event));

    }

}
