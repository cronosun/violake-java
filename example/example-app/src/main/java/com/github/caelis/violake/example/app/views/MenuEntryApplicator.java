package com.github.caelis.violake.example.app.views;

import android.widget.Button;
import android.widget.TextView;

import com.github.caelis.violake.android.Event;
import com.github.caelis.violake.android.Violake;
import com.github.caelis.violake.core.Disposable;
import com.github.caelis.violake.example.app.R;
import com.github.caelis.violake.example.core.MenuEntry;
import com.github.caelis.violake.android.ext.GetClick;
import com.github.caelis.violake.android.ext.SetText;
import com.github.caelis.violake.android.ext.StatefulApplicator;

import androidx.annotation.Nullable;

public final class MenuEntryApplicator extends StatefulApplicator<MenuEntryView, MenuEntry> {

    @Nullable
    private TextView title;
    @Nullable
    private TextView description;
    @Nullable
    private Button actionButton;

    @Override
    protected void onNewTarget(Violake violake, MenuEntryView menuEntryView) {
        title = menuEntryView.findViewById(R.id.menuEntryTitle);
        description = menuEntryView.findViewById(R.id.menuEntryDescription);
        actionButton = menuEntryView.findViewById(R.id.menuEntryAction);
    }

    @Override
    protected Disposable applyData(
            Violake violake, Event event, MenuEntryView menuEntryView, MenuEntry menuEntry) {

        return violake.chain(
                violake.apply(SetText.get(), title, menuEntry.getTitle()),
                violake.apply(SetText.get(), description, menuEntry.getDescription()),
                violake.apply(GetClick.get(), actionButton, menuEntry::invoke)
        );
    }
}
