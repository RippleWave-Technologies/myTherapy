package com.example.aic601project.R3_R8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.aic601project.ModelR4Appointment;
import com.example.aic601project.MainActivity;
import com.example.aic601project.ModelPatient;
import com.example.aic601project.OkHttpHandler;
import com.example.aic601project.R;
import com.example.aic601project.RecyclerViewInterface;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class PhysicianR4Activity2 extends AppCompatActivity implements RecyclerViewInterface  {

    // toolbar - admin_r1_1_topAppBar
    private MaterialToolbar toolbar;
    private ModelPatient patient;
    private ArrayList<ModelR4Appointment> appointments = new ArrayList<>();

    // RecyclerView - used to display the clinics
    RecyclerView recyclerView;
    // AdminFragment1Adapter - used to provide the data for the RecyclerView
    PhysicianR4Activity2Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r4_2);
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        toolbar = findViewById(R.id.physician_r4_topAppBar);
        setupToolbarWithBackButton();

        //gets data from previous screen
        Intent intent = getIntent();
        patient = intent.getParcelableExtra("patient");
        fetchingAppointmentData();

        // initiates the RecyclerView
        recyclerView = findViewById(R.id.physician_r4_recyclerView);
        adapter = new PhysicianR4Activity2Adapter(this, appointments, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    private void fetchingAppointmentData(){
        String url = "http://" + MainActivity.getIP() + "/myTherapy/fetchClinicPatientCompletedAppointments.php";

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            ArrayList<ModelR4Appointment> temp = okHttpHandler.fetchCompletedAppointments(url);

            for (ModelR4Appointment app: temp){
                if(app.getAfm().equals(PhysicianMainActivity.getAfm()) && app.getAmka().equals(patient.getAmka())){
                    appointments.add(app);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method for the RecyclerViewInterface / physician_r4_recyclerView
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, PhysicianR4Activity3.class);
        intent.putExtra("ModelR4Appointment", appointments.get(position));
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }
}