package com.example.aic601project.R3_R8;

import com.example.aic601project.ModelPatient;
import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PhysicianR4Activity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    // inputFields
    private TextInputLayout textInputName;
    private TextInputLayout textInputLastName;
    private TextInputLayout textInputAMKA;
    private TextInputLayout textInputStreet;
    private TextInputLayout textInputStNumber;
    private TextInputLayout textInputCity;
    private TextInputLayout textInputZip;

    private ModelPatient patient;
    // Buttons
    private Button physicianAppointmentsHistoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r4);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        toolbar = findViewById(R.id.physician_r4_topAppBar);
        setupToolbarWithBackButton();

        Intent intent = getIntent();
        patient = intent.getParcelableExtra("patient");

        // get all TextInput ids.
        textInputName = findViewById(R.id.physician_r4_textInputLayout_name);
        textInputLastName = findViewById(R.id.physician_r4_textInputLayout_lastName);
        textInputAMKA = findViewById(R.id.physician_r4_textInputLayout_amka);
        textInputStreet = findViewById(R.id.physician_r4_textInputLayout_street);
        textInputStNumber = findViewById(R.id.physician_r4_textInputLayout_stNumber);
        textInputCity = findViewById(R.id.physician_r4_textInputLayout_city);
        textInputZip = findViewById(R.id.physician_r4_textInputLayout_zip);

        physicianAppointmentsHistoryButton = findViewById(R.id.physician_r4_appointmentsHistoryButton);
        // Set all textInputField Disable
        this.loadStartPhysicianInformationScreen();
    }

    public void viewAppointmentsHistory(View v) {
        Intent i = new Intent(this, PhysicianR4AppointmentLogHistory.class);
        i.putExtra("appointments", patient.getCompletedAppointments());
        startActivity(i);
    }

    private void loadStartPhysicianInformationScreen() {
        this.toolbar.setTitle("Πληροφορίες");
        this.physicianAppointmentsHistoryButton.setVisibility(View.VISIBLE);

        this.setPatientInformation();
        // Set all textInputFields disabled
        this.changeStatusOfTextInputField(false);

    }

    private void changeStatusOfTextInputField(boolean status) {
        textInputName.setEnabled(status);
        textInputLastName.setEnabled(status);
        textInputAMKA.setEnabled(status);
        textInputStreet.setEnabled(status);
        textInputStNumber.setEnabled(status);
        textInputCity.setEnabled(status);
        textInputZip.setEnabled(status);
    }

    private void setPatientInformation(){
        textInputName.getEditText().setText(patient.getName());
        textInputLastName.getEditText().setText(patient.getSurname());
        textInputAMKA.getEditText().setText(patient.getAmka());
        textInputStreet.getEditText().setText(patient.getAddress());
        textInputStNumber.getEditText().setText(patient.getAddressNumber());
        textInputCity.getEditText().setText(patient.getCity());
        textInputZip.getEditText().setText(patient.getPostcode());
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
