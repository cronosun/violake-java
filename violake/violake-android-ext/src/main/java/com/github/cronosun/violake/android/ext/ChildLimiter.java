package com.github.cronosun.violake.android.ext;

import android.view.ViewGroup;

public final class ChildLimiter {
    private ChildLimiter() {
    }

    public static void removeAllChildrenBut(ViewGroup view, int numberOfChildrenToKeep) {
        while (view.getChildCount() > numberOfChildrenToKeep) {
            removeLastChild(view);
        }
    }

    private static void removeLastChild(ViewGroup view) {
        int childCount = view.getChildCount();
        if (childCount > 0) {
            view.removeViewAt(childCount - 1);
        }
    }
}
