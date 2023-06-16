package com.example.aic601project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String IP = "temp";

    private PatientList patientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        patientList = new PatientList(IP);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        ((BottomNavigationView) (findViewById(R.id.main_bottom_navigation_view))).setOnNavigationItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.MainPage1:
                            // Switch to LoginScreenAdmin.java
                            // Replace the current fragment with LoginScreenAdmin
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.main_frameLayout, new LoginAdmin())
                                    .commit();
                            return true;

                        case R.id.MainPage2:
                            // Switch to LoginScreenPhysician.java
                            // Replace the current fragment with LoginScreenPhysician
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.main_frameLayout, new LoginPhysician())
                                    .commit();
                            return true;

                        case R.id.MainPage3:
                            // Switch to LoginScreenUser.java
                            // Replace the current fragment with LoginScreenUser.java
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.main_frameLayout, new LoginUser())
                                    .commit();
                            return true;

                    }
                    return false;
                });

        // Set the initial fragment when the activity is created
        loadFragment(new LoginAdmin());
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_frameLayout, fragment)
                .commit();
    }

    // getter for the IP address
    public static String getIP() {
        return IP;
    }
}