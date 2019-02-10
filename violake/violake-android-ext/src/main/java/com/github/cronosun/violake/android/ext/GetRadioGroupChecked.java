package com.github.cronosun.violake.android.ext;

import android.widget.RadioGroup;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

import java8.util.function.Consumer;

/**
 * Get the current selection,
 * see {@link RadioGroup#setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener)}.
 */
public final class GetRadioGroupChecked implements Applicator<RadioGroup, Consumer<Integer>> {

    private static final GetRadioGroupChecked INSTANCE = new GetRadioGroupChecked();

    private GetRadioGroupChecked() {
    }

    public static GetRadioGroupChecked get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(
            Violake violake, Event event,
            RadioGroup target, Consumer<Integer> data) {

        target.setOnCheckedChangeListener((group, checkedId) -> data.accept(checkedId));

        return () -> {
            // TODO
            target.setOnCheckedChangeListener(null);
        };
    }
}
