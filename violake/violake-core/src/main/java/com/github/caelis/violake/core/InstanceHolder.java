package com.github.caelis.violake.core;

import javax.annotation.Nullable;

final class InstanceHolder {
    private InstanceHolder() {
    }

    static InstanceHolder INSTANCE = new InstanceHolder();

    @Nullable
    private ViolakeCore violake;
    private final Object lock = new Object();

    public void set(ViolakeCore violake) {
        synchronized (lock) {
            this.violake = violake;
        }
    }

    public ViolakeCore require() {
        ViolakeCore value = this.violake;
        if (value == null) {
            synchronized (lock) {
                value = this.violake;
            }
        }
        if (value == null) {
            throw new IllegalStateException("No violake core configured");
        } else {
            return value;
        }
    }
}
