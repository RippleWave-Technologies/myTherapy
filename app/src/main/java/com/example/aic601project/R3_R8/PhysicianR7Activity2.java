package com.example.aic601project.R3_R8;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;

import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

public class PhysicianR7Activity2 extends AppCompatActivity {

    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r7_2);
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        toolbar = findViewById(R.id.topAppBar); // Update the ID to topAppBar
        setupToolbarWithBackButton();

        // Retrieve the data from the Intent extras
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String name = intent.getStringExtra("name");
        String time = intent.getStringExtra("time");

        // Use the retrieved data to populate the views in the InformationActivity
        TextInputLayout dateTextView = findViewById(R.id.dateTxt);
        TextInputLayout nameTextView = findViewById(R.id.patientTxt);
        TextInputLayout timeTextView = findViewById(R.id.timeTxt);

        EditText dateEditText = dateTextView.getEditText();
        EditText nameEditText = nameTextView.getEditText();
        EditText timeEditText = timeTextView.getEditText();

        // Disable editing
        if (dateEditText != null) {
            dateEditText.setEnabled(false);
        }

        if (nameEditText != null) {
            nameEditText.setEnabled(false);
        }

        if (timeEditText != null) {
            timeEditText.setEnabled(false);
        }

        dateTextView.getEditText().setText(date);
        nameTextView.getEditText().setText(name);
        timeTextView.getEditText().setText(time);
    }

    // sets up a toolbar where clicking the back button calls onBackPressed()
    private void setupToolbarWithBackButton() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    // overrides the default onBackPressed() function and includes an exit animation
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_slide_in_or_out, R.anim.slide_out_from_top);
    }
}
