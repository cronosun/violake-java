package com.github.caelis.violake.android;

import android.view.View;

import com.github.caelis.violake.core.Disposable;

public interface Applicator<TTarget extends View, TData> {
    Disposable apply(Violake violake, Event event, TTarget target, TData data);
}
