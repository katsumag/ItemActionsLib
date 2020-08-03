package me.katsumag.itemactionslib;

import me.katsumag.itemactionslib.nbt.ItemNBT;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiPredicate;
import java.util.function.Function;

public enum IdentifierOption {
    COMPARE_NBT((stack0, stack1) -> {
        Function<ItemStack, String> function = itemStack -> ItemNBT.getNBTTag(itemStack, "itemActionKey");
        return function.apply(stack0).equals(function.apply(stack1));
    }),
    COMPARE_ITEMSTACK(ItemStack::isSimilar);

    private final BiPredicate<ItemStack, ItemStack> predicate;

    IdentifierOption(BiPredicate<ItemStack, ItemStack> predicate) {
        this.predicate = predicate;
    }

    public boolean test(ItemStack itemStack0, ItemStack itemStack1) {
        return predicate.test(itemStack0, itemStack1);
    }
}
