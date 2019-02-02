package com.github.caelis.arse.example.app;

import android.os.Bundle;

import com.github.caelis.arse.example.app.views.FragmentNavigator;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentNavigator.showMenu(fragmentManager, FragmentNavigator.MAIN);
    }
}
