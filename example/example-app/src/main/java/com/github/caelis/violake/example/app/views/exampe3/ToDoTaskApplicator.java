package com.github.caelis.violake.example.app.views.exampe3;

import com.github.caelis.violake.android.Applicator;
import com.github.caelis.violake.android.Event;
import com.github.caelis.violake.android.Violake;
import com.github.caelis.violake.core.Disposable;
import com.github.caelis.violake.example.core.example3.ToDoItem;
import com.github.caelis.violake.android.ext.GetClick;
import com.github.caelis.violake.android.ext.SetEnabled;
import com.github.caelis.violake.android.ext.SetText;
import com.github.caelis.violake.android.ext.SetVisibility;
import com.github.caelis.violake.android.ext.Visibility;

public final class ToDoTaskApplicator implements Applicator<ToDoTaskView, ToDoItem.Task> {

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
            ToDoItem.Task data) {

        final Disposable alwaysDisposable = violake.chain(
                violake.apply(SetText.get(), target.getTitle(), data.getText()),
                violake.apply(SetText.get(), target.getCreated(), data.getCreationDate()),
                violake.apply(SetVisibility.get(), target.getDoneImage(), data.isCompleted()
                        ? Visibility.VISIBLE : Visibility.INVISIBLE),
                violake.apply(SetVisibility.get(), target.getNotDoneImage(), data.isCompleted()
                        ? Visibility.INVISIBLE : Visibility.VISIBLE),
                violake.apply(SetEnabled.get(), target.getDelete(), data.getDelete() != null),
                violake.apply(SetEnabled.get(), target.getComplete(), data.getComplete() != null)
        );

        final Disposable deleteDisposable;
        if (target.getDelete() != null) {
            deleteDisposable = violake.apply(GetClick.get(), target.getDelete(),
                    () -> data.getDelete().run());
        } else {
            deleteDisposable = violake.emptyDisposable();
        }

        final Disposable completeDisposable;
        if (target.getDelete() != null) {
            completeDisposable = violake.apply(GetClick.get(), target.getComplete(),
                    () -> data.getComplete().run());
        } else {
            completeDisposable = violake.emptyDisposable();
        }

        return violake.chain(alwaysDisposable, deleteDisposable, completeDisposable);
    }
}
