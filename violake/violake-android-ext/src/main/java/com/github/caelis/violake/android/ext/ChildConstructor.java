package com.github.caelis.violake.android.ext;

import androidx.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import java.util.function.Supplier;

import javax.annotation.Nullable;

/**
 * Used to construct a child for a given parent. Note: child is not added to the parent.
 */
public interface ChildConstructor<T extends View> {
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

    static <T extends View> ChildConstructor<T> layout(@LayoutRes int resource) {
        return new ChildConstructors.Inflater<T>(resource);
    }

    static <T extends View> ChildConstructor<T> simple(SimpleChildConstructor<T> simple) {
        return ChildConstructors.simple(simple);
    }

}
