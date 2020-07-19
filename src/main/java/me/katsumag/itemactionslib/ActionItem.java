package me.katsumag.itemactionslib;

import me.katsumag.itemactionslib.event.ListenableEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.function.Supplier;

public interface ActionItem extends Supplier<ItemStack> {

    /**
     * Assigns an Action to an ActionType.
     *
     * @param type The target type that the Action should be assigned on.
     * @param action The action representing a Consumer.
     * @param <T> The type that the ActionType and the Action is based on.
     *
     * @return The ActionItem instance which is the Object that this method got invoked on.
     */
    <T extends ListenableEvent> ActionItem addAction(ActionType<T> type, Action<T> action);

    /**
     * Removes all actions associated with this ActionItem instance specified by ActionType.
     *
     * @param type The target type that all actions should be removed on.
     * @param <T> The type that the ActionType is based on.
     */
    <T extends ListenableEvent> void removeActions(ActionType<T> type);

    /**
     * Removes all actions associated with this ActionItem instance.
     */
    void clearActions();

    /**
     * @return A UUID for this item instance.
     */
    UUID getUniqueId();
}
