package com.example.aic601project.R3_R8;

import com.example.aic601project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class PhysicianMainActivity extends AppCompatActivity {
    String afm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_main);

        Intent intent = getIntent();
        afm = intent.getStringExtra("AFM");

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        ((BottomNavigationView) (findViewById(R.id.physician_bottom_navigation_view)))
                .setOnNavigationItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.PhysicianPage1:
                            // Switch to PhysicianFragment1.java
                            // Replace the current fragment with PhysicianFragment1
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.physician_frameLayout, new PhysicianFragment1())
                                    .commit();
                            return true;

                        case R.id.PhysicianPage2:
                            // Switch to PhysicianFragment2R6.java
                            // Replace the current fragment with PhysicianFragment2R6
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.physician_frameLayout, new PhysicianFragment2R6())
                                    .commit();
                            return true;

                        case R.id.PhysicianPage3:
                            // Switch to PhysicianFragment2R5.java
                            // Replace the current fragment with PhysicianFragment2R5
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.physician_frameLayout, new PhysicianFragment3R5())
                                    .commit();
                            return true;
                    }
                    return false;
                });

        // Set the initial fragment when the activity is created
        loadFragment(new PhysicianFragment1());
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.physician_frameLayout, fragment)
                .commit();
    }

    public String getAfm() {
        return afm;
    }
}