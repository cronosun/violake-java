package com.github.cronosun.violake.android.std;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Event;
import com.github.cronosun.violake.core.Disposable;

import androidx.annotation.Nullable;

final class DataApplication implements SingleApplication {

    private final Companion companion;
    private final Applicator applicator;
    private final Object data;
    @Nullable
    private Disposable disposable;

    DataApplication(Companion companion, Applicator applicator, Object data) {
        this.companion = companion;
        this.applicator = applicator;
        this.data = data;
    }

    @Override
    public void setActive(boolean active) {
        // TODO: Report exceptions
        if (active) {
            removeCurrentDisposable();
            setDisposable(
                    applicator.apply(companion.violake, Event.RESUME, companion.view, data));
        } else {
            removeCurrentDisposable();
        }
    }

    @Override
    public Object duplicate() {
        return this;
    }

    private void setDisposable(Disposable disposable) {
        removeCurrentDisposable();
        this.disposable = disposable;
    }

    @Override
    public void dispose() {
        boolean removed = removeCurrentDisposable();
        // also remove from companion
        this.companion.removeApplication(this, removed);
    }

    private boolean removeCurrentDisposable() {
        final Disposable localDisposable = this.disposable;
        this.disposable = null;
        if (localDisposable != null) {
            // TODO: Report exceptions
            localDisposable.dispose();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataApplication that = (DataApplication) o;

        // reference equality
        return this.data == that.data &&
                this.applicator == that.applicator &&
                this.companion == that.companion;
    }

    @Override
    public int hashCode() {
        int result = 1;
        // will do reference equality on those 3 objects
        result = 31 * result + System.identityHashCode(this.data);
        result = 31 * result + System.identityHashCode(this.applicator);
        result = 31 * result + System.identityHashCode(this.companion);

        return result;
    }
}
