package com.github.caelis.violake.example.app.views.exampe3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.github.caelis.violake.example.app.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class ToDoPendingView extends CardView {

    public ToDoPendingView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ToDoPendingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ToDoPendingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        LayoutInflater.from(context).inflate(R.layout.example3_todo_pending, this);
    }
}
