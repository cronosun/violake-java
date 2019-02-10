package com.github.cronosun.violake.android.ext;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Optional;

/**
 * Calls {@link TextInputLayout#setError(CharSequence)}
 */
public final class SetTextInputLayoutError implements Applicator<TextInputLayout, Optional<String>> {

    private SetTextInputLayoutError() {
    }

    private static final int MAX_LEN_CMP = 255;
    private static final SetTextInputLayoutError INSTANCE = new SetTextInputLayoutError();

    public static SetTextInputLayoutError get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake violake, Event event, TextInputLayout target, Optional<String> data) {
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
