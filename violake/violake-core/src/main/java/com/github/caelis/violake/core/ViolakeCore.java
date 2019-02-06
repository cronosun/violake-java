package com.github.caelis.violake.core;

import javax.annotation.CheckReturnValue;

public interface ViolakeCore {

    @CheckReturnValue
    Disposable disposableFrom(Runnable runnable);

    @CheckReturnValue
    Disposable append(Disposable self, Disposable next);

    @CheckReturnValue
    Disposable chain(Disposable... disposables);

    @CheckReturnValue
    Disposable emptyDisposable();

    /**
     * Post given item on violake thread. If this is called on the violake thread,
     * the {@link Runnable} is allowed to be executed immediately.
     */
    void post(Runnable runnable);

    boolean isDebugMode();

    static ViolakeCore get() {
        return InstanceHolder.INSTANCE.require();
    }

    static void set(ViolakeCore core) {
        InstanceHolder.INSTANCE.set(core);
    }
}
