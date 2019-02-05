package com.github.caelis.violake.example.app.views.exampe3;

import android.view.View;

import com.github.caelis.violake.android.ext.Recipe;
import com.github.caelis.violake.android.ext.RecipeSelector;
import com.github.caelis.violake.android.ext.RecipeSelectorBuilder;
import com.github.caelis.violake.android.ext.SetChildren;
import com.github.caelis.violake.example.core.example3.ToDoItem;

public final class ToDoItemsChildApplicator {

    private ToDoItemsChildApplicator() {
    }

    public static SetChildren<View, ToDoItem> get() {
        return INSTANCE;
    }

    private static final Recipe<ToDoTaskView, ToDoItem.Task> TASK = new Recipe<>(
            ToDoTaskApplicator.get(), parent -> new ToDoTaskView(parent.getContext()));
    private static final Recipe<ToDoPendingView, ToDoItem.Pending> PENDING = new Recipe<>(
            ToDoPendingApplicator.get(), parent -> new ToDoPendingView(parent.getContext()));

    private static SetChildren<View, ToDoItem> create() {
        RecipeSelectorBuilder<View, ToDoItem> builder = RecipeSelector.builder();
        builder.match(ToDoItem.Task.class, TASK);
        builder.match(ToDoItem.Pending.class, PENDING);
        return SetChildren.fromSelector(builder.build());
    }

    private static final SetChildren<View, ToDoItem> INSTANCE = create();
}
