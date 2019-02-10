package com.github.caelis.violake.example.core.example5;

import org.reactivestreams.Publisher;

import java.util.Optional;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;

public class Example5Model {

    private final BehaviorProcessor<Optional<String>> pending =
            BehaviorProcessor.createDefault(Optional.empty());
    private final BehaviorProcessor<Selection> selection =
            BehaviorProcessor.createDefault(Selection.IMAGE_AND_ACTION);


    public void setSelection(Selection selection) {
        this.selection.onNext(selection);
    }

    // TODO: remove?
    public Publisher<Selection> getSelection() {
        return selection;
    }

    public Publisher<Optional<SwitchModel>> getSwitchData() {
        return pending.switchMap(pending -> {
            if (pending.isPresent()) {
                // some progress is going on
                return Flowable.just(Optional.of(new SwitchModel.Progress(pending.get())));
            } else {
                // no progress
                return selection.map(selection -> {
                    switch (selection) {
                        case IMAGE_AND_ACTION:
                            return Optional.of(imageAndAction());
                        case WEB_ACTION:
                            return Optional.of(webAction());

                    }
                    throw new IllegalArgumentException("Unknown selection: " + selection);
                });
            }
        });
    }

    private SwitchModel.ImageAndAction imageAndAction() {
        // TODO: Also make sure we can remove that again..
        return new SwitchModel.ImageAndAction(() ->
                pending.onNext(Optional.of("Performing image action...")));
    }

    private SwitchModel.WebAction webAction() {
        return new SwitchModel.WebAction();
    }
}
