package com.github.cronosun.violake.android.ext;

import android.view.View;
import android.view.ViewGroup;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.DiffApplicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

import javax.annotation.Nullable;

import androidx.annotation.LayoutRes;

public class SetChildren<TChild extends View, TChildData> implements
        DiffApplicator<ViewGroup, Iterable<? extends TChildData>> {

    private final Configuration<TChild, TChildData> configuration;

    public SetChildren(Configuration<TChild, TChildData> configuration) {
        this.configuration = configuration;
    }

    public static <TChild extends View, TChildData> SetChildren<TChild, TChildData>
    fromSelector(
            RecipeSelector<TChild, TChildData> selector) {
        return new SetChildren<>(new Configuration<>(selector));
    }

    public static <TChild extends View, TChildData> SetChildren<TChild, TChildData>
    fromLayout(Applicator<TChild, TChildData> applicator, @LayoutRes int resource) {
        return new SetChildren<>(
                new Configuration<>(
                        new Recipe<>(applicator, ViewConstructor.layout(resource))));
    }

    @Override
    public Disposable apply(
            Violake violake,
            Event event,
            ViewGroup target,
            Iterable<? extends TChildData> data) {
        target.setTag(R.id.set_children_view_group_data, data);

        Disposable disposable = violake.emptyDisposable();
        int numberOfChildren = 0;
        int index = configuration.startAtIndex;
        for (TChildData childData : data) {
            disposable = violake.append(
                    disposable, applySingleChild(violake, target, index, childData));
            index++;
            numberOfChildren++;
        }

        if (configuration.removeOtherChildren) {
            ChildLimiter.removeAllChildrenBut(target, numberOfChildren);
        }

        return disposable;
    }

    @Override
    public boolean hasChanged(
            Violake violake,
            ViewGroup viewGroup, Iterable<? extends TChildData> data) {
        Object existingData = viewGroup.getTag(R.id.set_children_view_group_data);
        return existingData == null || !existingData.equals(data);
    }

    private Disposable applySingleChild(
            Violake violake, ViewGroup target, int indexInTarget, TChildData childData) {
        final Recipe<TChild, TChildData> childConfiguration =
                configuration.selector.recipeFor(childData);
        final ViewConstructor<? extends TChild> constructor =
                childConfiguration.getConstructor();

        final int targetChildCount = target.getChildCount();
        final TChild child;
        if (targetChildCount > indexInTarget) {
            // Maybe re-use existing...
            final View existingChild = target.getChildAt(indexInTarget);
            child = getOrConstruct(constructor, violake, target, existingChild);
            if (existingChild != child) {
                target.addView(child, indexInTarget);
                target.removeView(existingChild);
            }
        } else if (targetChildCount == indexInTarget) {
            // match one
            child = getOrConstruct(constructor, violake, target, null);
            target.addView(child);
        } else {
            // this must not happen (since we match children one after another)
            throw new IllegalStateException("There are gaps in the layout. Example: View group " +
                    "currently looks like this: [A, B] (two children). Now you requested to add " +
                    "child D, should then look like this [A, B, C, D]. But what's about C? Don't " +
                    "know what to add there.");
        }

        return violake.apply(childConfiguration.getApplicator(), child, childData);
    }

    private TChild getOrConstruct(
            ViewConstructor<? extends TChild> constructor,
            Violake violake, ViewGroup parent, @Nullable View child) {
        TChild newOrRecycledChild = constructor.constructOrRecycle(parent, child);
        if (newOrRecycledChild != child) {
            violake.traceOperation(this, parent, "created a new child");
        }
        return newOrRecycledChild;
    }

    public static final class Configuration<TChild extends View, TChildData> {
        private final RecipeSelector<TChild, TChildData> selector;
        private final int startAtIndex;
        private final boolean removeOtherChildren;

        public Configuration(
                RecipeSelector<TChild, TChildData> selector) {
            this(selector, 0, true);
        }

        public Configuration(
                RecipeSelector<TChild, TChildData> selector,
                int startAtIndex,
                boolean removeOtherChildren) {
            this.selector = selector;
            this.startAtIndex = startAtIndex;
            this.removeOtherChildren = removeOtherChildren;
        }

        public RecipeSelector<TChild, TChildData> getSelector() {
            return selector;
        }

        public int getStartAtIndex() {
            return startAtIndex;
        }

        public boolean isRemoveOtherChildren() {
            return removeOtherChildren;
        }
    }
}

