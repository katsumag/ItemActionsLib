package me.katsumag.itemactionslib;

import java.util.Objects;

public class Utils {

    public static <T> void notNull(T t) {
        Objects.requireNonNull(t, "Parameter " + t.getClass().getTypeName() + " may not be null.");
    }

    public static <T> void notNull(T t, T... ts) {
        Utils.notNull(t);
        for (T $ : ts) {
            Utils.notNull($);
        }
    }

}
