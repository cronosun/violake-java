package com.github.caelis.violake.example.app.views.example5;

import android.view.View;

import com.github.caelis.violake.example.app.R;
import com.github.caelis.violake.example.core.example5.Example5Model;
import com.github.caelis.violake.example.core.example5.Selection;
import com.github.caelis.violake.example.core.example5.SwitchModel;
import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.android.ext.GetRadioGroupChecked;
import com.github.cronosun.violake.android.ext.Recipe;
import com.github.cronosun.violake.android.ext.RecipeSelector;
import com.github.cronosun.violake.android.ext.RecipeSelectorBuilder;
import com.github.cronosun.violake.android.ext.SetChild;
import com.github.cronosun.violake.core.Disposable;

import java8.util.function.Consumer;

public final class Example5ViewApplicator implements Applicator<Example5View, Example5Model> {

    private final Recipe<Example5ImageWithActionView, SwitchModel.ImageAndAction> IMAGE_AND_ACTION =
            new Recipe<>(Example5ImageWithActionApplicator.get(),
                    parent -> new Example5ImageWithActionView(parent.getContext()));
    private final Recipe<Example5ProgressView, SwitchModel.Progress> PROGRESS =
            new Recipe<>(Example5ProgressApplicator.get(),
                    parent -> new Example5ProgressView(parent.getContext()));

    private Example5ViewApplicator() {
    }

    private static final Example5ViewApplicator INSTANCE = new Example5ViewApplicator();

    public static Example5ViewApplicator get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(
            Violake violake,
            Event event,
            Example5View target,
            Example5Model data) {

        RecipeSelectorBuilder<View, SwitchModel> builder = RecipeSelector.builder();
        builder.match(SwitchModel.ImageAndAction.class, IMAGE_AND_ACTION);
        builder.match(SwitchModel.Progress.class, PROGRESS);
        SetChild<View, SwitchModel> applicator = SetChild.fromSelector(builder.build());

        return violake.chain(
                violake.apply(GetRadioGroupChecked.get(), target.getRadioGroup(),
                        (Consumer<Integer>) id -> {
                            switch (id) {
                                case R.id.example5_radio_button1:
                                    data.setSelection(Selection.IMAGE_AND_ACTION);
                                    break;
                                case R.id.example5_radio_button2:
                                    data.setSelection(Selection.WEB_ACTION);
                                    break;
                            }
                        }
                ),
                violake.apply(
                        applicator,
                        target.getContainer(),
                        data.getSwitchData())
        );
    }
}
