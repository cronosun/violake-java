package com.github.caelis.violake.example.app.views.example5;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.github.caelis.violake.example.app.R;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import java8.util.Objects;

public class Example5View extends ConstraintLayout {

    @Nullable
    private FrameLayout container;
    @Nullable
    private RadioButton radioButton1;
    @Nullable
    private RadioButton radioButton2;
    @Nullable
    private RadioGroup radioGroup;

    public Example5View(Context context) {
        super(context);
        init();
    }

    public Example5View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Example5View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.example5_view, this);
        this.container = findViewById(R.id.example5_container);
        this.radioButton1 = findViewById(R.id.example5_radio_button1);
        this.radioButton2 = findViewById(R.id.example5_radio_button2);
        this.radioGroup = findViewById(R.id.example5_radio_group);
    }

    public FrameLayout getContainer() {
        return Objects.requireNonNull(container);
    }

    public RadioButton getRadioButton1() {
        return Objects.requireNonNull(radioButton1);
    }

    public RadioButton getRadioButton2() {
        return Objects.requireNonNull(radioButton2);
    }

    public RadioGroup getRadioGroup() {
        return Objects.requireNonNull(radioGroup);
    }
}
