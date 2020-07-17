package me.katsumag.itemactionslib;

import com.google.common.collect.ImmutableMap;
import org.bukkit.event.player.PlayerEvent;

import java.util.HashMap;
import java.util.Map;

public class ItemActionsManager {

    private static final Map<ItemAction<? extends PlayerEvent>, ActionType<? extends PlayerEvent>> ACTION_MAP = new HashMap<>();

    public <T extends PlayerEvent> void registerItemAction(ActionType<T> type, ItemAction<T> action) {
        ACTION_MAP.put(action, type);
    }

    public <T extends PlayerEvent> void removeItemAction(ItemAction<T> action) {
        ACTION_MAP.remove(action);
    }

    public ImmutableMap<ItemAction<? extends PlayerEvent>, ActionType<? extends PlayerEvent>> getItemActions() {
        return ImmutableMap.copyOf(ACTION_MAP);
    }

}
