package com.github.caelis.arse.android;

import android.view.View;

import com.github.caelis.arse.core.Disposable;

public interface Applicator<TTarget extends View, TData> {
    Disposable apply(Violake violake, Event event, TTarget target, TData data);
}
