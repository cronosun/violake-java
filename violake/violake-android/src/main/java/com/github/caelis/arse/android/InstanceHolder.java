package com.github.caelis.arse.android;

import com.github.caelis.arse.android.std.StdViolake;

import javax.annotation.Nullable;

final class InstanceHolder {
    private InstanceHolder() {
    }

    static InstanceHolder INSTANCE = new InstanceHolder();

    @Nullable
    private Violake arse;
    private final Object lock = new Object();

    public void set(Violake arse) {
        synchronized (lock) {
            this.arse = arse;
        }
    }

    public Violake require() {
        Violake value = this.arse;
        if (value == null) {
            synchronized (lock) {
                value = this.arse;
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
