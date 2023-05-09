package com.example.aic601project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sets the status and navigation bar color
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

    }

    /* onClick for add_button
    * case R.id.page1 it starts AddPhysioActivity
    * case R.id.page2 it starts ... */
    public void addToList (View v){

        int selectedItemId = ((BottomNavigationView)(findViewById(R.id.bottom_navigation_view))).getSelectedItemId();

        switch (selectedItemId){
            case R.id.page1:
                startActivity(new Intent(this, AddPhysioActivity.class));
                overridePendingTransition(R.anim.slide_in_from_bottom,  R.anim.no_slide_in_or_out);
                break;
            case R.id.page2:
                break;
        }
    }


}