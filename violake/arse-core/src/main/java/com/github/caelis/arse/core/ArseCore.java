package com.github.caelis.arse.core;

import javax.annotation.CheckReturnValue;

public interface ArseCore {

    @CheckReturnValue
    Disposable disposableFrom(Runnable runnable);

    @CheckReturnValue
    Disposable append(Disposable self, Disposable next);

    @CheckReturnValue
    Disposable emptyDisposable();

    boolean isDebugMode();
}
