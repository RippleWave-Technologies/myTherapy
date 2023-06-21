package com.example.aic601project.R9_R10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;

public class UserR9Activity1 extends AppCompatActivity {
    private MaterialToolbar toolbar;

    private Button tempButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_r9_2);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        toolbar = findViewById(R.id.user_r9notifications_topAppBar);
        setupToolbarWithBackButton();

        //tempButton to check that the info screen works
        tempButton = findViewById(R.id.user_notif_button);
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserR9Activity1.this, UserR9Activity2.class);
                UserR9Activity1.this.startActivity(myIntent);
            }
        });
    }

    // sets up a toolbar where clicking the back button calls onBackPressed()
    private void setupToolbarWithBackButton(){
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    // overrides the default onBackPressed() function and includes an exit animation
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_slide_in_or_out, R.anim.slide_out_from_top);
    }

    /* overrides the onCreateOptionsMenu because by calling setSupportActionBar
     * the menu will be populated with standard system menu items */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_r1_app_bar_layout, menu);
        return true;
    }
}