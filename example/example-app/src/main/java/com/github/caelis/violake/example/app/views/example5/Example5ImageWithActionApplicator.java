package com.github.caelis.violake.example.app.views.example5;

import com.github.caelis.violake.example.core.example5.SwitchModel;
import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.android.ext.GetClick;
import com.github.cronosun.violake.core.Disposable;

public final class Example5ImageWithActionApplicator implements
        Applicator<Example5ImageWithActionView, SwitchModel.ImageAndAction> {

    private Example5ImageWithActionApplicator() {
    }

    private static final Example5ImageWithActionApplicator INSTANCE =
            new Example5ImageWithActionApplicator();

    public static Example5ImageWithActionApplicator get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(
            Violake violake, Event event,
            Example5ImageWithActionView target,
            SwitchModel.ImageAndAction data) {
        return violake.apply(GetClick.get(), target.getActionButton(), data.getAction());
    }
}

