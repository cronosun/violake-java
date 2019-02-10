package com.github.caelis.violake.example.core;

public final class MenuEntriesProvider {

    private MenuEntriesProvider() {
    }

    public static final MenuId EXAMPLE1 = new MenuId("example1");
    public static final MenuId EXAMPLE2 = new MenuId("example2");
    public static final MenuId EXAMPLE3 = new MenuId("example3");
    public static final MenuId EXAMPLE4 = new MenuId("example4");
    public static final MenuId EXAMPLE5 = new MenuId("example5");

    public static MenuEntries createMenuEntries(MenuChangeHandler handler) {
        MenuEntries.Builder builder = new MenuEntries.Builder();
        builder.add(new MenuEntry("Ex 1: Clicks", "Simple demonstration with a button, " +
                "a label and an icon (actually two).",
                () -> handler.goToMenu(EXAMPLE1)));
        builder.add(new MenuEntry("Ex 2: Advanced Clicks", "Demonstration " +
                "with a button, " +
                "a label and a check box connected to each other using streams.",
                () -> handler.goToMenu(EXAMPLE2)));
        builder.add(new MenuEntry("Ex 3: TODO List", "Demonstrates a " +
                "ToDo list (NOT YET FINISHED).",
                () -> handler.goToMenu(EXAMPLE3)));
        builder.add(new MenuEntry("Ex 4: Form & Validation",
                "A simple form with validation.",
                () -> handler.goToMenu(EXAMPLE4)));
        builder.add(new MenuEntry("Ex 5: Layout Switching",
                "Demonstrates switching layouts (views).",
                () -> handler.goToMenu(EXAMPLE5)));
        return builder.build();
    }

    public interface MenuChangeHandler {
        void goToMenu(MenuId menuId);
    }
}
