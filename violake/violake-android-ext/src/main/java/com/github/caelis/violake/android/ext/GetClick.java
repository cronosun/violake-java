package com.github.caelis.violake.android.ext;

import android.view.View;

import com.github.caelis.arse.android.Applicator;
import com.github.caelis.arse.android.Violake;
import com.github.caelis.arse.android.Event;
import com.github.caelis.arse.core.Disposable;

public final class GetClick implements Applicator<View, Runnable> {

    private GetClick() {
    }

    private static final GetClick INSTANCE = new GetClick();

    public static GetClick get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake arse, Event event, View view, Runnable data) {
        arse.traceOperation(this, view, data, "get click");

        view.setOnClickListener(v -> data.run());

        // TODO: Won't work like this
        return () -> {
            view.setOnClickListener(null);
        };
    }
}
