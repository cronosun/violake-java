package com.github.caelis.arse.android.std;

import android.view.View;

import com.github.caelis.arse.android.Applicator;
import com.github.caelis.arse.android.Arse;
import com.github.caelis.arse.core.Disposable;

import org.reactivestreams.Publisher;

public class StdArse implements Arse {

    @Override
    public <TTarget extends View, TData> Disposable apply(Applicator<TTarget, TData> applicator, TTarget target, Publisher<? extends TData> stream) {
        return Companion.companionFor(this, target).apply(applicator, stream);
    }

    @Override
    public <TTarget extends View, TData> Disposable apply(Applicator<TTarget, TData> applicator, TTarget target, TData data) {
        return Companion.companionFor(this, target).apply(applicator, data);
    }

    @Override
    public Disposable disposableFrom(Runnable runnable) {
        return null;
    }

    @Override
    public Disposable append(Disposable self, Disposable next) {
        return null;
    }

    @Override
    public Disposable emptyDisposable() {
        return EmptyDisposable.INSTANCE;
    }

    @Override
    public boolean isDebugMode() {
        return true;
    }


    @Override
    public <TTarget extends View, TData> void traceOperation(Applicator<TTarget, TData> applicator, TTarget tTarget, TData data, String text) {

    }

    @Override
    public void reportStreamError(View target, Publisher<?> stream) {

    }
}
