package com.github.caelis.arse.example.app.views.exampe3;

import android.view.View;
import android.view.ViewGroup;

import com.github.caelis.arse.android.Violake;
import com.github.caelis.arse.example.app.R;
import com.github.caelis.arse.example.app.views.ExampleFragment;
import com.github.caelis.arse.example.core.example3.Example3;

public class Example3Fragment extends ExampleFragment {

    private final Example3 example3 = new Example3();

    @Override
    protected String title() {
        return "ToDo List";
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
