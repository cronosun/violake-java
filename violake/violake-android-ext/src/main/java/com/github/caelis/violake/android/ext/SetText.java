package com.github.caelis.violake.android.ext;

import android.widget.TextView;

import com.github.caelis.violake.android.Event;
import com.github.caelis.violake.core.Disposable;
import com.github.caelis.violake.android.Applicator;
import com.github.caelis.violake.android.Violake;

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
    public Disposable apply(Violake violake, Event event, TextView target, CharSequence data) {
        violake.traceOperation(this, target, data, "compare");
        if (!StringCharSeqCmp.areEqual(target.getText(), data, MAX_LEN_CMP)) {
            violake.traceOperation(this, target, data, "set text");
            target.setText(data);
        }
        return violake.emptyDisposable();
    }
}
