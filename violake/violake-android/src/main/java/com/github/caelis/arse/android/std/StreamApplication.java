package com.github.caelis.arse.android.std;

import com.github.caelis.arse.android.Event;
import com.github.caelis.arse.core.Disposable;
import com.github.caelis.arse.android.Applicator;

import androidx.annotation.Nullable;
import io.reactivex.Flowable;

final class StreamApplication implements Application {

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
}
