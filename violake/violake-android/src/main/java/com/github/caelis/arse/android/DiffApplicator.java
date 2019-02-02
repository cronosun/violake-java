package com.github.caelis.arse.android;

import android.view.View;

public interface DiffApplicator<TTarget extends View, TData> extends Applicator<TTarget, TData> {
    boolean hasChanged(Violake arse, TTarget target, TData data);
}
