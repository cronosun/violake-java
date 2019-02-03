package com.github.caelis.violake.example.app.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.caelis.violake.example.app.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public abstract class ExampleFragment extends Fragment {

    @Nullable
    @Override
    public final View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.example_fragment, container, false);
    }

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Toolbar toolbar = view.findViewById(R.id.example_fragment_toolbar);
        toolbar.setTitle(title());
        toolbar.setNavigationOnClickListener(v ->
                FragmentNavigator.showMenu(getFragmentManager(), FragmentNavigator.MAIN));

        final ViewGroup container = view.findViewById(R.id.example_fragment_container);
        final View exampleView = LayoutInflater.from(getContext()).inflate(
                layoutRes(), container, false);
        container.addView(exampleView);

        create(exampleView);
    }

    protected abstract String title();

    @LayoutRes
    protected abstract int layoutRes();

    protected abstract void create(View view);
}
