package com.github.cronosun.violake.android.ext;

import android.view.View;
import android.view.ViewGroup;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.DiffApplicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

import java.util.Collections;
import java.util.Optional;

import androidx.annotation.LayoutRes;

/**
 * Sets a single child (or none) to a {@link ViewGroup}.
 * <p>
 * A version of {@link SetChildren} with only 0 or one child.
 */
public final class SetChild<TChild extends View, TChildData> implements
        DiffApplicator<ViewGroup, Optional<? extends TChildData>> {

    private final SetChildren<TChild, TChildData> setChildren;

    private SetChild(SetChildren<TChild, TChildData> setChildren) {
        this.setChildren = setChildren;
    }

    public SetChild(SetChildren.Configuration<TChild, TChildData> configuration) {
        this(new SetChildren<>(configuration));
    }

    public static <TChild extends View, TChildData> SetChild<TChild, TChildData>
    fromSelector(
            RecipeSelector<TChild, TChildData> selector) {
        return new SetChild<>(new SetChildren.Configuration<>(selector));
    }

    public static <TChild extends View, TChildData> SetChild<TChild, TChildData>
    fromLayout(Applicator<TChild, TChildData> applicator, @LayoutRes int resource) {
        return new SetChild<>(
                new SetChildren.Configuration<>(
                        new Recipe<>(applicator, ViewConstructor.layout(resource))));
    }

    @Override
    public boolean hasChanged(
            Violake violake, ViewGroup viewGroup, Optional<? extends TChildData> data) {
        return data.map(tChildData -> setChildren
                .hasChanged(violake, viewGroup,
                        Collections.singletonList(tChildData)))
                .orElseGet(() -> setChildren
                        .hasChanged(violake, viewGroup, Collections.emptyList()));
    }

    @Override
    public Disposable apply(
            Violake violake, Event event, ViewGroup viewGroup,
            Optional<? extends TChildData> data) {
        if (data.isPresent()) {
            return setChildren.apply(violake, event, viewGroup,
                    Collections.singletonList(data.get()));
        } else {
            return setChildren.apply(violake, event, viewGroup,
                    Collections.emptyList());
        }
    }
}
