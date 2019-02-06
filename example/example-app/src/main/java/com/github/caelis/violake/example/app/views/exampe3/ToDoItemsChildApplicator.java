package com.github.caelis.violake.example.app.views.exampe3;

import android.view.View;

import com.github.caelis.violake.android.ext.Recipe;
import com.github.caelis.violake.android.ext.RecipeSelector;
import com.github.caelis.violake.android.ext.RecipeSelectorBuilder;
import com.github.caelis.violake.android.ext.SetChildren;
import com.github.caelis.violake.example.core.example3.TodoViewItem;

public final class ToDoItemsChildApplicator {

    private ToDoItemsChildApplicator() {
    }

    public static SetChildren<View, TodoViewItem> get() {
        return INSTANCE;
    }

    private static final Recipe<ToDoTaskView, TodoViewItem.Task> TASK = new Recipe<>(
            ToDoTaskApplicator.get(), parent -> new ToDoTaskView(parent.getContext()));
    private static final Recipe<ToDoPendingView, TodoViewItem.Pending> PENDING = new Recipe<>(
            ToDoPendingApplicator.get(), parent -> new ToDoPendingView(parent.getContext()));
    private static final Recipe<ToDoAddingView, TodoViewItem.Adding> ADDING = new Recipe<>(
            TodoAddingApplicator.get(), parent -> new ToDoAddingView(parent.getContext()));


    private static SetChildren<View, TodoViewItem> create() {
        RecipeSelectorBuilder<View, TodoViewItem> builder = RecipeSelector.builder();
        builder.match(TodoViewItem.Task.class, TASK);
        builder.match(TodoViewItem.Pending.class, PENDING);
        builder.match(TodoViewItem.Adding.class, ADDING);
        return SetChildren.fromSelector(builder.build());
    }

    private static final SetChildren<View, TodoViewItem> INSTANCE = create();
}
