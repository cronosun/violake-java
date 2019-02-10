package com.github.caelis.violake.example.app.views.exampe3;

import android.view.View;
import android.view.ViewGroup;

import com.github.cronosun.violake.android.Violake;
import com.github.caelis.violake.example.app.R;
import com.github.caelis.violake.example.app.views.ExampleFragment;
import com.github.caelis.violake.example.core.example3.Example3;

public class Example3Fragment extends ExampleFragment {

    private final Example3 example3 = new Example3();

    @Override
    protected String title() {
        return "ToDo List (Unfinished)";
    }

    @Override
    protected int layoutRes() {
        return R.layout.example3_layout;
    }

    @Override
    protected void create(View view) {
        final ViewGroup itemsContainer = view.findViewById(R.id.example3_items_container);
        Violake.get().apply(ToDoItemsChildApplicator.get(), itemsContainer, example3.getItems());
    }
}
