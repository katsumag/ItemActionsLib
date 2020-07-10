package me.katsumag.itemactionslib;

import me.katsumag.itemactionslib.events.custom.PlayerJumpClickEvent;
import me.katsumag.itemactionslib.events.listeners.PlayerJumpClickEventListener;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemActionsLib extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new PlayerJumpClickEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJumpClickEventListener(), this);
        new Item(new ItemStack(Material.ITEM_FRAME)).addAction(ActionType.SHIFT_JUMP_LEFT, event -> {


        });


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
