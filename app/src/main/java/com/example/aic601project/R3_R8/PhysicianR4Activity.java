package com.example.aic601project.R3_R8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

public class PhysicianR4Activity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    //    inputFields
    private TextInputLayout textInputName;
    private TextInputLayout textInputLastName;
    private TextInputLayout textInputAMKA;
    private TextInputLayout textInputStreet;
    private TextInputLayout textInputStNumber;
    private TextInputLayout textInputCity;
    private TextInputLayout textInputZip;

    //    Buttons
    private Boolean physicianEditButtonIsClicked;
    private Button physicianEditButton ;
    private Button physicianAppointmentsHistoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r4);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        toolbar = findViewById(R.id.physician_r4_topAppBar);
        setupToolbarWithBackButton();

        //        get all buttons ids
//        physicianEditButton = findViewById(R.id.physician_r4_appointmentsHistoryButton);
        physicianEditButton = findViewById(R.id.physician_r4_EditButton);

        //        get all TextInput ids.
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

    public void viewAppointmentsHistory(View v){
        Intent i = new Intent(this, PhysicianR4AppointmentLogHistory.class);
        startActivity(i);
//        requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom,  R.anim.no_slide_in_or_out);
    }

    private void loadStartPhysicianInformationScreen(){
        this.toolbar.setTitle("Πληροφορίες");
        this.physicianAppointmentsHistoryButton.setVisibility(View.VISIBLE);
        // check if edit button is clicked
        this.physicianEditButtonIsClicked = false;
        this.physicianEditButton.setText("Επεξεργασία");

        // Set all textInputFields disable
        this.changeStatusOfTextInputField(false);

    }

    private void loadEditPhysicianInformationScreen(){
        this.toolbar.setTitle("Επεξεργασία");

        this.physicianAppointmentsHistoryButton.setVisibility(View.INVISIBLE);
        // check if edit button is clicked
        this.physicianEditButtonIsClicked = true;
        this.physicianEditButton.setText("Αποθήκευση");

        // Set all textInputFields disable
        this.changeStatusOfTextInputField(true);

    }
    public void physicianEditButtonClicked (View v){
        if (physicianEditButtonIsClicked){
            this.loadStartPhysicianInformationScreen();
        }else{
            this.loadEditPhysicianInformationScreen();
        }
    }

    private void changeStatusOfTextInputField(boolean status){
        textInputName.setEnabled(status);
        textInputLastName.setEnabled(status);
        textInputAMKA.setEnabled(status);
        textInputStreet.setEnabled(status);
        textInputStNumber.setEnabled(status);
        textInputCity.setEnabled(status);
        textInputZip.setEnabled(status);
    }

    // sets up a toolbar where clicking the back button calls onBackPressed()
    private void setupToolbarWithBackButton(){
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    // overrides the default onBackPressed() function and includes an exit animation
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_slide_in_or_out, R.anim.slide_out_from_top);
    }
}
