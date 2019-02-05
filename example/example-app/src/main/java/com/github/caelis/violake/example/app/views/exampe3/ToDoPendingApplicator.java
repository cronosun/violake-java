package com.github.caelis.violake.example.app.views.exampe3;

import com.github.caelis.violake.android.Applicator;
import com.github.caelis.violake.android.Event;
import com.github.caelis.violake.android.Violake;
import com.github.caelis.violake.core.Disposable;
import com.github.caelis.violake.example.core.example3.ToDoItem;

public final class ToDoPendingApplicator implements Applicator<ToDoPendingView, ToDoItem.Pending> {

    private ToDoPendingApplicator() {
    }

    private static final ToDoPendingApplicator INSTANCE = new ToDoPendingApplicator();

    public static ToDoPendingApplicator get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(
            Violake violake,
            Event event,
            ToDoPendingView toDoPendingView,
            ToDoItem.Pending pending) {
        // nothing to do here
        return violake.emptyDisposable();
    }
}
