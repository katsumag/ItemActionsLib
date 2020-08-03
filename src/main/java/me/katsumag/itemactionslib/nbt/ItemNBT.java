package me.katsumag.itemactionslib.nbt;

import me.katsumag.itemactionslib.InitiationException;
import me.katsumag.itemactionslib.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Class to set / get NBT tags from items
 *
 * @Author Matt - MF-GUI, original author of this class.
 *
 * FYI - This class has been heavily modified from MF-GUI by {@Author Conclure}.
 */
public final class ItemNBT {

    private static final Class<?>
            CLASS_NMS_NBTTAGCOMPOUND,
            CLASS_NMS_ITEMSTACK,
            CLASS_CRAFT_ITEMSTACK;

    private static final Constructor<?>
            CONSTRUCTOR_NMS_NBTTAGCOMPOUND;

    private static final Method
            METHOD_GET_STRING,
            METHOD_SET_STRING,
            METHOD_HAS_TAG,
            METHOD_GET_TAG,
            METHOD_SET_TAG,
            METHOD_ASNMSCOPY,
            METHOD_ASBUKKITCOPY;

    static {
        try {
            CLASS_NMS_NBTTAGCOMPOUND = getNMSClass("NBTTagCompound");
            CLASS_NMS_ITEMSTACK = getNMSClass("ItemStack");
            CLASS_CRAFT_ITEMSTACK = getCraftClass("inventory.CraftItemStack");

            CONSTRUCTOR_NMS_NBTTAGCOMPOUND = CLASS_NMS_NBTTAGCOMPOUND.getConstructor();

            METHOD_GET_STRING = CLASS_NMS_NBTTAGCOMPOUND.getMethod("getString", String.class);
            METHOD_SET_STRING = CLASS_NMS_NBTTAGCOMPOUND.getMethod("setString", String.class, String.class);

            METHOD_HAS_TAG = CLASS_NMS_ITEMSTACK.getMethod("hasTag");
            METHOD_GET_TAG = CLASS_NMS_ITEMSTACK.getMethod("getTag");
            METHOD_SET_TAG = CLASS_NMS_ITEMSTACK.getMethod("setTag", CLASS_NMS_NBTTAGCOMPOUND);

            METHOD_ASNMSCOPY = CLASS_CRAFT_ITEMSTACK.getMethod("asNMSCopy", ItemStack.class);
            METHOD_ASBUKKITCOPY = CLASS_CRAFT_ITEMSTACK.getMethod("asBukkitCopy", CLASS_NMS_ITEMSTACK);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private ItemNBT() throws InitiationException {
        throw new InitiationException(ItemNBT.class);
    }

    private static Class<?> getClass(final String className) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clazz;
    }

    private static Object invokeMethod(Method method, Object object, Object... args) {
        try {
            return method.invoke(object, args);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getNBTTag(ItemStack itemStack, String key) {
        Utils.notNull(ItemStack.class, itemStack);
        Utils.notNull(String.class, key);
        if (itemStack.getType() == Material.AIR) {
            throw new IllegalArgumentException("Material of ItemStack cannot be AIR.");
        }

        Object nmsItemStack = asNMSCopy(itemStack);
        Object itemCompound = getNBTCompound(nmsItemStack);

        return getString(itemCompound, key);
    }

    public static ItemStack setNBTTag(ItemStack itemStack, String key, String value) {
        Utils.notNull(ItemStack.class, itemStack);
        Utils.notNull(String.class, key);
        if (itemStack.getType() == Material.AIR) {
            throw new IllegalArgumentException("Material of ItemStack cannot be AIR.");
        }

        Object nmsItemStack = asNMSCopy(itemStack);
        Object itemCompound = getNBTCompound(nmsItemStack);

        setString(itemCompound, key, value);
        setTag(nmsItemStack, itemCompound);

        return asBukkitCopy(nmsItemStack);
    }

    private static Object getNBTCompound(Object nmsItemStack) {
        return hasTag(nmsItemStack) ? getTag(nmsItemStack) : newNBTTagCompound();
    }

    private static void setString(Object nbtTagCompound, String key, String value) {
        invokeMethod(METHOD_SET_STRING, nbtTagCompound, key, value);
    }

    private static String getString(Object nbtTagCompound, String key) {
        return (String) invokeMethod(METHOD_GET_STRING, nbtTagCompound, key);
    }

    private static boolean hasTag(Object nmsItemStack) {
        return (boolean) invokeMethod(METHOD_HAS_TAG, nmsItemStack);
    }

    private static Object getTag(Object nmsItemStack) {
        return invokeMethod(METHOD_GET_TAG, nmsItemStack);
    }

    private static void setTag(Object nmsItemStack, Object nbtTagCompound) {
        invokeMethod(METHOD_SET_TAG, nmsItemStack, nbtTagCompound);
    }

    private static Object newNBTTagCompound() {
        try {
            return CONSTRUCTOR_NMS_NBTTAGCOMPOUND.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object asNMSCopy(ItemStack itemStack) {
        return invokeMethod(METHOD_ASNMSCOPY, null, itemStack);
    }

    private static ItemStack asBukkitCopy(Object nmsItemStack) {
        return (ItemStack) invokeMethod(METHOD_ASBUKKITCOPY, null, nmsItemStack);
    }

    private static Class<?> getNMSClass(String className) {
        return getClass("net.minecraft.server." + ServerVersion.NMS_VERSION + "." + className);
    }

    private static Class<?> getCraftClass(String className) {
        return getClass("org.bukkit.craftbukkit." + ServerVersion.NMS_VERSION + "." + className);
    }
}
