package com.github.caelis.violake.example.app.views.example5;

import com.github.caelis.violake.example.core.example5.SwitchModel;
import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.android.ext.SetText;
import com.github.cronosun.violake.core.Disposable;

public final class Example5ProgressApplicator implements
        Applicator<Example5ProgressView, SwitchModel.Progress> {

    private Example5ProgressApplicator() {
    }

    private static final Example5ProgressApplicator INSTANCE =
            new Example5ProgressApplicator();

    public static Example5ProgressApplicator get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(
            Violake violake, Event event,
            Example5ProgressView target,
            SwitchModel.Progress data) {
        return violake.apply(SetText.get(), target.getText(), data.getText());
    }
}

