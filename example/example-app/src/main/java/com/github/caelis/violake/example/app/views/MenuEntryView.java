package com.github.caelis.violake.example.app.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.cardview.widget.CardView;

// TODO: This is the wrong way around... We should inflate the layout in this component...
public class MenuEntryView extends CardView {
    public MenuEntryView(Context context) {
        super(context);
    }

    public MenuEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuEntryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
