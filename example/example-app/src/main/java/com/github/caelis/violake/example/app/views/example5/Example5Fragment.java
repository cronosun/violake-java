package com.github.caelis.violake.example.app.views.example5;

import android.view.View;

import com.github.caelis.violake.example.app.R;
import com.github.caelis.violake.example.app.views.ExampleFragment;
import com.github.caelis.violake.example.app.views.example4.Example4View;
import com.github.caelis.violake.example.app.views.example4.Example4ViewApplicator;
import com.github.caelis.violake.example.core.example4.Example4Model;
import com.github.caelis.violake.example.core.example4.Example4Service;
import com.github.caelis.violake.example.core.example5.Example5Model;
import com.github.cronosun.violake.android.Violake;

public class Example5Fragment extends ExampleFragment {

    private final Example5Model model = new Example5Model();

    @Override
    protected String title() {
        return "Layout Switching";
    }

    @Override
    protected int layoutRes() {
        return R.layout.example5_layout;
    }

    @Override
    protected void create(View view) {
        Example5View example5View = (Example5View) view;
        Violake.get().apply(Example5ViewApplicator.get(), example5View, model);
    }
}
