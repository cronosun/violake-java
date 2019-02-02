package com.github.caelis.violake.android.ext;

import android.view.View;

import com.github.caelis.arse.android.Applicator;
import com.github.caelis.arse.android.Event;
import com.github.caelis.arse.android.Violake;
import com.github.caelis.arse.core.Disposable;

public final class SetVisibilityInt implements Applicator<View, Integer> {

    private SetVisibilityInt() {
    }

    private static final SetVisibilityInt INSTANCE = new SetVisibilityInt();

    public static SetVisibilityInt get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake arse, Event event, View target, Integer data) {
        int currentVisibility = target.getVisibility();
        if (currentVisibility != data) {
            arse.traceOperation(this, target, data, "set visibility");
            target.setVisibility(data);
        }
        return arse.emptyDisposable();
    }
}
