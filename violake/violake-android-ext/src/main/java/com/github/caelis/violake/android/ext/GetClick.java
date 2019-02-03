package com.github.caelis.violake.android.ext;

import android.view.View;

import com.github.caelis.violake.android.Applicator;
import com.github.caelis.violake.android.Violake;
import com.github.caelis.violake.android.Event;
import com.github.caelis.violake.core.Disposable;

public final class GetClick implements Applicator<View, Runnable> {

    private GetClick() {
    }

    private static final GetClick INSTANCE = new GetClick();

    public static GetClick get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake violake, Event event, View view, Runnable data) {
        violake.traceOperation(this, view, data, "get click");

        view.setOnClickListener(v -> data.run());

        // TODO: Won't work like this
        return () -> {
            view.setOnClickListener(null);
        };
    }
}
