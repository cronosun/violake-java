package com.github.caelis.violake.example.core.example3;

import org.reactivestreams.Publisher;

import java8.util.function.Consumer;

public abstract class ToDoItem {

    private ToDoItem(ItemId id) {
        this.id = id;
    }

    private final ItemId id;

    public ItemId getId() {
        return id;
    }

    public static final class Task extends ToDoItem {
        private final String text;
        private final long creationTime;
        private final boolean completed;

        public Task(ItemId id, String text, long creationTime, boolean completed) {
            super(id);
            this.text = text;
            this.creationTime = creationTime;
            this.completed = completed;
        }

        public String getText() {
            return text;
        }

        public long getCreationTime() {
            return creationTime;
        }

        public boolean isCompleted() {
            return completed;
        }

        public Task withCompleted(boolean completed) {
            return new Task(getId(), text, creationTime, completed);
        }
    }

    public static final class PendingAdding extends ToDoItem {
        private PendingAdding(ItemId id) {
            super(id);
        }
    }

    public static final class Adding extends ToDoItem {
        private final Publisher<String> text;
        private final Consumer<String> textInput;
        private final Publisher<Boolean> canAdd;
        private final Runnable add;
        private final Runnable cancel;

        public Adding(
                ItemId id,
                Publisher<String> text,
                Consumer<String> textInput,
                Publisher<Boolean> canAdd,
                Runnable add,
                Runnable cancel) {
            super(id);
            this.text = text;
            this.textInput = textInput;
            this.canAdd = canAdd;
            this.add = add;
            this.cancel = cancel;
        }

        public Publisher<String> getText() {
            return text;
        }

        public Consumer<String> getTextInput() {
            return textInput;
        }

        public Publisher<Boolean> getCanAdd() {
            return canAdd;
        }

        public Runnable getAdd() {
            return add;
        }

        public Runnable getCancel() {
            return cancel;
        }
    }
}
