package java8.util;

import java.util.Arrays;

import java8.util.function.Supplier;

public final class Objects {
    private Objects() {
    }

    public static int hash(Object... var0) {
        return Arrays.hashCode(var0);
    }

    public static boolean equals(Object var0, Object var1) {
        return var0 == var1 || var0 != null && var0.equals(var1);
    }

    public static <T> T requireNonNull(T value) {
        if (value == null) {
            throw new NullPointerException();
        } else {
            return value;
        }
    }

    public static <T> T requireNonNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        } else {
            return value;
        }
    }

    public static <T> T requireNonNull(T value, Supplier<String> message) {
        if (value == null) {
            throw new NullPointerException(message.get());
        } else {
            return value;
        }
    }
}
