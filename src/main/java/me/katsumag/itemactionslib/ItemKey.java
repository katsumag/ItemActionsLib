package me.katsumag.itemactionslib;

import java.util.regex.Pattern;

public final class ItemKey {

    private static final Pattern PATTERN = Pattern.compile("[0-9a-fA-F_\\-]");

    private final String key;

    public ItemKey(String key) {
        Utils.notNull(String.class, key);
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Key should not be empty.");
        }
        if (!PATTERN.matcher(key).matches()) {
            throw new IllegalArgumentException("Key should match regex: \""+PATTERN.toString()+"\".");
        }
        this.key = key;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof ItemKey) {
            return key.equals(((ItemKey)obj).key);
        }
        if (obj instanceof String) {
            return key.equals(obj);
        }
        return false;
    }

    @Override
    public String toString() {
        return key;
    }
}
