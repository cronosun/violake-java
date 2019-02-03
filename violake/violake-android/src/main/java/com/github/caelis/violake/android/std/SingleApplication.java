package com.github.caelis.violake.android.std;

import com.github.caelis.violake.core.Disposable;

interface SingleApplication extends Disposable {
    void setActive(boolean active);

    /**
     * Returned object should provide some sort of equals hashcode to avoid duplicates.
     * E.g. should be equal when two equal applications make no sense for one view.
     */
    Object duplicate();
}
