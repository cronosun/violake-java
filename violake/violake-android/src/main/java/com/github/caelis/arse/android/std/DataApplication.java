package com.github.caelis.arse.android.std;

import com.github.caelis.arse.android.Event;
import com.github.caelis.arse.core.Disposable;
import com.github.caelis.arse.android.Applicator;

import androidx.annotation.Nullable;

final class DataApplication implements Application {

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
            setDisposable(
                    applicator.apply(companion.arse, Event.RESUME, companion.view, data));
        } else {
            removeCurrentDisposable();
        }
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
}
