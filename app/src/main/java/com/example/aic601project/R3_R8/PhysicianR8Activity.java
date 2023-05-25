package com.example.aic601project.R3_R8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;

public class PhysicianR8Activity extends AppCompatActivity {
    // toolbar - physician_r8_topAppBar
    private MaterialToolbar toolbar;
    String[] items = {"Θεραπεία1", "Θεραπεία2", "Θεραπεία3", "Θεραπεία4", "Θεραπεία5"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r8);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        toolbar = findViewById(R.id.physician_r8_topAppBar);
        setupToolbarWithBackButton();

        autoCompleteTxt = findViewById(R.id.physician_r8_autoCompleteTextView_actions);

        adapterItems = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.physician_r8_app_bar_layout, menu);
        return true;
    }
}

// TODO
// the physician_r8_autoCompleteTextView_actions should be populated with the services on the database
// clicking on physician_r8_Button, an visit is confirmed and the appropriate changes are made to the database