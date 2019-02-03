package com.github.caelis.violake.example.app.views.exampe3;

import android.view.View;
import android.view.ViewGroup;

import com.github.caelis.violake.example.core.example3.ToDoItem;
import com.github.caelis.violake.android.ext.ChildConstructor;
import com.github.caelis.violake.android.ext.SetChildren;
import com.github.caelis.violake.android.ext.SimpleChildConstructor;

public final class ToDoItemsChildApplicator {

    private static final SetChildren<View, ToDoItem> INSTANCE = create();

    private ToDoItemsChildApplicator() {
    }

    public static SetChildren<View, ToDoItem> get() {
        return INSTANCE;
    }

    private static SetChildren<View, ToDoItem> create() {
        // TODO: We only have the task applicator... we need to extend the 'set child' to support multiple configurations (one per data type).
        SetChildren.Configuration<View, ToDoItem> configuration =
                new SetChildren.Configuration<View, ToDoItem>(ToDoTaskApplicator.get(), ChildConstructor.simple(new SimpleChildConstructor<View>() {
                    @Override
                    public View construct(ViewGroup parent) {
                        return new ToDoTaskView(parent.getContext());
                    }
                }));

        return new SetChildren<>(configuration);
    }

}
