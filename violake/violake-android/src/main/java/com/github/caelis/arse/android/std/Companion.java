package com.github.caelis.arse.android.std;

import android.view.View;

import com.github.caelis.arse.core.Disposable;
import com.github.caelis.arse.android.Applicator;
import com.github.caelis.arse.android.Arse;
import com.github.caelis.arse.android.R;

import org.reactivestreams.Publisher;

import java.util.HashSet;
import java.util.Set;

import androidx.core.view.ViewCompat;
import io.reactivex.Flowable;

final class Companion implements View.OnAttachStateChangeListener {

    final Arse arse;
    final View view;
    private Set<Application> applications;

    Companion(Arse arse, View view) {
        this.arse = arse;
        this.view = view;
        init();
    }

    private void init() {
        view.addOnAttachStateChangeListener(this);
    }

    Disposable apply(
            Applicator applicator,
            Publisher data) {
        // TODO: Prevent duplicate application
        StreamApplication streamApplication = new StreamApplication(
                this, applicator, Flowable.fromPublisher(data));
        addApplicationAndSetActivation(streamApplication);
        return streamApplication;
    }

    Disposable apply(
            Applicator applicator,
            Object data) {
        // TODO: Prevent duplicate application
        DataApplication dataApplication = new DataApplication(this, applicator, data);
        addApplicationAndSetActivation(dataApplication);
        return dataApplication;
    }

    private void setAttachState(boolean attached) {
        // need to update all applications
        final Set<Application> local = this.applications;
        if (local != null) {
            // TODO: We need to handle concurrent modifications!
            for (Application application : local) {
                application.setActive(attached);
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(View view) {
        setAttachState(true);
    }

    @Override
    public void onViewDetachedFromWindow(View view) {
        setAttachState(false);
    }

    static Companion companionFor(Arse arse, View view) {
        final Object tagObject = view.getTag(R.id.violake_companion_id);
        if (tagObject instanceof Companion) {
            return (Companion) tagObject;
        } else {
            Companion newCompanion = new Companion(arse, view);
            view.setTag(R.id.violake_companion_id, newCompanion);
            return newCompanion;
        }
    }

    private void addApplicationAndSetActivation(Application application) {
        Set<Application> local = this.applications;
        if (local == null) {
            local = this.applications = new HashSet<>(5);
        }
        local.add(application);
        application.setActive(isAttached());
    }

    void removeApplication(
            Application application,
            boolean expectToHaveRegistration) {
        final Set<Application> local = this.applications;
        final boolean removed;
        if (local != null) {
            removed = local.remove(application);
        } else {
            removed = false;
        }
        if (expectToHaveRegistration && !removed) {
            // TODO: Output some serious logs!
        }
    }

    private boolean isAttached() {
        return ViewCompat.isAttachedToWindow(view);
    }

}
