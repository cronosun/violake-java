package com.github.caelis.violake.example.app.views.exampe3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;

import com.github.caelis.violake.example.app.R;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class ToDoAddingView extends CardView {

    private TextInputEditText textInput;
    private Button okButton;
    private Button cancelButton;

    public ToDoAddingView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ToDoAddingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ToDoAddingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        LayoutInflater.from(context).inflate(R.layout.example3_todo_adding, this);
        textInput = findViewById(R.id.todo_text_input);
        okButton = findViewById(R.id.example3_ok_button);
        cancelButton = findViewById(R.id.example3_cancel_button);
    }

    public TextInputEditText getTextInput() {
        return textInput;
    }

    public Button getOkButton() {
        return okButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
