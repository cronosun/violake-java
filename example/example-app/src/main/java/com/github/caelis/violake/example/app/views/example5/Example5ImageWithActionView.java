package com.github.caelis.violake.example.app.views.example5;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;

import com.github.caelis.violake.example.app.R;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Example5ImageWithActionView extends ConstraintLayout {

    private Button actionButton;

    public Example5ImageWithActionView(Context context) {
        super(context);
        init();
    }

    public Example5ImageWithActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Example5ImageWithActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.example5_image_with_action, this);
        this.actionButton = findViewById(R.id.example5_action);
    }

    public Button getActionButton() {
        return actionButton;
    }
}
