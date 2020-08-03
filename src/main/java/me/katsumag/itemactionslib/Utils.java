package me.katsumag.itemactionslib;

import java.util.Objects;

public final class Utils {

    private Utils() throws InitiationException {
        throw new InitiationException(Utils.class);
    }

    public static <T> void notNull(Class<T> clazz, T t) {
        Objects.requireNonNull(t, "Parameter " + clazz.getTypeName() + " may not be null.");
    }

    public static <T> void notNull(Class<T> clazz, T... t) {
        for (T tt : t) {
            Objects.requireNonNull(tt, "Parameter " + clazz.getTypeName() + " may not be null.");
        }
    }

}
