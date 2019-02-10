package com.github.cronosun.violake.android.ext;

import android.widget.CompoundButton;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.core.Disposable;

import java8.util.function.Consumer;

public final class GetChecked implements Applicator<CompoundButton, Consumer<Boolean>> {

    private static final GetChecked INSTANCE = new GetChecked();

    private GetChecked() {
    }

    public static GetChecked get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(
            Violake violake, Event event, CompoundButton target, Consumer<Boolean> checked) {

        checked.accept(target.isChecked());
        target.setOnCheckedChangeListener((buttonView, isChecked) -> checked.accept(isChecked));

        return violake.disposableFrom(() -> {
            // TODO
            target.setOnCheckedChangeListener(null);
        });
    }
}
