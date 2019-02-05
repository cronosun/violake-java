package com.github.caelis.violake.example.app.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.caelis.violake.android.Violake;
import com.github.caelis.violake.android.ext.SetChildren;
import com.github.caelis.violake.example.app.R;
import com.github.caelis.violake.example.core.MenuEntries;
import com.github.caelis.violake.example.core.MenuEntriesProvider;
import com.github.caelis.violake.example.core.MenuEntry;
import com.github.caelis.violake.example.core.MenuId;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MenuFragment extends Fragment {

    private static final SetChildren<MenuEntryView, MenuEntry> SET_CHILDREN =
            SetChildren.fromLayout(new MenuEntryApplicator(), R.layout.menu_entry);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.menu_fragment, container, false);

        final ViewGroup menuEntriesContainer = view.findViewById(R.id.mainActivityMenuEntriesList);
        final MenuEntries menuEntries = MenuEntriesProvider.createMenuEntries(this::navigateTo);
        Violake.get().apply(SET_CHILDREN, menuEntriesContainer, menuEntries.getMenuEntryList());

        return view;
    }

    private void navigateTo(MenuId menuId) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentNavigator.showMenu(fragmentManager, menuId);
    }
}
