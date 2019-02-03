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

    boolean isDebugMode();
}
