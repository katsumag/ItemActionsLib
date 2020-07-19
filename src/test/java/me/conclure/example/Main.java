package me.conclure.example;

import me.katsumag.itemactionslib.ActionItem;
import me.katsumag.itemactionslib.ActionType;
import me.katsumag.itemactionslib.ItemActionManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private final ItemActionManager itemActionManager;
    private final ActionItem item;

    public Main() {
        this.itemActionManager = ItemActionManager.create(this);
        this.item = this.itemActionManager.newItem(Material.APPLE);
    }

    @Override
    public void onEnable() {
        this.item.addAction(ActionType.LEFT_CLICK, event -> event.getPlayer().setGameMode(GameMode.CREATIVE));
    }

    @Override
    public void onDisable() {
        this.item.removeActions(ActionType.LEFT_CLICK);
    }

    public ItemActionManager getItemActionManager() {
        return itemActionManager;
    }
}
