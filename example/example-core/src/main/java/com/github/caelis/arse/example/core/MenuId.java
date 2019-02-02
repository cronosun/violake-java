package com.github.caelis.arse.example.core;

import java8.util.Objects;

public final class MenuId {
    private final String id;

    public MenuId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuId menuId = (MenuId) o;
        return Objects.equals(id, menuId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MenuId{" +
                "id='" + id + '\'' +
                '}';
    }
}
