package me.katsumag.itemactionslib.conclure;

import me.katsumag.itemactionslib.conclure.listeners.*;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class ExampleMain extends JavaPlugin {

    private final ItemActionManager manager = new ItemActionManager(this);

    public ExampleMain() {

        /*So every ActionType<T extends ListenableEvent> should have it's own listener.
        Contact DM if you want more explanation //Conclure#0001
        */

        manager.register(ActionType.CLICK, new ClickEventListener());
        manager.register(ActionType.LEFT_CLICK, new LeftClickEventListener());
        manager.register(ActionType.RIGHT_CLICK, new RightClickEventListener());
        manager.register(ActionType.LEFT_CLICK_AIR, new LeftClickAirEventListener());
        manager.register(ActionType.LEFT_CLICK_BLOCK, new LeftClickBlockListener());
        manager.register(ActionType.RIGHT_CLICK_AIR, new RightClickAirEventListener());
        manager.register(ActionType.RIGHT_CLICK_BLOCK, new RightClickBlockEventListener());
        manager.register(ActionType.SHIFT_LEFT_CLICK, new ShiftLeftClickEventListener());
        manager.register(ActionType.SHIFT_LEFT_CLICK_AIR, new ShiftLeftClickAirEventListener());
        manager.register(ActionType.SHIFT_LEFT_CLICK_BLOCK, new ShiftLeftClickBlockEventListener());
        manager.register(ActionType.SHIFT_RIGHT_CLICK, new ShiftRightClickEventListener());
        manager.register(ActionType.SHIFT_RIGHT_CLICK_AIR, new ShiftRightClickAirEventListener());
        manager.register(ActionType.SHIFT_RIGHT_CLICK_BLOCK, new ShiftRightClickBlockEventListener());

        Item item = manager.newItem(Material.ITEM_FRAME).addAction(ActionType.LEFT_CLICK_BLOCK, event -> {
            System.out.println(event.getEventName());
            System.out.println(event.getPlayer().getName());
        });
    }
}
