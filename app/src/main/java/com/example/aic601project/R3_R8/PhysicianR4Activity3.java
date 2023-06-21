package com.example.aic601project.R3_R8;

import com.example.aic601project.ModelR4Appointment;
import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PhysicianR4Activity3 extends AppCompatActivity {
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r4_3);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        toolbar = findViewById(R.id.physician_r4_topAppBar);
        setupToolbarWithBackButton();


        Intent intent = getIntent();
        ModelR4Appointment appointment = intent.getParcelableExtra("ModelR4Appointment");

        TextInputLayout lastName = findViewById(R.id.physician_r4_appointment_lastName);
        TextInputLayout actions = findViewById(R.id.physician_r4_appointment_actions);
        TextInputLayout date = findViewById(R.id.physician_r4_appointment_date);
        TextInputLayout time = findViewById(R.id.physician_r4_appointment_time);

        lastName.getEditText().setText(appointment.getLastName());
        actions.getEditText().setText(appointment.getService());
        String[] arrOfStr = appointment.getDate().split(" ");
        date.getEditText().setText(arrOfStr[0]);
        time.getEditText().setText(arrOfStr[1].substring(0,5));
    }

    // sets up a toolbar where clicking the back button calls onBackPressed()
    private void setupToolbarWithBackButton() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    // overrides the default onBackPressed() function and includes an exit animation
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_slide_in_or_out, R.anim.slide_out_from_top);
    }
}