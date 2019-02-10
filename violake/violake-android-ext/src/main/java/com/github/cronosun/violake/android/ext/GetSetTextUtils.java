package com.github.cronosun.violake.android.ext;

import android.widget.TextView;

final class GetSetTextUtils {

    private GetSetTextUtils() {
    }

    static void setText(TextView textView, CharSequence text) {
        textView.setTag(R.id.violake_ext_currently_setting_text, true);
        textView.setText(text);
        textView.setTag(R.id.violake_ext_currently_setting_text, null);
    }

    static boolean isSettingText(TextView textView) {
        final Object value = textView.getTag(R.id.violake_ext_currently_setting_text);
        if (value != null) {
            if (value instanceof Boolean) {
                return (boolean) value;
            } else {
                throw new RuntimeException("Invalid tag value found: " + value);
            }
        }
        return false;
    }
}
