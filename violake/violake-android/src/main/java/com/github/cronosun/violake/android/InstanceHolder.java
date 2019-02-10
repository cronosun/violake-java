package com.github.cronosun.violake.android;

import com.github.cronosun.violake.android.std.StdViolake;
import com.github.cronosun.violake.core.ViolakeCore;

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
            ViolakeCore.set(fallback);
            set(fallback);
            return fallback;
        } else {
            return value;
        }
    }
}
