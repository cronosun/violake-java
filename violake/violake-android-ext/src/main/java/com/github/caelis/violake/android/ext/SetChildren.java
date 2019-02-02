package com.github.caelis.violake.android.ext;

import android.view.View;
import android.view.ViewGroup;

import com.github.caelis.arse.android.Applicator;
import com.github.caelis.arse.android.DiffApplicator;
import com.github.caelis.arse.android.Event;
import com.github.caelis.arse.android.Violake;
import com.github.caelis.arse.android.ext.R;
import com.github.caelis.arse.core.Disposable;

import javax.annotation.Nullable;

import androidx.annotation.LayoutRes;

public class SetChildren<TChild extends View, TChildData> implements
        DiffApplicator<ViewGroup, Iterable<? extends TChildData>> {

    private final Configuration<TChild, TChildData> configuration;

    public SetChildren(Configuration<TChild, TChildData> configuration) {
        this.configuration = configuration;
    }

    public static <TChild extends View, TChildData> SetChildren<TChild, TChildData> fromLayout(
            Applicator<TChild, TChildData> applicator, @LayoutRes int resource) {
        return new SetChildren<>(new Configuration<>(applicator, ChildConstructor.layout(resource)));
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
            Violake arse,
            ViewGroup viewGroup, Iterable<? extends TChildData> data) {
        Object existingData = viewGroup.getTag(R.id.set_children_view_group_data);
        return existingData == null || !existingData.equals(data);
    }

    private Disposable applySingleChild(
            Violake violake, ViewGroup target, int indexInTarget, TChildData childData) {
        final int targetChildCount = target.getChildCount();
        final TChild child;
        if (targetChildCount > indexInTarget) {
            // Maybe re-use existing...
            final View existingChild = target.getChildAt(indexInTarget);
            child = getOrConstruct(violake, target, existingChild);
            if (existingChild != child) {
                target.addView(child, indexInTarget);
                target.removeView(existingChild);
            }
        } else if (targetChildCount == indexInTarget) {
            // add one
            child = getOrConstruct(violake, target, null);
            target.addView(child);
        } else {
            // this must not happen (since we add children one after another)
            throw new IllegalStateException("There are gaps in the layout. Example: View group " +
                    "currently looks like this: [A, B] (two children). Now you requested to add " +
                    "child D, should then look like this [A, B, C, D]. But what's about C? Don't " +
                    "know what to add there.");
        }

        return violake.apply(configuration.applicator, child, childData);
    }

    private TChild getOrConstruct(Violake violake, ViewGroup parent, @Nullable View child) {
        final ChildConstructor<? extends TChild> constructor = configuration.constructor;
        TChild newOrRecycledChild = constructor.constructOrRecycle(parent, child);
        if (newOrRecycledChild != child) {
            violake.traceOperation(this, parent, "created a new child");
        }
        return newOrRecycledChild;
    }

    public static final class Configuration<TChild extends View, TChildData> {
        private final Applicator<? extends TChild, ? extends TChildData> applicator;
        private final ChildConstructor<? extends TChild> constructor;
        private final int startAtIndex;
        private final boolean removeOtherChildren;

        public Configuration(
                Applicator<? extends TChild, ? extends TChildData> applicator,
                ChildConstructor<? extends TChild> constructor) {
            this(applicator, constructor, 0, true);
        }

        public Configuration(
                Applicator<? extends TChild, ? extends TChildData> applicator,
                ChildConstructor<? extends TChild> constructor,
                int startAtIndex,
                boolean removeOtherChildren) {
            this.applicator = applicator;
            this.constructor = constructor;
            this.startAtIndex = startAtIndex;
            this.removeOtherChildren = removeOtherChildren;
        }

        public Applicator<? extends TChild, ? extends TChildData> getApplicator() {
            return applicator;
        }

        public ChildConstructor<? extends TChild> getConstructor() {
            return constructor;
        }

        public int getStartAtIndex() {
            return startAtIndex;
        }

        public boolean isRemoveOtherChildren() {
            return removeOtherChildren;
        }
    }
}

