package me.katsumag.itemactionslib.events.listeners;

import me.katsumag.itemactionslib.ItemAction;
import me.katsumag.itemactionslib.ItemActionsManager;
import me.katsumag.itemactionslib.events.custom.PlayerJumpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class PlayerJumpEventListener implements Listener {

    private final ItemActionsManager manager = new ItemActionsManager();

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent e) {

        List<ItemAction> actionList = manager.

    }

}
