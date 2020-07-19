package me.katsumag.itemactionslib;

import me.katsumag.itemactionslib.event.*;
import me.katsumag.itemactionslib.listeners.*;

public final class ActionType<T extends ListenableEvent> {

    public static final ActionType<ClickEvent> CLICK =
            new ActionType<>(ClickEvent.class, new ClickEventListener());

    public static final ActionType<LeftClickEvent> LEFT_CLICK =
            new ActionType<>(LeftClickEvent.class, new LeftClickEventListener());

    public static final ActionType<RightClickEvent> RIGHT_CLICK =
            new ActionType<>(RightClickEvent.class, new RightClickEventListener());

    public static final ActionType<LeftClickAirEvent> LEFT_CLICK_AIR =
            new ActionType<>(LeftClickAirEvent.class, new LeftClickAirEventListener());

    public static final ActionType<LeftClickBlockEvent> LEFT_CLICK_BLOCK =
            new ActionType<>(LeftClickBlockEvent.class, new LeftClickBlockListener());

    public static final ActionType<RightClickAirEvent> RIGHT_CLICK_AIR =
            new ActionType<>(RightClickAirEvent.class, new RightClickAirEventListener());

    public static final ActionType<RightClickBlockEvent> RIGHT_CLICK_BLOCK =
            new ActionType<>(RightClickBlockEvent.class, new RightClickBlockEventListener());

    public static final ActionType<ShiftLeftClickEvent> SHIFT_LEFT_CLICK =
            new ActionType<>(ShiftLeftClickEvent.class, new ShiftLeftClickEventListener());

    public static final ActionType<ShiftLeftClickAirEvent> SHIFT_LEFT_CLICK_AIR =
            new ActionType<>(ShiftLeftClickAirEvent.class, new ShiftLeftClickAirEventListener());

    public static final ActionType<ShiftLeftClickBlockEvent> SHIFT_LEFT_CLICK_BLOCK =
            new ActionType<>(ShiftLeftClickBlockEvent.class, new ShiftLeftClickBlockEventListener());

    public static final ActionType<ShiftRightClickEvent> SHIFT_RIGHT_CLICK =
            new ActionType<>(ShiftRightClickEvent.class, new ShiftRightClickEventListener());

    public static final ActionType<ShiftRightClickAirEvent> SHIFT_RIGHT_CLICK_AIR =
            new ActionType<>(ShiftRightClickAirEvent.class, new ShiftRightClickAirEventListener());

    public static final ActionType<ShiftRightClickBlockEvent> SHIFT_RIGHT_CLICK_BLOCK =
            new ActionType<>(ShiftRightClickBlockEvent.class, new ShiftRightClickBlockEventListener());

    public static final ActionType<PlayerShiftEvent> PLAYER_SHIFT =
            new ActionType<>(PlayerShiftEvent.class, new PlayerShiftEventListener());

    public static final ActionType<PlayerJumpEvent> PLAYER_JUMP =
            new ActionType<>(PlayerJumpEvent.class, new PlayerJumpEventListener());

    public static final ActionType<PlayerShiftJumpEvent> PLAYER_SHIFT_JUMP =
            new ActionType<>(PlayerShiftJumpEvent.class, new PlayerShiftJumpEventListener());

    public static final ActionType<PlayerSprintEvent> PLAYER_SPRINT =
            new ActionType<>(PlayerSprintEvent.class, new PlayerSprintEventListener());

    /*public static final ActionType<PlayerJumpClickEvent> SHIFT_JUMP_RIGHT = new ActionType<>(PlayerJumpClickEvent.class);
    public static final ActionType<PlayerJumpClickEvent> SHIFT_JUMP_LEFT = new ActionType<>(PlayerJumpClickEvent.class);*/

    private final Class<T> event;
    private final AbstractListener<T> provider;

    private ActionType(Class<T> event, AbstractListener<T> provider) {
        this.event = event;
        this.provider = provider;
    }

    public Class<T> getEventClass() {
        return this.event;
    }

    public AbstractListener<T> getProvider() {
        return this.provider;
    }

    public static ActionType<?>[] values() {
        return new ActionType[]{
                CLICK,
                LEFT_CLICK,
                RIGHT_CLICK,
                LEFT_CLICK_AIR,
                LEFT_CLICK_BLOCK,
                RIGHT_CLICK_AIR,
                RIGHT_CLICK_BLOCK,
                SHIFT_LEFT_CLICK,
                SHIFT_LEFT_CLICK_AIR,
                SHIFT_LEFT_CLICK_BLOCK,
                SHIFT_RIGHT_CLICK,
                SHIFT_RIGHT_CLICK_AIR,
                SHIFT_RIGHT_CLICK_BLOCK,
                PLAYER_SHIFT,
                PLAYER_JUMP,
                PLAYER_SHIFT_JUMP,
                PLAYER_SPRINT
        };
    }
}
