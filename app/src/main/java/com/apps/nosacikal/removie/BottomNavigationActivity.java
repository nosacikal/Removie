package com.apps.nosacikal.removie;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

/*
 * Selasa 18 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class BottomNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MovieListFragment()).commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new MovieListFragment();
                            break;

                        case R.id.nav_daily:
                            selectedFragment = new MovieListFragment();
                            break;

                        case R.id.nav_gallery:
                            selectedFragment = new MovieListFragment();
                            break;
//
//                        case R.id.nav_music_video:
//                            selectedFragment = new MusicVideoFragment();
//                            break;
//
//                        case R.id.nav_profile:
//                            selectedFragment = new ProfileFragment();
//                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}
