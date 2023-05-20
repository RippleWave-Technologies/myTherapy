package com.example.aic601project.R1_R2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.aic601project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));


        ((BottomNavigationView)(findViewById(R.id.admin_bottom_navigation_view))).setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.AdminPage1:
                    // Switch to AdminFragment1.java
                    // Replace the current fragment with AdminFragment1
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.admin_frameLayout, new AdminFragment1())
                            .commit();
                    return true;

                case R.id.AdminPage2:
                    // Switch to AdminFragment2.java
                    // Replace the current fragment with AdminFragment2
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.admin_frameLayout, new AdminFragment2())
                            .commit();
                    return true;
            }
            return false;
        });

        // Set the initial fragment when the activity is created
        loadFragment(new AdminFragment1());
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.admin_frameLayout, fragment)
                .commit();
    }
}