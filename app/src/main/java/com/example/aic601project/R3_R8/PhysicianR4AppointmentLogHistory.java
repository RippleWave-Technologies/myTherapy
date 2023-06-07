package com.example.aic601project.R3_R8;

import java.util.ArrayList;
import java.util.List;

import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PhysicianR4AppointmentLogHistory extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private RecyclerView recyclerView;
    private List<JavaTempPhysicianR5Patients> patients;
    private PhysicianR4Adapter patientsAdapter;
    private PhysicianR4Adapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r4_appointment_log_history);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        toolbar = findViewById(R.id.physician_r4_topAppBar);
        setupToolbarWithBackButton();

        patients = new ArrayList<>();

        patients.add(new JavaTempPhysicianR5Patients("22/6/454"));
        patients.add(new JavaTempPhysicianR5Patients("22/6/454"));
        patients.add(new JavaTempPhysicianR5Patients("22/6/454"));
        patients.add(new JavaTempPhysicianR5Patients("22/6/454"));
        patients.add(new JavaTempPhysicianR5Patients("22/6/454"));
        patients.add(new JavaTempPhysicianR5Patients("22/6/454"));

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
            Intent intent = new Intent(getApplicationContext(), AppointmentInformationView.class);
            startActivity(intent);
            // requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom,
            // R.anim.no_slide_in_or_out);
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