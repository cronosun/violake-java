package com.github.caelis.violake.android;

import android.view.View;

import com.github.caelis.violake.core.Disposable;
import com.github.caelis.violake.core.ViolakeCore;

import org.reactivestreams.Publisher;

import javax.annotation.CheckReturnValue;

public interface Violake extends ViolakeCore {
    static Violake get() {
        return InstanceHolder.INSTANCE.require();
    }

    static void setInstance(Violake violake) {
        InstanceHolder.INSTANCE.set(violake);
    }

    @CheckReturnValue
    <TTarget extends View, TData> Disposable apply(
            Applicator<? super TTarget, TData> applicator,
            TTarget target,
            Publisher<? extends TData> stream);

    @CheckReturnValue
    <TTarget extends View, TData> Disposable apply(
            Applicator<? super TTarget, TData> applicator,
            TTarget target,
            TData data);

    <TTarget extends View, TData> void traceOperation(
            Applicator<? super TTarget, TData> applicator,
            TTarget target,
            TData data,
            String text);

    <TTarget extends View, TData> void traceOperation(
            Applicator<? super TTarget, TData> applicator,
            TTarget target,
            String text);


    void reportStreamError(View target, Publisher<?> stream);

    // TODO: Return Scheduler? Answer: No, would be A RxJava Dependency...
}
