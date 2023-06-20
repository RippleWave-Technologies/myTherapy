package com.example.aic601project.R3_R8;

import com.example.aic601project.Appointment;
import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class R4AppointmentInformationView extends AppCompatActivity {
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r4_appointment_information_view);


        Intent intent = getIntent();
        Appointment appointment = intent.getParcelableExtra("appointment");

        TextView lastName = findViewById(R.id.physician_r4_appointment_lastName);
        TextView actions = findViewById(R.id.physician_r4_textInputLayout_actions);
        TextView date = findViewById(R.id.physician_r4_textInputLayout_date);
        TextView time = findViewById(R.id.physician_r4_textInputLayout_time);

        lastName.setText(appointment.getLastName());
        actions.setText(appointment.getService());
        String[] arrOfStr = appointment.getDate().split(" ");
        date.setText(arrOfStr[0]);
        time.setText(arrOfStr[1]);


        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        toolbar = findViewById(R.id.physician_r4_topAppBar);
        setupToolbarWithBackButton();

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