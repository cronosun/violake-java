package java8.util;

import java.util.Arrays;

public final class Objects {
    private Objects() {
    }

    public static int hash(Object... var0) {
        return Arrays.hashCode(var0);
    }

    public static boolean equals(Object var0, Object var1) {
        return var0 == var1 || var0 != null && var0.equals(var1);
    }
}
