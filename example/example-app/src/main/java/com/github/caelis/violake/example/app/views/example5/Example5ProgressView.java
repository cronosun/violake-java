package com.github.caelis.violake.example.app.views.example5;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.github.caelis.violake.example.app.R;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Example5ProgressView extends ConstraintLayout {

    private TextView text;

    public Example5ProgressView(Context context) {
        super(context);
        init();
    }

    public Example5ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Example5ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.example5_progress, this);
        this.text = findViewById(R.id.example5_progress);
    }

    public TextView getText() {
        return text;
    }
}
