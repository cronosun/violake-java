package com.github.cronosun.violake.android.ext;

import android.widget.CompoundButton;

import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.core.Disposable;
import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Violake;

public final class SetChecked implements Applicator<CompoundButton, Boolean> {

    private static final SetChecked INSTANCE = new SetChecked();

    private SetChecked() {
    }

    public static SetChecked get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake violake, Event event, CompoundButton target, Boolean checked) {
        violake.traceOperation(this, target, checked, "is checked?");
        if (target.isChecked() != checked) {
            violake.traceOperation(this, target, checked, "set checked");
            target.setChecked(checked);
        }
        return violake.emptyDisposable();
    }
}
