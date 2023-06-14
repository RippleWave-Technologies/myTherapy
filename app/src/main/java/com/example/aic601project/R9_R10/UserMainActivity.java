package com.example.aic601project.R9_R10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.aic601project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserMainActivity extends AppCompatActivity {
    String amka;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        Intent intent = getIntent();
        amka = intent.getStringExtra("AMKA");

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

        // onClick listener for user_imageButton_notifications
        findViewById(R.id.user_imageButton_notifications).setOnClickListener(v -> launchUserR9NotificationsActivity());
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.user_frameLayout, fragment)
                .commit();
    }

    // method for user_imageButton_notifications
    private void launchUserR9NotificationsActivity() {
        startActivity(new Intent(this, UserR9NotificationsActivity.class));
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }

    public String getAmka() {
        return amka;
    }
}