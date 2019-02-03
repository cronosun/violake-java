package com.github.caelis.violake.android.std;

import com.github.caelis.violake.core.Disposable;

final class EmptyDisposable implements Disposable {

    static final EmptyDisposable INSTANCE = new EmptyDisposable();

    public static boolean isEmpty(Disposable disposable) {
        return disposable == INSTANCE;
    }

    private EmptyDisposable() {
    }

    @Override
    public void dispose() {
    }
}
