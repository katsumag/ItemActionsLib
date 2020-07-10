package me.katsumag.itemactionslib;

import com.google.common.collect.ImmutableList;
import org.bukkit.event.player.PlayerEvent;

import java.util.ArrayList;
import java.util.List;

public class ItemActionsManager {

    private static final List<ItemAction> ITEM_ACTION_LIST = new ArrayList<>();

    public <T extends PlayerEvent> void registerItemAction(ItemAction<T> action) {
        ITEM_ACTION_LIST.add(action);
    }

    public <T extends PlayerEvent> void removeItemAction(ItemAction<T> action) {
        ITEM_ACTION_LIST.remove(action);
    }

    protected ImmutableList<ItemAction> getItemActions() {
        return ImmutableList.copyOf(ITEM_ACTION_LIST);
    }

}
