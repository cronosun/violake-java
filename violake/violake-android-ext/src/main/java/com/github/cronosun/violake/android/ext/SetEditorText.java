package com.github.cronosun.violake.android.ext;

import android.widget.EditText;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

import javax.annotation.Nullable;

/**
 * Similar to {@link SetText} but optimized for {@link EditText} (for example never applies text
 * when the same text is already in the view and tries to keep selection).
 */
public final class SetEditorText implements Applicator<EditText, CharSequence> {

    private SetEditorText() {
    }

    private static final SetEditorText INSTANCE = new SetEditorText();

    public static SetEditorText get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(Violake violake, Event event, EditText target, CharSequence data) {
        violake.traceOperation(this, target, data, "compare");
        if (!areEqual(target.getText(), data)) {
            violake.traceOperation(this, target, data, "set text");
            return doApply(violake, target, data);
        }
        return violake.emptyDisposable();
    }

    private Disposable doApply(Violake violake, EditText target, CharSequence data) {
        final int oldTextLen = target.getText().length();
        final int newTextLen = data.length();
        final int selectionStart = target.getSelectionStart();
        final int selectionEnd = target.getSelectionEnd();

        int newSelectionStart = selectionStart;
        int newSelectionEnd = selectionEnd;

        if (newSelectionStart != newSelectionEnd) {
            // have some sort of selection
            if (newSelectionEnd == oldTextLen) {
                // end selected, again try to select end
                newSelectionEnd = newTextLen;
            }
        }

        // fix if selection out of bounds
        if (newSelectionEnd > newTextLen) {
            newSelectionEnd = newTextLen;
        }
        if (newSelectionStart > newTextLen) {
            newSelectionStart = newTextLen;
        }


        target.setText(data);
        target.setSelection(newSelectionStart, newSelectionEnd);

        return violake.emptyDisposable();
    }

    private boolean areEqual(@Nullable CharSequence a, CharSequence b) {
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
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
