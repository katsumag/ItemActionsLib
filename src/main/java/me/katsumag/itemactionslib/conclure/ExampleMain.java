package me.katsumag.itemactionslib.conclure;

import me.katsumag.itemactionslib.conclure.listeners.LeftClickBlockListener;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class ExampleMain extends JavaPlugin {

    private final ItemActionManager manager = new ItemActionManager(this);

    public ExampleMain() {

        /*So every ActionType<T extends ListenableEvent> should have it's own listener.
        Contact DM if you want more explanation //Conclure#0001
        */
        manager.register(ActionType.LEFT_CLICK_BLOCK, new LeftClickBlockListener());

        Item item = manager.newItem(Material.ITEM_FRAME).addAction(ActionType.LEFT_CLICK_BLOCK, event -> {
            System.out.println(event.getEventName());
            System.out.println(event.getPlayer().getName());
        });
    }
}
