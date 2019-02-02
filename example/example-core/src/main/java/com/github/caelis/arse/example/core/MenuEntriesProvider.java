package com.github.caelis.arse.example.core;

public final class MenuEntriesProvider {

    private MenuEntriesProvider() {
    }

    public static final MenuId EXAMPLE1 = new MenuId("example1");
    public static final MenuId EXAMPLE2 = new MenuId("example2");

    public static MenuEntries createMenuEntries(MenuChangeHandler handler) {
        MenuEntries.Builder builder = new MenuEntries.Builder();
        builder.add(new MenuEntry("Example 1", "Simple demonstration with a button " +
                "and two reactive labels.",
                () -> handler.goToMenu(EXAMPLE1)));
        builder.add(new MenuEntry("Example 2", "Simple demonstration with a button, " +
                "a label and a check box connected to each other using streams.",
                () -> handler.goToMenu(EXAMPLE2)));
        return builder.build();
    }

    public interface MenuChangeHandler {
        void goToMenu(MenuId menuId);
    }
}
