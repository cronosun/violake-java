package com.github.cronosun.violake.android.ext;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

import java8.util.function.Consumer;

/**
 * Gets text from {@link EditText}.
 * <p>
 * By default does not report changes caused by setting text programmatically - as long as you
 * use one of the provided set text methods provided in this package, like {@link SetText} or
 * {@link SetEditorText}. If you don't like this behaviour, use
 * {@link #getInstanceReportSetTextChanges()}.
 */
public final class GetText implements Applicator<EditText, Consumer<String>> {

    private final boolean doReportSetTextChanges;

    private GetText(boolean doReportSetTextChanges) {
        this.doReportSetTextChanges = doReportSetTextChanges;
    }

    private static final GetText INSTANCE = new GetText(false);
    private static final GetText INSTANCE_REPORT_SET_TEXT_CHANGES =
            new GetText(true);

    public static GetText get() {
        return INSTANCE;
    }

    /**
     * Also reports changes caused by setting text programmatically.
     */
    public static GetText getInstanceReportSetTextChanges() {
        return INSTANCE_REPORT_SET_TEXT_CHANGES;
    }

    @Override
    public Disposable apply(
            Violake violake,
            Event event,
            EditText editText,
            Consumer<String> data) {

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                if (!doReportSetTextChanges) {
                    if (GetSetTextUtils.isSettingText(editText)) {
                        // nope, do not report that change
                        return;
                    }
                }
                data.accept(text.toString());
            }

            @Override
            public void afterTextChanged(Editable text) {

            }
        };
        editText.addTextChangedListener(textWatcher);
        return violake.disposableFrom(() -> editText.removeTextChangedListener(textWatcher));
    }
}
