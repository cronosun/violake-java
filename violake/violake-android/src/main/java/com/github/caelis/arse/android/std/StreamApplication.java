package com.github.caelis.arse.android.std;

import com.github.caelis.arse.android.Applicator;
import com.github.caelis.arse.android.DiffApplicator;
import com.github.caelis.arse.android.Event;
import com.github.caelis.arse.core.Disposable;

import androidx.annotation.Nullable;
import io.reactivex.Flowable;

final class StreamApplication implements SingleApplication {

    private final Companion companion;
    private final Applicator applicator;
    private final Flowable<Object> data;
    @Nullable
    private Disposable applicationDisposable;
    @Nullable
    private io.reactivex.disposables.Disposable streamDisposable;

    StreamApplication(Companion companion, Applicator applicator, Flowable<Object> data) {
        this.companion = companion;
        this.applicator = applicator;
        this.data = data;
    }

    @Override
    public void dispose() {
        boolean removedStream = removeStreamDisposable();
        boolean removedApplication = removeApplicationDisposable();
        companion.removeApplication(this,
                removedStream || removedApplication);
    }

    private void applyData(Object data) {
        boolean isUpdate = this.applicationDisposable != null;
        final Event event = isUpdate ? Event.UPDATE : Event.RESUME;
        removeApplicationDisposable();
        // TODO: Report errors

        // Make sure we don't apply new data for no reason
        if (isUpdate && this.applicator instanceof DiffApplicator) {
            DiffApplicator diffApplicator = (DiffApplicator) this.applicator;
            if (!diffApplicator.hasChanged(companion.arse, companion.view, data)) {
                return;
            }
        }

        this.applicationDisposable =
                applicator.apply(companion.arse, event, companion.view, data);
    }

    private boolean removeStreamDisposable() {
        // TODO: Report errors
        final io.reactivex.disposables.Disposable localStreamDisposable =
                this.streamDisposable;
        this.streamDisposable = null;
        if (localStreamDisposable != null) {
            localStreamDisposable.dispose();
            return true;
        } else {
            return false;
        }
    }

    private boolean removeApplicationDisposable() {
        // TODO: Report errors
        final Disposable localApplicationDisposable =
                this.applicationDisposable;
        this.applicationDisposable = null;
        if (localApplicationDisposable != null) {
            localApplicationDisposable.dispose();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setActive(boolean active) {
        if (active) {
            removeApplicationDisposable();
            removeStreamDisposable();
            this.streamDisposable = data.subscribe(
                    this::applyData,
                    (error) -> {
                        // TODO: Report error
                    });
        } else {
            removeStreamDisposable();
            removeApplicationDisposable();
        }
    }

    @Override
    public Object duplicate() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreamApplication that = (StreamApplication) o;

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
