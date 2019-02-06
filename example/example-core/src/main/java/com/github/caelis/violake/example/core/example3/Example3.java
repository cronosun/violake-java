package com.github.caelis.violake.example.core.example3;

import com.github.caelis.violake.core.ViolakeCore;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.processors.BehaviorProcessor;

public final class Example3 {

    public Example3() {
        addDummyData();
    }

    private final BehaviorProcessor<List<TodoViewItem>> items =
            BehaviorProcessor.createDefault(Collections.emptyList());

    public Publisher<List<TodoViewItem>> getItems() {
        return items;
    }

    Timer timer = new Timer();

    void addDummyData() {
        List<TodoViewItem> items = new ArrayList<>();
        items.add(new TodoViewItem.Task("Do something today", "Now", false, EnumSet.of(Action.ADD), (action) -> {
        }));
        items.add(new TodoViewItem.Pending("Adding item..."));
        items.add(new TodoViewItem.Task("Another TODO Item", "2019-02-25", false, EnumSet.of(Action.DELETE), (action) -> {
        }));
        this.items.onNext(items);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ViolakeCore.get().post(() -> {
                    items.add(new TodoViewItem.Task("Do something today", "Now", false, EnumSet.of(Action.ADD), (action) -> {
                    }));
                    Example3.this.items.onNext(items);
                });
            }
        }, 1000, 1000);
    }

    private void addNewItem(String text) {

    }

    private static class Item {
        private final String text;
        private final long creationDate;
        private final boolean completed;

        private Item(String text, long creationDate, boolean completed) {
            this.text = text;
            this.creationDate = creationDate;
            this.completed = completed;
        }
    }
}
