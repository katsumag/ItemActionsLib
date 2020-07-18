package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.PlayerShiftEvent;
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
