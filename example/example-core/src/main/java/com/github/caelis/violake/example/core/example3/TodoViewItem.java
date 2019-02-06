package com.github.caelis.violake.example.core.example3;

import java.util.EnumSet;

import java8.util.Objects;
import java8.util.function.Consumer;

public abstract class TodoViewItem {

    private TodoViewItem() {
    }

    public static final class Task extends TodoViewItem {
        private final String text;
        private final String creationDate;
        private final boolean completed;

        private final EnumSet<Action> availableActions;
        private final Consumer<Action> performAction;

        public Task(
                String text,
                String creationDate,
                boolean completed,
                EnumSet<Action> availableActions,
                Consumer<Action> performAction) {
            this.text = text;
            this.creationDate = creationDate;
            this.completed = completed;
            this.availableActions = availableActions;
            this.performAction = performAction;
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

        public EnumSet<Action> getAvailableActions() {
            return availableActions;
        }

        public Consumer<Action> getPerformAction() {
            return performAction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return completed == task.completed &&
                    Objects.equals(text, task.text) &&
                    Objects.equals(creationDate, task.creationDate) &&
                    Objects.equals(availableActions, task.availableActions) &&
                    Objects.equals(performAction, task.performAction);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text, creationDate, completed, availableActions, performAction);
        }
    }

    public static final class Pending extends TodoViewItem {

        private final String text;

        public Pending(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pending pending = (Pending) o;
            return Objects.equals(text, pending.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text);
        }
    }

    public static final class Adding extends TodoViewItem {
        private final ToDoItem.Adding adding;

        public Adding(ToDoItem.Adding adding) {
            this.adding = adding;
        }

        public ToDoItem.Adding getAdding() {
            return adding;
        }
    }
}
