package com.example.aic601project.R1_R2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.aic601project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        // sets the status and navigation bar color
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        // Has to be changed due to deprecated API usage
        ((BottomNavigationView)(findViewById(R.id.admin_bottom_navigation_view))).setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int selectedItemId = item.getItemId();
                if (selectedItemId == R.id.AdminPage1){
                    findViewById(R.id.adminR1_button_users).setVisibility(View.VISIBLE);
                } else if (selectedItemId == R.id.AdminPage2) {
                    findViewById(R.id.adminR1_button_users).setVisibility(View.INVISIBLE);
                }
                return true;
            }
        });
    }

    /* onClick for add_button
    * case R.id.AdminPage1 it starts AdminR1Activity
    * case R.id.AdminPage2 it starts ... */
    public void addToList (View v){
        int selectedItemId = ((BottomNavigationView)(findViewById(R.id.admin_bottom_navigation_view))).getSelectedItemId();
        switch (selectedItemId){
            case R.id.AdminPage1:
                startActivity(new Intent(this, AdminR1Activity.class));
                overridePendingTransition(R.anim.slide_in_from_bottom,  R.anim.no_slide_in_or_out);
                break;
            case R.id.AdminPage2:
                /* startActivity(new Intent(this, AdminR2Activity.class));
                overridePendingTransition(R.anim.slide_in_from_bottom,  R.anim.no_slide_in_or_out); */
                break;
        }
    }

    // temporary onClick for a show_user_details_button
    public void viewUserData (View v){
        Intent i = new Intent(this, AdminR1Activity.class);
        i.putExtra("key", 2);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_from_bottom,  R.anim.no_slide_in_or_out);
    }


}