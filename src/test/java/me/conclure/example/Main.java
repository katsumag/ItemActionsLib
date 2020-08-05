package me.conclure.example;

import me.katsumag.itemactionslib.*;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private final ItemActionManager itemActionManager = new ItemActionManager();
    private ActionItem item;

    @Override
    public void onEnable() {
        this.itemActionManager.newItem(new ItemKey("IALExample"), Material.APPLE, IdentifierOption.COMPARE_NBT).ifPresent(actionItem -> this.item = actionItem);
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
