package com.github.caelis.violake.android.ext;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.github.caelis.violake.android.Applicator;
import com.github.caelis.violake.android.Event;
import com.github.caelis.violake.android.Violake;
import com.github.caelis.violake.core.Disposable;

import java8.util.function.Consumer;

public final class GetText implements Applicator<EditText, Consumer<String>> {

    private GetText() {
    }

    private static final GetText INSTANCE = new GetText();

    public static GetText get() {
        return INSTANCE;
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
