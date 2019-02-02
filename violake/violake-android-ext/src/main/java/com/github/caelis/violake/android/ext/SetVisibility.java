package com.github.caelis.violake.android.ext;

import android.view.View;

import com.github.caelis.arse.android.Applicator;
import com.github.caelis.arse.android.Event;
import com.github.caelis.arse.android.Violake;
import com.github.caelis.arse.core.Disposable;

public final class SetVisibility implements Applicator<View, Visibility> {

    private SetVisibility() {
    }

    private static final SetVisibility INSTANCE = new SetVisibility();

    public static SetVisibility get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake violake, Event event, View target, Visibility data) {
        return SetVisibilityInt.get().apply(violake, event, target, data.getCode());
    }
}
