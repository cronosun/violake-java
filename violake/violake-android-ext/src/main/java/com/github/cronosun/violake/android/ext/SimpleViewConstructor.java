package com.github.cronosun.violake.android.ext;

import android.view.View;
import android.view.ViewGroup;

public interface SimpleViewConstructor<T extends View> {
    /**
     * Constructs a new child.
     */
    T construct(ViewGroup parent);
}
