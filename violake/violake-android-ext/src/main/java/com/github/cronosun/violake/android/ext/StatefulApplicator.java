package com.github.cronosun.violake.android.ext;

import android.view.View;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

import androidx.annotation.Nullable;

/**
 * An applicator that is allowed to have state, so you're allowed to store fields. Whenever this
 * applicator is called with different (reference equality) {@link Violake} and {@link TTarget},
 * the {@link #onNewTarget(Violake, View)} method is called before the
 * {@link #applyData(Violake, Event, View, Object)} method ({@link #onNewTarget(Violake, View)} is
 * also called initially, so it's guaranteed that {@link #onNewTarget(Violake, View)} is always
 * called at least once before {@link #applyData(Violake, Event, View, Object)}).
 * <p>
 * Use case: Store children found by {@link View#findViewById(int)} in the
 * {@link #onNewTarget(Violake, View)}.
 */
public abstract class StatefulApplicator<TTarget extends View, TData> implements
        Applicator<TTarget, TData> {

    @Nullable
    private Violake violake;
    @Nullable
    private TTarget target;

    protected abstract void onNewTarget(Violake violake, TTarget target);

    protected abstract Disposable applyData(
            Violake violake, Event event, TTarget target, TData data);

    @Override
    public final Disposable apply(Violake violake, Event event, TTarget target, TData data) {
        if (this.target != target || this.violake != violake) {
            onNewTarget(violake, target);
        }
        return applyData(violake, event, target, data);
    }
}
