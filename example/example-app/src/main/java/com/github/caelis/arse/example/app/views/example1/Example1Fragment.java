package com.github.caelis.arse.example.app.views.example1;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.caelis.arse.android.Violake;
import com.github.caelis.arse.example.app.R;
import com.github.caelis.arse.example.app.views.ExampleFragment;
import com.github.caelis.arse.example.core.example1.Example1;
import com.github.caelis.violake.android.ext.GetClick;
import com.github.caelis.violake.android.ext.SetText;
import com.github.caelis.violake.android.ext.SetVisibility;
import com.github.caelis.violake.android.ext.Visibility;

import io.reactivex.Flowable;

public class Example1Fragment extends ExampleFragment {

    private final Example1 data = new Example1();

    @Override
    protected String title() {
        return "Example 1";
    }

    @Override
    protected int layoutRes() {
        return R.layout.example1_layout;
    }

    @Override
    protected void create(View view) {
        // find views
        Button button = view.findViewById(R.id.the_button);
        TextView text = view.findViewById(R.id.the_text_view);
        ImageView evenImage = view.findViewById(R.id.even_image);
        ImageView oddImage = view.findViewById(R.id.odd_image);

        // Bind data
        Violake.get().apply(GetClick.get(), button, data::incrementClickCount);
        Violake.get().apply(SetText.get(), text, data.message());
        Violake.get().apply(SetVisibility.get(), evenImage,
                Flowable.fromPublisher(data.isEvenClickCount())
                        .map(even -> even ? Visibility.VISIBLE : Visibility.GONE));
        Violake.get().apply(SetVisibility.get(), oddImage,
                Flowable.fromPublisher(data.isEvenClickCount())
                        .map(even -> even ? Visibility.GONE : Visibility.VISIBLE));
    }
}
