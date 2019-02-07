package com.github.caelis.violake.android.ext;

import android.view.View;

import com.github.caelis.violake.android.Applicator;
import com.github.caelis.violake.android.Event;
import com.github.caelis.violake.android.Violake;
import com.github.caelis.violake.core.Disposable;
import com.github.caelis.violake.core.Unit;

/**
 * {@link View#requestFocus()} the focus when the view gets attached.
 */
public final class FocusOnAttach implements Applicator<View, Unit> {

    private static final FocusOnAttach INSTANCE = new FocusOnAttach();

    public static FocusOnAttach get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake violake, Event event, View view, Unit unit) {
        if (event == Event.RESUME) {
            view.requestFocus();
            violake.traceOperation(this, view, "request focus");
        }
        return violake.emptyDisposable();
    }
}
