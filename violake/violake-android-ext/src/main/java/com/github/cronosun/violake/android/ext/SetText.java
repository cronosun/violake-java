package com.github.cronosun.violake.android.ext;

import android.widget.TextView;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

public final class SetText implements Applicator<TextView, CharSequence> {

    private SetText() {
    }

    private static final SetText INSTANCE = new SetText();
    private static final int MAX_LEN_CMP = 255;

    public static SetText get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake violake, Event event, TextView target, CharSequence data) {
        violake.traceOperation(this, target, data, "compare");
        if (!StringCharSeqCmp.areEqual(target.getText(), data, MAX_LEN_CMP)) {
            violake.traceOperation(this, target, data, "set text");
            GetSetTextUtils.setText(target, data);
        }
        return violake.emptyDisposable();
    }
}
