package com.github.caelis.violake.core;

public final class Unit {
    private Unit() {
    }

    private static final Unit INSTANCE = new Unit();

    public static Unit get() {
        return INSTANCE;
    }
}
