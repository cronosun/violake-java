package com.github.caelis.violake.example.app.views.example4;

import android.view.View;

import com.github.caelis.violake.android.Violake;
import com.github.caelis.violake.example.app.R;
import com.github.caelis.violake.example.app.views.ExampleFragment;
import com.github.caelis.violake.example.core.example4.Example4Model;
import com.github.caelis.violake.example.core.example4.Example4Service;

public class Example4Fragment extends ExampleFragment {

    private final Example4Service service = new Example4Service();
    private final Example4Model model = new Example4Model(service);

    @Override
    protected String title() {
        return "Form";
    }

    @Override
    protected int layoutRes() {
        return R.layout.example4_layout;
    }

    @Override
    protected void create(View view) {
        Example4View example4View = (Example4View)view;
        Violake.get().apply(Example4ViewApplicator.get(), example4View, model);

    }
}
