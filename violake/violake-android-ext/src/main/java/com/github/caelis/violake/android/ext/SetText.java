package com.github.caelis.violake.android.ext;

import android.widget.TextView;

import com.github.caelis.arse.android.Event;
import com.github.caelis.arse.core.Disposable;
import com.github.caelis.arse.android.Applicator;
import com.github.caelis.arse.android.Violake;

import javax.annotation.Nullable;

public final class SetText implements Applicator<TextView, CharSequence> {

    private SetText() {
    }

    private static final SetText INSTANCE = new SetText();
    private static final int MAX_LEN_CMP = 255;

    public static SetText get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake arse, Event event, TextView target, CharSequence data) {
        arse.traceOperation(this, target, data, "compare");
        if (!areEqual(target.getText(), data)) {
            arse.traceOperation(this, target, data, "set text");
            target.setText(data);
        }
        return arse.emptyDisposable();
    }

    private boolean areEqual(@Nullable CharSequence a, CharSequence b) {
        if (a == b) {
            return true;
        }
        if (a == null) {
            return false;
        }
        final int length = a.length();
        if (length != b.length()) {
            return false;
        }
        if (length == 0) {
            return true;
        }
        if (length > MAX_LEN_CMP) {
            // too long, won't compare
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }


}
