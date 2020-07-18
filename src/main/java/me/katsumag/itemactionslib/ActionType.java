package me.katsumag.itemactionslib;

import me.katsumag.itemactionslib.event.*;

public class ActionType<T extends ListenableEvent> {

    public static final ActionType<ClickEvent> CLICK = new ActionType<>(ClickEvent.class);
    public static final ActionType<LeftClickEvent> LEFT_CLICK = new ActionType<>(LeftClickEvent.class);
    public static final ActionType<RightClickEvent> RIGHT_CLICK = new ActionType<>(RightClickEvent.class);
    public static final ActionType<LeftClickAirEvent> LEFT_CLICK_AIR = new ActionType<>(LeftClickAirEvent.class);
    public static final ActionType<LeftClickBlockEvent> LEFT_CLICK_BLOCK = new ActionType<>(LeftClickBlockEvent.class);
    public static final ActionType<RightClickAirEvent> RIGHT_CLICK_AIR = new ActionType<>(RightClickAirEvent.class);
    public static final ActionType<RightClickBlockEvent> RIGHT_CLICK_BLOCK = new ActionType<>(RightClickBlockEvent.class);
    public static final ActionType<ShiftLeftClickEvent> SHIFT_LEFT_CLICK = new ActionType<>(ShiftLeftClickEvent.class);
    public static final ActionType<ShiftLeftClickAirEvent> SHIFT_LEFT_CLICK_AIR = new ActionType<>(ShiftLeftClickAirEvent.class);
    public static final ActionType<ShiftLeftClickBlockEvent> SHIFT_LEFT_CLICK_BLOCK = new ActionType<>(ShiftLeftClickBlockEvent.class);
    public static final ActionType<ShiftRightClickEvent> SHIFT_RIGHT_CLICK = new ActionType<>(ShiftRightClickEvent.class);
    public static final ActionType<ShiftRightClickAirEvent> SHIFT_RIGHT_CLICK_AIR = new ActionType<>(ShiftRightClickAirEvent.class);
    public static final ActionType<ShiftRightClickBlockEvent> SHIFT_RIGHT_CLICK_BLOCK = new ActionType<>(ShiftRightClickBlockEvent.class);

    public static final ActionType<PlayerShiftEvent> PLAYER_SHIFT = new ActionType<>(PlayerShiftEvent.class);
    public static final ActionType<PlayerJumpEvent> PLAYER_JUMP = new ActionType<>(PlayerJumpEvent.class);
    public static final ActionType<PlayerShiftJumpEvent> PLAYER_SHIFT_JUMP = new ActionType<>(PlayerShiftJumpEvent.class);
    public static final ActionType<PlayerSprintEvent> PLAYER_SPRINT = new ActionType<>(PlayerSprintEvent.class);

    /*public static final ActionType<PlayerJumpClickEvent> SHIFT_JUMP_RIGHT = new ActionType<>(PlayerJumpClickEvent.class);
    public static final ActionType<PlayerJumpClickEvent> SHIFT_JUMP_LEFT = new ActionType<>(PlayerJumpClickEvent.class);*/

    private final Class<T> event;

    private ActionType(Class<T> event) {
        this.event = event;
    }

    public Class<T> getEvent() {
        return event;
    }

}
