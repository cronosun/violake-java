package com.github.caelis.arse.android;

import android.view.View;

import com.github.caelis.arse.core.Disposable;
import com.github.caelis.arse.core.ViolakeCore;

import org.reactivestreams.Publisher;

import javax.annotation.CheckReturnValue;

public interface Violake extends ViolakeCore {
    static Violake get() {
        return InstanceHolder.INSTANCE.require();
    }

    static void setInstance(Violake arse) {
        InstanceHolder.INSTANCE.set(arse);
    }

    @CheckReturnValue
    <TTarget extends View, TData> Disposable apply(
            Applicator<? extends TTarget, ? extends TData> applicator,
            TTarget target,
            Publisher<? extends TData> stream);

    @CheckReturnValue
    <TTarget extends View, TData> Disposable apply(
            Applicator<? extends TTarget, ? extends TData> applicator,
            TTarget target,
            TData data);

    <TTarget extends View, TData> void traceOperation(
            Applicator<TTarget, TData> applicator,
            TTarget target,
            TData data,
            String text);

    <TTarget extends View, TData> void traceOperation(
            Applicator<TTarget, TData> applicator,
            TTarget target,
            String text);


    void reportStreamError(View target, Publisher<?> stream);

    // TODO: Return Scheduler? Answer: No, would be A RxJava Dependency...
}
