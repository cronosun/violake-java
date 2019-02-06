package com.github.caelis.violake.example.core.example3;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.processors.BehaviorProcessor;

public class ToDoList {

    private int itemCounter = 0;
    private final List<ToDoItem> itemsSrc = new ArrayList<>();
    private final BehaviorProcessor<List<ToDoItem>> items =
            BehaviorProcessor.createDefault(itemsSrc);

    public void addTask(@Nullable ItemId afterId, String text) {
        final ToDoItem.Task task =
                new ToDoItem.Task(nextId(), text, System.currentTimeMillis(), false);
        addTask(afterId, task);
    }

    private void addTask(@Nullable ItemId afterId, ToDoItem.Task task) {
        insertAfter(afterId, task);
    }

    private void addAdding(@Nullable ItemId afterId) {
        BehaviorProcessor<String> text = BehaviorProcessor.createDefault("");
        final ItemId thisId = nextId();
        insertAfter(afterId, new ToDoItem.Adding(thisId, text, text::onNext,
                text.map(s -> !s.isEmpty()),
                () -> {
                    addTask(thisId, text.getValue());
                    removeById(thisId);
                },
                () -> removeById(thisId)));
    }

    private void completeItem(ItemId id) {
        int index = indexOf(id);
        if (index != -1) {
            ToDoItem maybeTask = itemsSrc.get(index);
            if (maybeTask instanceof ToDoItem.Task) {
                ToDoItem.Task task = (ToDoItem.Task) maybeTask;
                task = task.withCompleted(true);
                itemsSrc.set(index, task);
                emitChange();
            }
        }
    }

    private int indexOf(ItemId id) {
        int index = 0;
        boolean found = false;
        if (id != null) {
            for (ToDoItem candidate : this.itemsSrc) {
                if (candidate.getId().equals(id)) {
                    found = true;
                    break;
                }
                index++;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    private void removeById(ItemId id) {
        int index = indexOf(id);
        if (index != -1) {
            this.itemsSrc.remove(index);
            emitChange();
        }
    }

    private void insertAfter(@Nullable ItemId id, ToDoItem item) {
        if (id != null) {
            int index = indexOf(id);
            if (index != -1) {
                this.itemsSrc.add(index + 1, item);
            } else {
                this.itemsSrc.add(0, item);
            }
        } else {
            this.itemsSrc.add(0, item);
        }
        emitChange();
    }

    private void emitChange() {
        setItems(this.itemsSrc);
    }

    private void setItems(List<ToDoItem> items) {
        this.items.onNext(items);
    }

    private ItemId nextId() {
        return new ItemId(":id" + itemCounter++);
    }

    public Publisher<List<TodoViewItem>> viewItems() {
        return items.map(toDoItems -> {
            List<TodoViewItem> newList = new ArrayList<>(toDoItems.size());
            for (ToDoItem item : toDoItems) {
                newList.add(toViewItem(item));
            }
            return newList;
        });
    }

    private TodoViewItem toViewItem(ToDoItem item) {
        if (item instanceof ToDoItem.Task) {
            ToDoItem.Task task = (ToDoItem.Task) item;
            EnumSet<Action> availableActions = task.isCompleted() ? EnumSet.of(Action.ADD) : EnumSet.of(Action.ADD, Action.DELETE, Action.COMPLETE);
            return new TodoViewItem.Task(task.getText(),
                    "TODO" + task.getCreationTime(),
                    task.isCompleted(),
                    availableActions,
                    action -> {
                        switch (action) {
                            case ADD:
                                addAdding(task.getId());
                                break;
                            case DELETE:
                                removeById(task.getId());
                                break;
                            case COMPLETE:
                                completeItem(task.getId());
                                break;
                        }
                    });
        } else if (item instanceof ToDoItem.PendingAdding) {
            return new TodoViewItem.Pending("Adding item...");
        } else if (item instanceof ToDoItem.Adding) {
            return new TodoViewItem.Adding((ToDoItem.Adding) item);
        }
        throw new RuntimeException("Unknown: " + item);
    }
}
