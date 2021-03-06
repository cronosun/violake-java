package com.github.cronosun.violake.android.std;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.github.cronosun.violake.android.Applicator;
import com.github.cronosun.violake.android.Violake;
import com.github.cronosun.violake.core.Disposable;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

public class StdViolake implements Violake {

    private final long mainThreadId;
    private final Handler mainHandler;

    public StdViolake() {
        Looper mainLooper = Looper.getMainLooper();
        mainThreadId = mainLooper.getThread().getId();
        mainHandler = new Handler(mainLooper);
    }

    @Override
    public <TTarget extends View, TData> Disposable apply(
            Applicator<? super TTarget, TData> applicator,
            TTarget target,
            Publisher<? extends TData> stream) {
        return Companion.companionFor(this, target).apply(applicator, stream);
    }

    @Override
    public <TTarget extends View, TData> Disposable apply(
            Applicator<? super TTarget, TData> applicator,
            TTarget target,
            TData data) {
        return Companion.companionFor(this, target).apply(applicator, data);
    }

    @Override
    public Disposable disposableFrom(Runnable runnable) {
        return null;
    }

    @Override
    public Disposable append(Disposable self, Disposable next) {
        if (self == EmptyDisposable.INSTANCE) {
            return next;
        } else if (next == EmptyDisposable.INSTANCE) {
            return self;
        }
        return () -> {
            self.dispose();
            next.dispose();
        };
    }

    @Override
    public Disposable chain(Disposable... disposables) {
        int length = disposables.length;
        if (length == 0) {
            return EmptyDisposable.INSTANCE;
        } else if (length == 1) {
            return disposables[0];
        } else if (length == 2) {
            return append(disposables[0], disposables[1]);
        } else {
            final List<Disposable> listOfDisposables = new ArrayList<>(length);
            for (Disposable disposable : disposables) {
                if (disposable != EmptyDisposable.INSTANCE) {
                    listOfDisposables.add(disposable);
                }
            }
            final int listOfDisposableLength = listOfDisposables.size();
            if (listOfDisposableLength == 0) {
                return EmptyDisposable.INSTANCE;
            } else if (listOfDisposableLength == 1) {
                return listOfDisposables.get(0);
            } else {
                return () -> {
                    for (Disposable entry : listOfDisposables) {
                        entry.dispose();
                    }
                };
            }
        }
    }

    @Override
    public Disposable emptyDisposable() {
        return EmptyDisposable.INSTANCE;
    }

    @Override
    public void post(Runnable runnable) {
        if (Thread.currentThread().getId() == mainThreadId) {
            runnable.run();
        } else {
            mainHandler.post(runnable);
        }
    }

    @Override
    public void postDelayed(int delayMs, Runnable runnable) {
        mainHandler.postDelayed(runnable, delayMs);
    }

    @Override
    public boolean isDebugMode() {
        return true;
    }


    @Override
    public <TTarget extends View, TData> void traceOperation(Applicator<? super TTarget, TData> applicator, TTarget tTarget, TData data, String text) {

    }

    @Override
    public <TTarget extends View, TData> void traceOperation(Applicator<? super TTarget, TData> applicator, TTarget tTarget, String text) {

    }

    @Override
    public void reportStreamError(View target, Publisher<?> stream) {

    }
}
