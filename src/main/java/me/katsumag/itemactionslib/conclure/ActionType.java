package me.katsumag.itemactionslib.conclure;

import me.katsumag.itemactionslib.conclure.event.*;

public class ActionType<T extends ListenableEvent> {

    public static final ActionType<ClickEvent> CLICK = new ActionType<>(ClickEvent.class);
    public static final ActionType<LeftClickEvent> LEFT_CLICK = new ActionType<>(LeftClickEvent.class);
    public static final ActionType<RightClickEvent> RIGHT_CLICK = new ActionType<>(RightClickEvent.class);
    public static final ActionType<LeftClickAirEvent> LEFT_CLICK_AIR = new ActionType<>(LeftClickAirEvent.class);
    public static final ActionType<LeftClickBlockEvent> LEFT_CLICK_BLOCK = new ActionType<>(LeftClickBlockEvent.class);
    public static final ActionType<RightClickAirEvent> RIGHT_CLICK_AIR = new ActionType<>(RightClickAirEvent.class);
    public static final ActionType<RightClickBlockEvent> RIGHT_CLICK_BLOCK = new ActionType<>(RightClickBlockEvent.class);
    public static final ActionType<ShiftLeftClickAirEvent> SHIFT_LEFT_CLICK_AIR = new ActionType<>(ShiftLeftClickAirEvent.class);
    public static final ActionType<ShiftLeftClickBlockEvent> SHIFT_LEFT_CLICK_BLOCK = new ActionType<>(ShiftLeftClickBlockEvent.class);
    /*public static final ActionType<PlayerInteractEvent> SHIFT_LEFT = new ActionType<>(PlayerInteractEvent.class);
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
