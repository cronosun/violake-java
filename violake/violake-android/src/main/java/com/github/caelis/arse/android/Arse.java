package com.github.caelis.arse.android;

import android.view.View;

import com.github.caelis.arse.core.ArseCore;
import com.github.caelis.arse.core.Disposable;

import org.reactivestreams.Publisher;

import javax.annotation.CheckReturnValue;

public interface Arse extends ArseCore {
    static Arse get() {
        return InstanceHolder.INSTANCE.require();
    }

    static void setInstance(Arse arse) {
        InstanceHolder.INSTANCE.set(arse);
    }

    @CheckReturnValue
    <TTarget extends View, TData> Disposable apply(
            Applicator<TTarget, TData> applicator,
            TTarget target,
            Publisher<? extends TData> stream);

    @CheckReturnValue
    <TTarget extends View, TData> Disposable apply(
            Applicator<TTarget, TData> applicator,
            TTarget target,
            TData data);

    <TTarget extends View, TData> void traceOperation(
            Applicator<TTarget, TData> applicator,
            TTarget target,
            TData data,
            String text);


    void reportStreamError(View target, Publisher<?> stream);

    // TODO: Return Scheduler? Answer: No, would be A RxJava Dependency...
}
