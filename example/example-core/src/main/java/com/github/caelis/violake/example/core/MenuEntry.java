package com.github.caelis.violake.example.core;

import java8.util.Objects;

public final class MenuEntry {
    private final String title;
    private final String description;
    private final Runnable invoke;

    public MenuEntry(String title, String description, Runnable invoke) {
        this.title = title;
        this.description = description;
        this.invoke = invoke;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuEntry menuEntry = (MenuEntry) o;
        return Objects.equals(title, menuEntry.title) &&
                Objects.equals(description, menuEntry.description) &&
                Objects.equals(invoke, menuEntry.invoke);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void invoke() {
        invoke.run();
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, invoke);
    }

    @Override
    public String toString() {
        return "MenuEntry{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", invoke=" + invoke +
                '}';
    }
}
