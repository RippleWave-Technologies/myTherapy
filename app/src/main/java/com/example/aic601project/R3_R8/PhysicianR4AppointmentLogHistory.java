package com.example.aic601project.R3_R8;

import java.util.ArrayList;
import java.util.List;

import com.example.aic601project.Appointment;
import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PhysicianR4AppointmentLogHistory extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private RecyclerView recyclerView;
    private List<JavaTempPhysicianR5Patients> patients;
    private PhysicianR4Adapter patientsAdapter;
    private PhysicianR4Adapter.RecyclerViewClickListener listener;

    private ArrayList<Appointment> completedAppointments ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r4_appointment_log_history);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        toolbar = findViewById(R.id.physician_r4_topAppBar);
        setupToolbarWithBackButton();


        Intent intent = getIntent();
        completedAppointments = intent.getParcelableExtra("appointments");

        patients = new ArrayList<>();

        for (Appointment app : completedAppointments) {
            patients.add(new JavaTempPhysicianR5Patients(app.getDate() + "                                " + app.getService()));
        }

        recyclerView = findViewById(R.id.physician_r4_appointmentsHistory);
        recyclerView.setHasFixedSize(true);

        setOnClickListener();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        patientsAdapter = new PhysicianR4Adapter(patients, listener);
        recyclerView.setAdapter(patientsAdapter);
        recyclerView.requestFocus();
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), R4AppointmentInformationView.class);
            startActivity(intent);
            intent.putExtra("appointment", (Parcelable) completedAppointments.get(position));
        };
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