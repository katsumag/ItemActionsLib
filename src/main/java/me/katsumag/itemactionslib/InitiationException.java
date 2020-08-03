package me.katsumag.itemactionslib;

public class InitiationException extends Exception {

    public InitiationException(Class<?> clazz) {
        super(clazz.getName() + " may not be initiated.");
    }

}
