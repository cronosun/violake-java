package com.github.caelis.violake.example.core.example3;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.processors.BehaviorProcessor;

public final class Example3 {

    public Example3() {
        addDummyData();
    }

    private final BehaviorProcessor<List<ToDoItem>> items =
            BehaviorProcessor.createDefault(Collections.emptyList());

    public Publisher<List<ToDoItem>> getItems() {
        return items;
    }

    void addDummyData() {
        List<ToDoItem> items = new ArrayList<>();
        items.add(new ToDoItem.Task("Do something today", "Now", false, () -> {
        }, () -> {
        }));
        items.add(ToDoItem.Pending.INSTANCE);
        items.add(new ToDoItem.Task("Another TODO Item", "2019-02-25", true, () -> {
        }, () -> {
        }));
        this.items.onNext(items);
    }
}
