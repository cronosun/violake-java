package com.github.caelis.arse.android;

import com.github.caelis.arse.android.std.StdArse;

import javax.annotation.Nullable;

final class InstanceHolder {
    private InstanceHolder() {
    }

    static InstanceHolder INSTANCE = new InstanceHolder();

    @Nullable
    private Arse arse;
    private final Object lock = new Object();

    public void set(Arse arse) {
        synchronized (lock) {
            this.arse = arse;
        }
    }

    public Arse require() {
        Arse value = this.arse;
        if (value == null) {
            synchronized (lock) {
                value = this.arse;
            }
        }
        if (value == null) {
            final Arse fallback = new StdArse();
            set(fallback);
            return fallback;
        } else {
            return value;
        }
    }

}
