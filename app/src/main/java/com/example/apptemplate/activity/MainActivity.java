package com.example.apptemplate.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.apptemplate.R;

import java.util.ArrayList;

/**
 * Created by Marco on 10/11/2015.
 */
public class MainActivity extends AppCompatActivity implements
    NavDrawerFragment.FragmentDrawerListener {

    // Action bar
    private Toolbar toolbar;
    // Navigation bar
    private NavDrawerFragment drawerFragment;
    // Currently selected fragment
    private int currentFragment = 0;
    private Fragment selectedFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Action bar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Navigation Drawer
        drawerFragment = (NavDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(
                R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id.DrawerLayout),
                toolbar);
        drawerFragment.setDrawerListener(this);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        String title = getString(R.string.app_name);
        switch (position) {
            case 0: // Home fragment
                selectedFragment = HomeFragment.newInstance();
                title = getString(R.string.drawer_item_home);
                break;
            case 1: // Other fragment
                selectedFragment = OtherFragment.newInstance();
                title = getString(R.string.drawer_item_other);
                break;
            default:
                selectedFragment = HomeFragment.newInstance();
                title = getString(R.string.drawer_item_home);
                break;
        }
        if (selectedFragment != null) {
            currentFragment = position;
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_body, selectedFragment)
                    .commit();
            getSupportActionBar().setTitle(title);
        }
    }
}
