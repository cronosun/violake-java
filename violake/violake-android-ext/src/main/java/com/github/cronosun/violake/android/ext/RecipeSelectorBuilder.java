package com.github.cronosun.violake.android.ext;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import java8.util.Objects;

public final class RecipeSelectorBuilder<TChild extends View, TChildData> {

    @Nullable
    private List<Entry<TChild, TChildData>> entries;

    RecipeSelectorBuilder() {
    }

    public RecipeSelectorBuilder<TChild, TChildData> match(
            Predicate<TChildData> predicate, Recipe<TChild, TChildData> recipe) {
        return match(new Entry<>(predicate, recipe));
    }

    @SuppressWarnings("unchecked")
    public <TData extends TChildData> RecipeSelectorBuilder<TChild, TChildData> match(
            Class<TData> cls, Recipe<? extends TChild, TData> recipe) {
        return match((data) -> cls.isAssignableFrom(data.getClass()), (Recipe) recipe);
    }

    public RecipeSelector<TChild, TChildData> build() {
        final List<Entry<TChild, TChildData>> moved = this.entries;
        this.entries = null;
        return new RecipeSelectorFromBuilder<>(moved);
    }

    private RecipeSelectorBuilder<TChild, TChildData> match(
            Entry<TChild, TChildData> entry) {
        if (entries == null) {
            entries = new ArrayList<>(5);
        }
        this.entries.add(entry);
        return this;
    }

    public interface Predicate<TChildData> {
        boolean test(TChildData data);
    }

    private static class Entry<TChild extends View, TChildData> {
        private final Predicate<TChildData> test;
        private final Recipe<TChild, TChildData> recipe;

        private Entry(Predicate<TChildData> test, Recipe<TChild, TChildData> recipe) {
            this.test = Objects.requireNonNull(test);
            this.recipe = Objects.requireNonNull(recipe);
        }
    }

    private static class RecipeSelectorFromBuilder<TChild extends View, TChildData>
            implements RecipeSelector<TChild, TChildData> {

        @Nullable
        private final List<Entry<TChild, TChildData>> entries;

        private RecipeSelectorFromBuilder(@Nullable List<Entry<TChild, TChildData>> entries) {
            this.entries = entries;
        }

        @Override
        public Recipe<TChild, TChildData> recipeFor(TChildData data) {
            if (entries == null) {
                throw noElementFor(data);
            }
            final int size = entries.size();
            for (int i = 0; i < size; i++) {
                final Entry<TChild, TChildData> entry = entries.get(i);
                if (entry.test.test(data)) {
                    return entry.recipe;
                }
            }
            throw noElementFor(data);
        }

        private RuntimeException noElementFor(TChildData data) {
            throw new RuntimeException("This selector is not exhaustive. There's no entry for " +
                    "the data type " + data.getClass() + "; data " + data);
        }
    }
}
