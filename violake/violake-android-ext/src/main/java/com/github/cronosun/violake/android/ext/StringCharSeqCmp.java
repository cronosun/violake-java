package com.github.cronosun.violake.android.ext;

import javax.annotation.Nullable;

final class StringCharSeqCmp {

    private StringCharSeqCmp() {
    }

    /**
     * Compares two char sequences.
     * <p>
     * - Returns true if both strings are equal.
     * - Returns false if strings are not equal or if strings are too long (exceed the given length).
     */
    static boolean areEqual(@Nullable CharSequence a, CharSequence b, int compareUpToLength) {
        if (a == b) {
            return true;
        }
        if (a == null) {
            return false;
        }
        final int length = a.length();
        if (length != b.length()) {
            return false;
        }
        if (length == 0) {
            return true;
        }
        if (length > compareUpToLength) {
            // too long, won't compare
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
