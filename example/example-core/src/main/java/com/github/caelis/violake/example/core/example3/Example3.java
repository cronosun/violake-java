package com.github.caelis.violake.example.core.example3;

import org.reactivestreams.Publisher;

import java.util.List;

public final class Example3 {

    public Example3() {
        addDummyData();
    }

    private final ToDoList toDoList = new ToDoList();

    public Publisher<List<TodoViewItem>> getItems() {
        return toDoList.viewItems();
    }

    void addDummyData() {
        toDoList.addTask(null, "Sample item");
    }


}
