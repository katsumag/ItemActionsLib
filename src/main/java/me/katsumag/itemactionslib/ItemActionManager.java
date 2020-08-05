package me.katsumag.itemactionslib;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import me.katsumag.itemactionslib.event.ListenableEvent;
import me.katsumag.itemactionslib.listeners.*;
import me.katsumag.itemactionslib.nbt.ItemNBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public final class ItemActionManager {

    private final Map<ActionType<?>, AbstractListener<?>> actionMap;
    private final Map<ItemKey, ActionItem> itemMap;
    private final JavaPlugin plugin;

    private static boolean isAlreadyInvoked = false;

    /**
     * Creates a new instance of ItemActionManager - will throw an {@link IllegalStateException} if you have already initialised it.
     */

    public ItemActionManager() {
        if (isAlreadyInvoked) throw new IllegalStateException("ItemActionManager has already been invoked.");
        else isAlreadyInvoked = true;

        this.plugin = JavaPlugin.getProvidingPlugin(ItemActionManager.class);

        final Map<ActionType<?>, AbstractListener<?>> map = Maps.newHashMap();
        Stream.of(ActionType.values()).forEach($ -> {
            map.put($, $.getProvider());
            this.plugin.getServer().getPluginManager().registerEvents($.getProvider(), this.plugin);
        });
        this.actionMap = ImmutableMap.copyOf(map);
        this.itemMap = new HashMap<>();
    }

    /**
     * Creates a new instance of an ActionItem.
     *
     * @param itemKey Unique ItemKey that should represent this ActionItem.
     * @param itemStack ItemStack this ActionItem should be based off.
     * @param option IdentifierOption to be used.
     * @param options IdentifierOptions to be used.
     * @return Optional<ActionItem> - contains the new ActionItem if the key isn't registered, or empty if it is.
     *
     */
    public Optional<ActionItem> newItem(ItemKey itemKey, ItemStack itemStack, IdentifierOption option, IdentifierOption... options) {
        Utils.notNull(ItemKey.class, itemKey);
        Utils.notNull(ItemStack.class, itemStack);
        Utils.notNull(IdentifierOption.class, option);
        Utils.notNull(IdentifierOption.class, options);
        if (itemMap.containsKey(itemKey)) {
            return Optional.empty();
        }
        ActionItem item = new ActionItem() {

            private final Set<IdentifierOption> set;
            private final ItemStack itemStack0;
            private final String key;

            {
                set = Sets.immutableEnumSet(option, options);
                key = itemKey.toString();
                itemStack0 = hasNBTIdentifier()
                        ? ItemNBT.setNBTTag(itemStack.clone(), "itemActionKey", key)
                        : itemStack.clone();
            }

            @Override
            public <T extends ListenableEvent> ActionItem addAction(ActionType<T> type, Action<T> action) {
                ItemActionManager.this.addAction(type, action, itemKey);
                return this;
            }

            @Override
            public <T extends ListenableEvent> void removeActions(ActionType<T> type) {
                ItemActionManager.this.removeActions(type, itemKey);
            }

            @Override
            public void clearActions() {
                ItemActionManager.this.clearActions(itemKey);
            }

            @Override
            public Set<IdentifierOption> getIdentifierOptions() {
                return set;
            }

            @Override
            public ItemStack get() {
                return itemStack0;
            }

            @Override
            public boolean isSimilar(ActionItem item) {
                if (item == null) {
                    return false;
                }
                return isSimilar(item.get());
            }

            @Override
            public boolean isSimilar(ItemStack item) {
                if (item == null) {
                    return false;
                }
                if (item.getType() == Material.AIR) {
                    return false;
                }
                return set.stream().allMatch(option -> option.test(itemStack0, item));
            }

            @Override
            public boolean hasNBTIdentifier() {
                return set.contains(IdentifierOption.COMPARE_NBT);
            }

            @Override
            public ItemKey getKey() {
                return itemKey;
            }

            @Override
            public int hashCode() {
                return itemKey.hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null) {
                    return false;
                }
                if (obj == this) {
                    return true;
                }
                if (obj instanceof ActionItem) {
                    return ((ActionItem) obj).getKey().equals(itemKey);
                }
                if (obj instanceof ItemKey) {
                    return obj.toString().equals(key);
                }
                return false;
            }
        };
        itemMap.put(itemKey,item);
        return Optional.of(item);
    }

    /**
     * Creates a new instance of an ActionItem.
     *
     * @param itemKey Unique ItemKey that should represent this ActionItem.
     * @param material Material this ActionItem should be based off.
     * @param option IdentifierOption to be used.
     * @param options IdentifierOptions to be used.
     * @return Optional<ActionItem> - contains the new ActionItem if the key isn't registered, or empty if it is.
     *
     */
    public Optional<ActionItem> newItem(ItemKey itemKey, Material material, IdentifierOption option, IdentifierOption... options) {
        Utils.notNull(Material.class, material);
        return this.newItem(itemKey, new ItemStack(material), option, options);
    }

    /**
     *
     * @param itemKey The ItemKey to check.
     * @return boolean If the itemKey is registered to an item.
     */

    public boolean hasItemKey(ItemKey itemKey) {
        if (itemKey == null) {
            return false;
        }
        Utils.notNull(ItemKey.class, itemKey);
        return itemMap.containsKey(itemKey);
    }

    /**
     *
     * @param itemKey The ItemKey to remove by.
     * @return boolean If the key (and subsequently its item) was removed.
     */

    public boolean removeItem(ItemKey itemKey) {
        if (itemKey == null) {
            return false;
        }
        if (!hasItemKey(itemKey)) {
            return false;
        }
        itemMap.remove(itemKey);
        return true;
    }

    /**
     * @param type The ActionType to trigger the action.
     * @param action The actual action to be ran.
     * @param itemKey the ItemKey for the item to add the action to.
     */
    @SuppressWarnings("unchecked")
    private <T extends ListenableEvent> void addAction(ActionType<T> type, Action<T> action, ItemKey itemKey) {
        Utils.notNull(ActionType.class, type);
        Utils.notNull(Action.class, action);
        Utils.notNull(ItemKey.class, itemKey);
        AbstractListener<?> listener = actionMap.get(type);
        ActionItem item = itemMap.get(itemKey);
        if (item == null) {
            return;
        }
        if (type.getProvider().getClass().isInstance(listener)) {
            type.getProvider().getClass().cast(listener).addAction(action, item);
        }
    }

    /**
     * @param type The ActionType to remove
     * @param itemKey The ItemKey for the item to remove the actions from.
     */
    private <T extends ListenableEvent> void removeActions(ActionType<T> type, ItemKey itemKey) {
        Utils.notNull(ActionType.class, type);
        Utils.notNull(ItemKey.class, itemKey);
        ActionItem item = itemMap.get(itemKey);
        if (item == null) {
            return;
        }
        actionMap.get(type).clearActions(item);
    }

    /**
     *
     * @param itemKey The ItemKey for the item to remove the actions from.
     */
    private void clearActions(ItemKey itemKey) {
        Utils.notNull(ItemKey.class, itemKey);
        ActionItem item = itemMap.get(itemKey);
        if (item == null) {
            return;
        }
        actionMap.values().forEach($ -> $.clearActions(item));
    }

    /**
     *
     * @param itemKey The ItemKey corresponding to the ActionItem to fetch.
     * @return An Optional - contains the ActionItem if the key has an item, or null if not.
     */
    public Optional<ActionItem> getByKey(ItemKey itemKey) {
        Utils.notNull(ItemKey.class, itemKey);
        return Optional.ofNullable(itemMap.get(itemKey));
    }

    /**
     *
     * @param key The String value of an ItemKey corresponding to the ActionItem to fetch.
     * @return An Optional - contains the ActionItem if the key has an item, or null if not.
     */
    public Optional<ActionItem> getByStringKey(String key) {
        Utils.notNull(String.class, key);
        return Optional.ofNullable(itemMap.get(new ItemKey(key)));
    }
}
