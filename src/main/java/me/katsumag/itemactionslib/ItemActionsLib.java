package me.katsumag.itemactionslib;

import me.katsumag.itemactionslib.events.listeners.PlayerJumpClickEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemActionsLib extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new PlayerJumpClickEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJumpClickEventListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
