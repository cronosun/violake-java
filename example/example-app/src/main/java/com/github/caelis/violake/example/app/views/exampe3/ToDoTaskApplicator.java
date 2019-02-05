package com.github.caelis.violake.example.app.views.exampe3;

import android.widget.ImageButton;

import com.github.caelis.violake.android.Applicator;
import com.github.caelis.violake.android.Event;
import com.github.caelis.violake.android.Violake;
import com.github.caelis.violake.android.ext.GetClick;
import com.github.caelis.violake.android.ext.SetEnabled;
import com.github.caelis.violake.android.ext.SetText;
import com.github.caelis.violake.android.ext.SetVisibility;
import com.github.caelis.violake.android.ext.Visibility;
import com.github.caelis.violake.core.Disposable;
import com.github.caelis.violake.example.core.example3.Action;
import com.github.caelis.violake.example.core.example3.TodoViewItem;

public final class ToDoTaskApplicator implements Applicator<ToDoTaskView, TodoViewItem.Task> {

    private ToDoTaskApplicator() {
    }

    private static final ToDoTaskApplicator INSTANCE = new ToDoTaskApplicator();

    public static ToDoTaskApplicator get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(
            Violake violake,
            Event event,
            ToDoTaskView target,
            TodoViewItem.Task data) {

        return violake.chain(
                violake.apply(SetText.get(), target.getTitle(), data.getText()),
                violake.apply(SetText.get(), target.getCreated(), data.getCreationDate()),
                violake.apply(SetVisibility.get(), target.getDoneImage(), data.isCompleted()
                        ? Visibility.VISIBLE : Visibility.INVISIBLE),
                violake.apply(SetVisibility.get(), target.getNotDoneImage(), data.isCompleted()
                        ? Visibility.INVISIBLE : Visibility.VISIBLE),

                configureAction(violake, data, Action.ADD, target.getAdd()),
                configureAction(violake, data, Action.DELETE, target.getDelete()),
                configureAction(violake, data, Action.COMPLETE, target.getComplete())
        );
    }

    private Disposable configureAction(
            Violake violake, TodoViewItem.Task data, Action action, ImageButton button) {
        boolean isAvailable = data.getAvailableActions().contains(action);
        if (isAvailable) {
            return violake.chain(
                    violake.apply(SetEnabled.get(), button, true),
                    violake.apply(GetClick.get(), button,
                            () -> data.getPerformAction().accept(action)));
        } else {
            return violake.apply(SetEnabled.get(), button, false);
        }
    }
}
