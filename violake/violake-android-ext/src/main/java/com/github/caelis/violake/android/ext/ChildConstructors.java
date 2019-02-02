package com.github.caelis.violake.android.ext;

import androidx.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.caelis.arse.android.ext.R;

import javax.annotation.Nullable;

final class ChildConstructors {

    private ChildConstructors() {
    }

    static class Inflater<T extends View> extends DefaultChildConstructor<T> {

        Inflater(@LayoutRes int resource) {
            this.resource = resource;
        }

        @LayoutRes
        private final int resource;

        @SuppressWarnings("unchecked")
        @Override
        protected T constructInternal(ViewGroup parent) {
            return (T) LayoutInflater.from(parent.getContext()).
                    inflate(resource, parent, false);
        }


        @Nullable
        @Override
        public Object discriminator() {
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Inflater<?> inflater = (Inflater<?>) o;
            return resource == inflater.resource;
        }

        @Override
        public int hashCode() {
            return resource;
        }
    }

    private static abstract class DefaultChildConstructor<T extends View>
            implements ChildConstructor<T> {

        protected abstract T constructInternal(ViewGroup parent);

        @Override
        public final T construct(ViewGroup parent) {
            final T view = constructInternal(parent);
            Object discriminator = discriminator();
            if (discriminator != null) {
                view.setTag(R.id.child_constructor_constructed_by, discriminator);
            }
            return view;
        }

        @Override
        public final T constructOrRecycle(ViewGroup parent, @Nullable View existingChild) {
            // has been constructed by myself?
            if (existingChild != null) {
                final Object existingDiscriminator = existingChild.getTag(
                        R.id.child_constructor_constructed_by);
                if (existingDiscriminator != null) {
                    final Object thisDiscriminator = discriminator();
                    if (existingDiscriminator.equals(thisDiscriminator)) {
                        //noinspection unchecked
                        return (T) existingChild;
                    }
                }
            }

            // need to create a new child
            return construct(parent);
        }

        /**
         * Must implement equals / hashcode. It's used to check if the child has been constructed
         * by this constructor. So: Two child constructors with the same discriminator should
         * produce the same children (re-usable children). Return null if this constructor always
         * produces different (incompatible) children.
         * <p>
         * Say there's a child C in the layout that has been constructed by a constructor with
         * discriminator X. Later the system wants to now if we have to re-construct child C or
         * whether we can re-use child C - the system compares the discriminators.
         */
        @Nullable
        protected abstract Object discriminator();
    }
}