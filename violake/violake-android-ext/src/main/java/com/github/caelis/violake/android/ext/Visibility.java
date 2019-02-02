package com.github.caelis.violake.android.ext;

import android.view.View;

public enum Visibility {
    VISIBLE(View.VISIBLE),
    INVISIBLE(View.INVISIBLE),
    GONE(View.GONE);

    private final int code;

    Visibility(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
