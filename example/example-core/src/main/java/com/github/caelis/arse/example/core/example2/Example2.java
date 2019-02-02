package com.github.caelis.arse.example.core.example2;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;

/***
 * Controller for example 1.
 */
public final class Example2 {

    private final BehaviorProcessor<Integer> clickCount = BehaviorProcessor.createDefault(0);
    private final BehaviorProcessor<EvenClickCountManipulation> evenClickCountManipulation =
            BehaviorProcessor.createDefault(EvenClickCountManipulation.NotManipulated.INSTANCE);

    public void incrementClickCount() {
        clickCount.onNext(clickCount.getValue() + 1);
    }

    public Publisher<String> message() {
        return Flowable.combineLatest(clickCount, evenClickCountManipulation,
                (count, manipulation) -> {
                    if (manipulation instanceof EvenClickCountManipulation.Manipulated) {
                        final EvenClickCountManipulation.Manipulated manipulated =
                                (EvenClickCountManipulation.Manipulated) manipulation;
                        final boolean isEven = isEven(count);
                        if (manipulated.isEvenClickCount != isEven) {
                            return "Hey, you just manipulated the 'is even' value! Now we're out " +
                                    "of sync. Don't do that! You clicked " + count + " times.";
                        }
                    }

                    return "You clicked the button " + count + " times!";
                });
    }

    public Publisher<Boolean> isEvenClickCount() {
        return clickCount.map(this::isEven);
    }

    public void setEventClickCount(boolean isEvenClickCount) {
        evenClickCountManipulation.onNext(
                new EvenClickCountManipulation.Manipulated(isEvenClickCount));
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    private abstract static class EvenClickCountManipulation {
        private EvenClickCountManipulation() {
        }

        static class NotManipulated extends Example2.EvenClickCountManipulation {
            private NotManipulated() {
            }

            private static final NotManipulated INSTANCE = new NotManipulated();
        }

        /**
         * User manually manipulated the 'is even click count'.
         */
        static class Manipulated extends Example2.EvenClickCountManipulation {
            private final boolean isEvenClickCount;

            Manipulated(boolean isEvenClickCount) {
                this.isEvenClickCount = isEvenClickCount;
            }
        }
    }
}
