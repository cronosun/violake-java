package com.github.caelis.violake.example.app.views.example2;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.github.cronosun.violake.android.Violake;
import com.github.caelis.violake.example.app.R;
import com.github.caelis.violake.example.app.views.ExampleFragment;
import com.github.caelis.violake.example.core.example2.Example2;
import com.github.cronosun.violake.android.ext.GetChecked;
import com.github.cronosun.violake.android.ext.GetClick;
import com.github.cronosun.violake.android.ext.SetChecked;
import com.github.cronosun.violake.android.ext.SetText;

public class Example2Fragment extends ExampleFragment {

    private final Example2 data = new Example2();

    @Override
    protected String title() {
        return "Example 2";
    }

    @Override
    protected int layoutRes() {
        return R.layout.example2_layout;
    }

    @Override
    protected void create(View view) {
        // find views
        Button button = view.findViewById(R.id.the_button);
        TextView text = view.findViewById(R.id.the_text_view);
        CheckBox box = view.findViewById(R.id.checkBox);

        // Bind data
        Violake.get().apply(GetClick.get(), button, data::incrementClickCount);
        Violake.get().apply(SetText.get(), text, data.message());
        Violake.get().apply(SetChecked.get(), box, data.isEvenClickCount());
        Violake.get().apply(GetChecked.get(), box, data::setEventClickCount);
    }
}
