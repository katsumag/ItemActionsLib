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

    private ItemActionManager() {
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
     * @param itemStack - ItemStack this ActionItem should be based of.
     * @return New instance of an ActionItem.
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
                return !itemStack0.isSimilar(item)
                        || !hasNBTIdentifier()
                        || ItemNBT.getNBTTag(item, "itemActionKey").equals(key);
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
     * @param material - Material this ActionItem should be based of.
     * @return New instance of an ActionItem.
     */
    public Optional<ActionItem> newItem(ItemKey itemKey, Material material, IdentifierOption option, IdentifierOption... options) {
        Utils.notNull(Material.class, material);
        return this.newItem(itemKey, material, option, options);
    }

    public boolean hasItemKey(ItemKey itemKey) {
        if (itemKey == null) {
            return false;
        }
        Utils.notNull(ItemKey.class, itemKey);
        return itemMap.containsKey(itemKey);
    }

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

    private <T extends ListenableEvent> void removeActions(ActionType<T> type, ItemKey itemKey) {
        Utils.notNull(ActionType.class, type);
        Utils.notNull(ItemKey.class, itemKey);
        ActionItem item = itemMap.get(itemKey);
        if (item == null) {
            return;
        }
        actionMap.get(type).clearActions(item);
    }

    private void clearActions(ItemKey itemKey) {
        Utils.notNull(ItemKey.class, itemKey);
        ActionItem item = itemMap.get(itemKey);
        if (item == null) {
            return;
        }
        actionMap.values().forEach($ -> $.clearActions(item));
    }

    public Optional<ActionItem> getByKey(ItemKey itemKey) {
        Utils.notNull(ItemKey.class, itemKey);
        return Optional.ofNullable(itemMap.get(itemKey));
    }

    public Optional<ActionItem> getByStringKey(String key) {
        Utils.notNull(String.class, key);
        return Optional.ofNullable(itemMap.get(ItemKey.get(key)));
    }

    /*
    public Optional<ActionItem> getByUUID(UUID id) {

        for (AbstractListener<?> abstractListener : actionMap.values()) {
            for (ActionItem actionItem : abstractListener.getActions().keys()) {
                if (actionItem.getUniqueId() == id) return Optional.of(actionItem);
            }
        }
        return Optional.empty();
    }

    public Optional<ActionItem> getByItemStack(ItemStack item) {

        for (AbstractListener<?> abstractListener : actionMap.values()) {
            for (ActionItem actionItem : abstractListener.getActions().keys()) {
                if (actionItem.getUniqueId().equals(UUID.fromString(ItemNBT.getNBTTag(item, "ItemActionsLib")))) return Optional.of(actionItem);
            }
        }
        return Optional.empty();
    }*/

    /**
     * Creates a new instance of ItemActionManager.
     *
     * @return New instance of ItemActionManager.
     */
    public static ItemActionManager create() {
        return new ItemActionManager();
    }
}
