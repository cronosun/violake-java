package com.github.cronosun.violake.android.ext;

import android.view.View;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

public final class SetVisibilityInt implements Applicator<View, Integer> {

    private SetVisibilityInt() {
    }

    private static final SetVisibilityInt INSTANCE = new SetVisibilityInt();

    public static SetVisibilityInt get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake violake, Event event, View target, Integer data) {
        int currentVisibility = target.getVisibility();
        if (currentVisibility != data) {
            violake.traceOperation(this, target, data, "set visibility");
            target.setVisibility(data);
        }
        return violake.emptyDisposable();
    }
}
