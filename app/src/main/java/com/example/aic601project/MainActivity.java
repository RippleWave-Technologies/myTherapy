package com.example.aic601project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aic601project.R1_R2.AdminMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sets the status and navigation bar color
        getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
    }

    public void goToOtherActivity (View v){
        int selectedItemId = ((BottomNavigationView)(findViewById(R.id.main_bottom_navigation_view))).getSelectedItemId();
        switch (selectedItemId){
            case R.id.MainPage1:
                startActivity(new Intent(this, AdminMainActivity.class));
                break;
            case R.id.MainPage2:
                // startActivity(new Intent(this, PhysioMainActivity.class));
                Toast.makeText(MainActivity.this, "Παιδιά δουλεύουμε τώρα :) .MainPage2", Toast.LENGTH_LONG).show();
                break;
            case R.id.MainPage3:
                // startActivity(new Intent(this, UserMainActivity.class));
                Toast.makeText(MainActivity.this, "Παιδιά δουλεύουμε τώρα :) .MainPage3", Toast.LENGTH_LONG).show();
                break;
        }
    }
}