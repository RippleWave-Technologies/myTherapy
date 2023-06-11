package com.example.aic601project;

import com.example.aic601project.R1_R2.AdminMainActivity;
import com.example.aic601project.R3_R8.PhysicianMainActivity;
import com.example.aic601project.R9_R10.UserMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String IP = "temp";

    private PatientList patientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        patientList = new PatientList(IP);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        getWindow()
                .setNavigationBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

    }

    // onClick for main_button_goToActivity Button
    public void goToOtherActivity(View v) {
        int selectedItemId = ((BottomNavigationView) (findViewById(R.id.main_bottom_navigation_view)))
                .getSelectedItemId();
        if (selectedItemId == R.id.MainPage1) {
            startActivity(new Intent(this, AdminMainActivity.class));
        } else if (selectedItemId == R.id.MainPage2) {
            startActivity(new Intent(this, PhysicianMainActivity.class));
        } else if (selectedItemId == R.id.MainPage3) {
            startActivity(new Intent(this, UserMainActivity.class));
        }
    }

    // getter for the IP address
    public static String getIP() {
        return IP;
    }
}