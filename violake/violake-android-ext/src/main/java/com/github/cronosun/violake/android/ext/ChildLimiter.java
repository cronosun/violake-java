package com.github.cronosun.violake.android.ext;

import android.view.ViewGroup;

final class ChildLimiter {
    private ChildLimiter() {
    }

    static void removeAllChildrenBut(ViewGroup view, int numberOfChildrenToKeep) {
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
