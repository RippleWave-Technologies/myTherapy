package com.example.aic601project.R9_R10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.aic601project.R;
import com.example.aic601project.R3_R8.PhysicianFragment1;
import com.example.aic601project.R3_R8.PhysicianFragment2R6;
import com.example.aic601project.R3_R8.PhysicianFragment3R5;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));


        ((BottomNavigationView)(findViewById(R.id.user_bottom_navigation_view))).setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.UserPage1:
                    // Switch to UserFragment1R9.java
                    // Replace the current fragment with UserFragment1R9
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.user_frameLayout, new UserFragment1R9())
                            .commit();
                    return true;

                case R.id.UserPage2:
                    // Switch to UserFragment2R10.java
                    // Replace the current fragment with UserFragment2R10
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.user_frameLayout, new UserFragment2R10())
                            .commit();
                    return true;
            }
            return false;
        });

        // Set the initial fragment when the activity is created
        loadFragment(new UserFragment1R9());
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.user_frameLayout, fragment)
                .commit();
    }
}