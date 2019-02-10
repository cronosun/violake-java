package com.github.cronosun.violake.android.ext;

import android.view.View;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

public final class SetEnabled implements Applicator<View, Boolean> {

    private SetEnabled() {
    }

    private static final SetEnabled INSTANCE = new SetEnabled();

    public static SetEnabled get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake violake, Event event, View target, Boolean data) {
        if (target.isEnabled() != data) {
            target.setEnabled(data);
        }
        return violake.emptyDisposable();
    }
}
