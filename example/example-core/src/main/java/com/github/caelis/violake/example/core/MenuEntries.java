package com.github.caelis.violake.example.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

public final class MenuEntries {
    private final List<MenuEntry> menuEntryList;

    private MenuEntries(List<MenuEntry> menuEntryList) {
        this.menuEntryList = menuEntryList;
    }

    public List<MenuEntry> getMenuEntryList() {
        return menuEntryList;
    }

    public static final class Builder {
        @Nullable
        private List<MenuEntry> menuEntryList;

        public Builder add(MenuEntry entry) {
            if (menuEntryList == null) {
                menuEntryList = new ArrayList<>();
            }
            menuEntryList.add(entry);
            return this;
        }

        public MenuEntries build() {
            List<MenuEntry> moved = this.menuEntryList;
            this.menuEntryList = null;
            return new MenuEntries(moved == null ? Collections.emptyList() :
                    Collections.unmodifiableList(moved));
        }
    }
}
