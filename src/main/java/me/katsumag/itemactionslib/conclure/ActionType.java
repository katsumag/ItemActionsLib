package me.katsumag.itemactionslib.conclure;

import me.katsumag.itemactionslib.conclure.event.LeftClickBlockEvent;
import me.katsumag.itemactionslib.conclure.event.ListenableEvent;
import me.katsumag.itemactionslib.events.custom.PlayerJumpClickEvent;
import me.katsumag.itemactionslib.events.custom.PlayerJumpEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class ActionType<T extends ListenableEvent> {

    public static final ActionType<LeftClickBlockEvent> LEFT_CLICK_BLOCK = new ActionType<>(LeftClickBlockEvent.class);
    /*public static final ActionType<PlayerInteractEvent> RIGHT_CLICK = new ActionType<>(PlayerInteractEvent.class);
    public static final ActionType<PlayerInteractEvent> SHIFT_LEFT = new ActionType<>(PlayerInteractEvent.class);
    public static final ActionType<PlayerInteractEvent> SHIFT_RIGHT = new ActionType<>(PlayerInteractEvent.class);

    public static final ActionType<PlayerJumpEvent> JUMP = new ActionType<>(PlayerJumpEvent.class);
    public static final ActionType<PlayerJumpEvent> SHIFT_JUMP = new ActionType<>(PlayerJumpEvent.class);

    public static final ActionType<PlayerJumpClickEvent> SHIFT_JUMP_RIGHT = new ActionType<>(PlayerJumpClickEvent.class);
    public static final ActionType<PlayerJumpClickEvent> SHIFT_JUMP_LEFT = new ActionType<>(PlayerJumpClickEvent.class);

    public static final ActionType<PlayerToggleSprintEvent> SPRINT = new ActionType<>(PlayerToggleSprintEvent.class);
    public static final ActionType<PlayerToggleSneakEvent> SNEAK = new ActionType<>(PlayerToggleSneakEvent.class);*/

    private final Class<T> event;

    private ActionType(Class<T> event) {
        this.event = event;
    }

    public Class<T> getEvent() {
        return event;
    }

}
