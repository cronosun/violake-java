package com.github.caelis.violake.android;

import com.github.caelis.violake.android.std.StdViolake;

import javax.annotation.Nullable;

final class InstanceHolder {
    private InstanceHolder() {
    }

    static InstanceHolder INSTANCE = new InstanceHolder();

    @Nullable
    private Violake violake;
    private final Object lock = new Object();

    public void set(Violake violake) {
        synchronized (lock) {
            this.violake = violake;
        }
    }

    public Violake require() {
        Violake value = this.violake;
        if (value == null) {
            synchronized (lock) {
                value = this.violake;
            }
        }
        if (value == null) {
            final Violake fallback = new StdViolake();
            set(fallback);
            return fallback;
        } else {
            return value;
        }
    }

}
