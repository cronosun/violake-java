package com.github.caelis.violake.android.ext;

import android.view.View;

import com.github.caelis.violake.android.Applicator;

/**
 * A recipe is a combination of:
 * <p>
 * - {@link Applicator}
 * - {@link ViewConstructor}
 * <p>
 * So it has the information how to construct a view and how to apply data to
 * that constructed view.
 */
public final class Recipe<TChild extends View, TChildData> implements
        RecipeSelector<TChild, TChildData> {

    private final Applicator<? extends TChild, ? extends TChildData> applicator;
    private final ViewConstructor<? extends TChild> constructor;

    public Recipe(
            Applicator<? extends TChild, ? extends TChildData> applicator,
            ViewConstructor<? extends TChild> constructor) {
        this.applicator = applicator;
        this.constructor = constructor;
    }

    public Recipe(
            Applicator<? extends TChild, ? extends TChildData> applicator,
            SimpleViewConstructor<? extends TChild> constructor) {
        this(applicator, ViewConstructor.simple(constructor));
    }

    public Applicator<? extends TChild, ? extends TChildData> getApplicator() {
        return applicator;
    }

    public ViewConstructor<? extends TChild> getConstructor() {
        return constructor;
    }

    @Override
    public Recipe<TChild, TChildData> recipeFor(TChildData tChildData) {
        return this;
    }
}
