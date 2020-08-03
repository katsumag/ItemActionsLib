package me.katsumag.itemactionslib.listeners;

import me.katsumag.itemactionslib.event.PlayerJumpEvent;
import me.katsumag.itemactionslib.event.PlayerShiftJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
// Does not relate involve an ItemStack
@Deprecated
public final class PlayerShiftJumpEventListener extends AbstractListener<PlayerShiftJumpEvent> {

    @EventHandler
    public void onJump(PlayerJumpEvent e) {
        if (! e.getPlayer().isSneaking()) return;

        PlayerShiftJumpEvent event = new PlayerShiftJumpEvent(e.getPlayer());
        Bukkit.getPluginManager().callEvent(event);
        getActions().forEach((uuid, action) -> action.perform(event));

    }

}
