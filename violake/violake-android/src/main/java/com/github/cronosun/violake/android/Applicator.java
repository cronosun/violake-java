package com.github.cronosun.violake.android;

import android.view.View;

import com.github.cronosun.violake.core.Disposable;

public interface Applicator<TTarget extends View, TData> {
    Disposable apply(Violake violake, Event event, TTarget target, TData data);
}
