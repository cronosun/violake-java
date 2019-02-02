package com.github.caelis.arse.core;

/**
 * Immutable list of 0-n disposables.
 *
 * // TODO: Do we really need that in core?
 */
public interface DisposableList extends Disposable {
    int length();

    void dispose(int index);

    void disposeAll();

    @Override
    default void dispose() {
        disposeAll();
    }
}
