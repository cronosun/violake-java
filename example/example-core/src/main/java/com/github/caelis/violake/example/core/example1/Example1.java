package com.github.caelis.violake.example.core.example1;

import org.reactivestreams.Publisher;

import io.reactivex.processors.BehaviorProcessor;

/***
 * Controller for example 1.
 */
public final class Example1 {

    private final BehaviorProcessor<Integer> clickCount = BehaviorProcessor.createDefault(0);

    public void incrementClickCount() {
        clickCount.onNext(clickCount.getValue() + 1);
    }

    public Publisher<String> message() {
        return clickCount.map(count -> "You clicked the button " + count + " times!");
    }

    public Publisher<Boolean> isEvenClickCount() {
        return clickCount.map(this::isEven);
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
