package com.github.caelis.violake.android.ext;

import android.widget.CompoundButton;

import com.github.caelis.arse.android.Event;
import com.github.caelis.arse.core.Disposable;
import com.github.caelis.arse.android.Applicator;
import com.github.caelis.arse.android.Violake;

public final class SetChecked implements Applicator<CompoundButton, Boolean> {

    private static final SetChecked INSTANCE = new SetChecked();

    private SetChecked() {
    }

    public static SetChecked get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake arse, Event event, CompoundButton target, Boolean checked) {
        arse.traceOperation(this, target, checked, "is checked?");
        if (target.isChecked() != checked) {
            arse.traceOperation(this, target, checked, "set checked");
            target.setChecked(checked);
        }
        return arse.emptyDisposable();
    }
}
