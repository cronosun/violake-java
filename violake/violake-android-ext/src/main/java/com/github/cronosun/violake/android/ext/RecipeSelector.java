package com.github.cronosun.violake.android.ext;

import android.view.View;

/**
 * Finds the correct {@link Recipe} for given {@link TChildData}.
 */
public interface RecipeSelector<TChild extends View, TChildData> {
    Recipe<TChild, TChildData> recipeFor(TChildData data);

    static <TChild extends View, TChildData> RecipeSelectorBuilder<TChild, TChildData> builder() {
        return new RecipeSelectorBuilder<>();
    }
}
