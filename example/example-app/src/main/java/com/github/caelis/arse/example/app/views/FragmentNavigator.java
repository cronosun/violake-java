package com.github.caelis.arse.example.app.views;

import com.github.caelis.arse.example.app.R;
import com.github.caelis.arse.example.app.views.exampe3.Example3Fragment;
import com.github.caelis.arse.example.app.views.example1.Example1Fragment;
import com.github.caelis.arse.example.app.views.example2.Example2Fragment;
import com.github.caelis.arse.example.core.MenuEntriesProvider;
import com.github.caelis.arse.example.core.MenuId;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public final class FragmentNavigator {

    private static final Map<MenuId, Supplier<Fragment>> fragments;
    public static MenuId MAIN = new MenuId("main_view");

    static {
        Map<MenuId, Supplier<Fragment>> local = new HashMap<>();

        local.put(MAIN, MenuFragment::new);
        local.put(MenuEntriesProvider.EXAMPLE1, Example1Fragment::new);
        local.put(MenuEntriesProvider.EXAMPLE2, Example2Fragment::new);
        local.put(MenuEntriesProvider.EXAMPLE3, Example3Fragment::new);

        fragments = local;
    }

    private FragmentNavigator() {
    }

    public static void showMenu(FragmentManager fragmentManager, MenuId menuId) {
        final Supplier<Fragment> fragmentSupplier = fragments.get(menuId);
        if (fragmentSupplier == null) {
            throw new NullPointerException("No fragment for menu " + menuId + " configured");
        }
        final Fragment fragment = fragmentSupplier.get();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
