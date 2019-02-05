package com.github.caelis.violake.example.core.example3;

import javax.annotation.Nullable;

import java8.util.Objects;

public abstract class ToDoItem {

    private ToDoItem() {
    }

    public static final class Task extends ToDoItem {
        private final String text;
        private final String creationDate;
        private final boolean completed;
        @Nullable
        private final Runnable delete;
        @Nullable
        private final Runnable complete;

        public Task(
                String text,
                String creationDate,
                boolean completed,
                @Nullable Runnable delete,
                @Nullable Runnable complete) {
            this.text = text;
            this.creationDate = creationDate;
            this.completed = completed;
            this.delete = delete;
            this.complete = complete;
        }

        public String getText() {
            return text;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public boolean isCompleted() {
            return completed;
        }

        @Nullable
        public Runnable getDelete() {
            return delete;
        }

        @Nullable
        public Runnable getComplete() {
            return complete;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return completed == task.completed &&
                    Objects.equals(text, task.text) &&
                    Objects.equals(creationDate, task.creationDate) &&
                    Objects.equals(delete, task.delete) &&
                    Objects.equals(complete, task.complete);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text, creationDate, completed, delete, complete);
        }
    }

    public static final class Pending extends ToDoItem {

        public static final Pending INSTANCE = new Pending();

        private Pending() {
        }
    }
}
