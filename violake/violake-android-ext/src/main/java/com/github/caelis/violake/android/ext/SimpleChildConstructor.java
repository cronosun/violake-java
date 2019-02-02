package com.github.caelis.violake.android.ext;

import android.view.View;
import android.view.ViewGroup;

public interface SimpleChildConstructor<T extends View> {
    /**
     * Constructs a new child.
     */
    T construct(ViewGroup parent);
}
