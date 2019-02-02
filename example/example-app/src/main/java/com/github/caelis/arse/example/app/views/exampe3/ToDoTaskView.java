package com.github.caelis.arse.example.app.views.exampe3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.caelis.arse.example.app.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class ToDoTaskView extends CardView {

    private TextView title;
    private TextView created;
    private ImageButton delete;
    private ImageButton complete;

    private ImageView doneImage;
    private ImageView notDoneImage;

    public ToDoTaskView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ToDoTaskView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ToDoTaskView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        LayoutInflater.from(context).inflate(R.layout.example3_todo_task, this);
        this.title = findViewById(R.id.example3_item_title);
        this.created = findViewById(R.id.example3_created_at);
        this.delete = findViewById(R.id.example3_delete_button);
        this.complete = findViewById(R.id.example3_done_button);
        this.doneImage = findViewById(R.id.example3_completed_icon);
        this.notDoneImage = findViewById(R.id.example3_not_done_icon);
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getCreated() {
        return created;
    }

    public ImageButton getDelete() {
        return delete;
    }

    public ImageButton getComplete() {
        return complete;
    }

    public ImageView getDoneImage() {
        return doneImage;
    }

    public ImageView getNotDoneImage() {
        return notDoneImage;
    }
}
