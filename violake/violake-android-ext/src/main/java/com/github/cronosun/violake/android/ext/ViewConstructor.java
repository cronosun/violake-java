package com.github.cronosun.violake.android.ext;

import android.view.View;
import android.view.ViewGroup;

import javax.annotation.Nullable;

import androidx.annotation.LayoutRes;

/**
 * Used to construct a view for a given parent. Note: child is not added to the parent.
 */
public interface ViewConstructor<T extends View> {
    /**
     * Always constructs a new child. Note: Usually you want to use
     * {@link #constructOrRecycle(ViewGroup, View)} to avoid unnecessary creation of new
     * children.
     */
    T construct(ViewGroup parent);

    /**
     * Same as {@link #construct(ViewGroup)} but might return the given child if the constructor
     * detects that the given child can be re-used.
     */
    T constructOrRecycle(ViewGroup parent, @Nullable View existingChild);

    static <T extends View> ViewConstructor<T> layout(@LayoutRes int resource) {
        return new ChildConstructors.Inflater<T>(resource);
    }

    static <T extends View> ViewConstructor<T> simple(SimpleViewConstructor<T> simple) {
        return ChildConstructors.simple(simple);
    }
}
