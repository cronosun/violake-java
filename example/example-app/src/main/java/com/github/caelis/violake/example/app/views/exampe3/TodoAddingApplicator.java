package com.github.caelis.violake.example.app.views.exampe3;

import com.github.caelis.violake.android.Applicator;
import com.github.caelis.violake.android.Event;
import com.github.caelis.violake.android.Violake;
import com.github.caelis.violake.android.ext.GetClick;
import com.github.caelis.violake.android.ext.GetText;
import com.github.caelis.violake.android.ext.SetEditorText;
import com.github.caelis.violake.android.ext.SetEnabled;
import com.github.caelis.violake.core.Disposable;
import com.github.caelis.violake.example.core.example3.TodoViewItem;

public class TodoAddingApplicator implements Applicator<ToDoAddingView, TodoViewItem.Adding> {

    private TodoAddingApplicator() {
    }

    private static final TodoAddingApplicator INSTANCE = new TodoAddingApplicator();

    public static TodoAddingApplicator get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(
            Violake violake,
            Event event,
            ToDoAddingView target,
            TodoViewItem.Adding data) {

        return violake.chain(
                violake.apply(GetText.get(), target.getTextInput(), data.getAdding().getTextInput()),
                violake.apply(SetEditorText.get(), target.getTextInput(), data.getAdding().getText()),
                violake.apply(SetEnabled.get(), target.getOkButton(), data.getAdding().getCanAdd()),
                violake.apply(GetClick.get(), target.getOkButton(), data.getAdding().getAdd()),
                violake.apply(GetClick.get(), target.getCancelButton(), data.getAdding().getCancel())
        );
    }
}
