package com.github.cronosun.violake.android.ext;

import android.widget.TextView;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

import java.util.Optional;

/**
 * Calls {@link TextView#setError(CharSequence)}
 */
public final class SetError implements Applicator<TextView, Optional<String>> {

    private SetError() {
    }

    private static final int MAX_LEN_CMP = 255;
    private static final SetError INSTANCE = new SetError();

    public static SetError get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake violake, Event event, TextView target, Optional<String> data) {
        violake.traceOperation(this, target, data, "compare");
        if (data.isPresent()) {
            final String errorText = data.get();
            if (!StringCharSeqCmp.areEqual(target.getError(), errorText, MAX_LEN_CMP)) {
                violake.traceOperation(this, target, data, "set error");
                target.setError(errorText);
            }
        } else {
            final CharSequence currentError = target.getError();
            if (!(currentError == null || currentError.length() == 0)) {
                violake.traceOperation(this, target, data, "error text removed");
                target.setError(null);
            }
        }

        return violake.emptyDisposable();
    }
}
