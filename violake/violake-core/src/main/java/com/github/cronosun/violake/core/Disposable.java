package com.github.cronosun.violake.core;

public interface Disposable {
    /**
     * Dispose this. If already disposed this is a no-op. It's OK to dispose something more
     * than once.
     */
    void dispose();
}
