package com.github.cronosun.violake.android.std;

import android.view.View;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.R;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.Map;

import androidx.core.view.ViewCompat;
import io.reactivex.Flowable;

final class Companion implements View.OnAttachStateChangeListener {

    final Violake violake;
    final View view;
    private Map<Object, SingleApplication> applications;

    Companion(Violake violake, View view) {
        this.violake = violake;
        this.view = view;
        init();
    }

    private void init() {
        view.addOnAttachStateChangeListener(this);
    }

    @SuppressWarnings("unchecked")
    Disposable apply(
            Applicator applicator,
            Publisher data) {
        StreamApplication streamApplication = new StreamApplication(
                this, applicator, Flowable.fromPublisher(data));
        return addApplicationAndSetActivation(streamApplication);
    }

    Disposable apply(
            Applicator applicator,
            Object data) {
        DataApplication dataApplication = new DataApplication(this, applicator, data);
        return addApplicationAndSetActivation(dataApplication);
    }

    private void setAttachState(boolean attached) {
        // need to update all applications
        final Map<Object, SingleApplication> local = this.applications;
        if (local != null) {
            // TODO: We need to handle concurrent modifications!
            for (SingleApplication application : local.values()) {
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

    static Companion companionFor(Violake violake, View view) {
        final Object tagObject = view.getTag(R.id.violake_companion_id);
        if (tagObject instanceof Companion) {
            return (Companion) tagObject;
        } else {
            Companion newCompanion = new Companion(violake, view);
            view.setTag(R.id.violake_companion_id, newCompanion);
            return newCompanion;
        }
    }

    private Disposable addApplicationAndSetActivation(SingleApplication application) {
        Map<Object, SingleApplication> local = this.applications;
        if (local == null) {
            local = this.applications = new HashMap<>(5);
        }
        // we do not add duplicate applications
        final SingleApplication existingApplication = local.get(application.duplicate());
        if (existingApplication != null) {
            // nope, it's a duplicate
            return existingApplication;
        } else {
            local.put(application.duplicate(), application);
            application.setActive(isAttached());
            return application;
        }
    }

    void removeApplication(
            SingleApplication application,
            boolean expectToHaveRegistration) {
        final Map<Object, SingleApplication> local = this.applications;
        final boolean removed;
        if (local != null) {
            removed = local.remove(application.duplicate()) != null;
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
