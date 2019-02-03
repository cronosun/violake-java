package com.github.caelis.violake.android;

import android.view.View;

public interface DiffApplicator<TTarget extends View, TData> extends Applicator<TTarget, TData> {
    boolean hasChanged(Violake violake, TTarget target, TData data);
}
