package com.example.aic601project.R3_R8;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aic601project.MainActivity;
import com.example.aic601project.OkHttpHandler;
import com.example.aic601project.R;
import com.example.aic601project.R1_R2.AdminR1Activity1;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class PatientR3Activity extends AppCompatActivity {

    // The variables to store the input from the user for the Patients Credentials
    private TextInputLayout patientName;
    private TextInputLayout patientSurname;
    private TextInputLayout patientAmkaLayout;
    private TextInputLayout patientStreet;
    private TextInputLayout patientStreetNo;
    private TextInputLayout patientCity;
    private TextInputLayout patientZip;
    // The Patients' (R3) Button that adds the Patients Credentials
    private Button button;
    // The Patients' (R3) Toolbar
    private MaterialToolbar toolbar;
    // String - used to get the ip address from the MainActivity
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_r3);
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        // Gets the IP from the MainActivity
        ip = MainActivity.getIP();

        // Bridging the variables with the ids from the activity_patient_r3 layout
        patientName = findViewById(R.id.r3_textInputLayout_name);
        patientSurname = findViewById(R.id.r3_textInputLayout_surname);
        patientAmkaLayout = findViewById(R.id.r3_textInputLayout_amka);
        patientStreet = findViewById(R.id.r3_textInputLayout_street);
        patientStreetNo = findViewById(R.id.r3_textInputLayout_streetno);
        patientCity = findViewById(R.id.r3_textInputLayout_city);
        patientZip = findViewById(R.id.r3_textInputLayout_zip);
        TextInputEditText patientAmkaEditText = findViewById(R.id.r3_textInputLayout_editText_amka);

        button = findViewById(R.id.r3_add_button);
        // Disabling the button initially so that it gets enabled only if every field is filled in correctly
        button.setEnabled(false);
        toolbar = findViewById(R.id.r3_appBarLayout);
        setupToolbarWithBackButton();

        patientAmkaEditText.setOnKeyListener((v, keyCode, event) -> {
            // Validate fields on each key press
            validateFields();
            return false;
        });
    }


    // This is the method that onClick uses for the r3_add_button Button to sent the information to the database and create the correlation between the Patient and the Clinic
    public void addPatient(View view) {
        // Get the patient details from the input fields
        String name = Objects.requireNonNull(patientName.getEditText()).getText().toString();
        String surname = Objects.requireNonNull(patientSurname.getEditText()).getText().toString();
        String amka = Objects.requireNonNull(patientAmkaLayout.getEditText()).getText().toString();
        String street = Objects.requireNonNull(patientStreet.getEditText()).getText().toString();
        String streetNo = Objects.requireNonNull(patientStreetNo.getEditText()).getText().toString();
        String city = Objects.requireNonNull(patientCity.getEditText()).getText().toString();
        String zip = Objects.requireNonNull(patientZip.getEditText()).getText().toString();

        // Get the clincsAfm from the AdminR1Activity1 class
        String clincsAfm = AdminR1Activity1.textInputLayoutArray[1];

        // Check if Patient details and clincsAfm are correlated
        int areCorrelated = checkCorrelation(amka, name, surname, street, streetNo, city, zip, clincsAfm);

        // Proceed if they are correlated for the 1 and 2 case
        if  (areCorrelated==0){
            // Show a toast indicating that patient details and clincsAfm are not correlated
            Toast.makeText(this, "Ο ασθενής αυτός δεν ανήκει σε αυτό το φυσιοθεραπευτήριο", Toast.LENGTH_SHORT).show();
            return;
        } else if (areCorrelated==1) {
            // Show a toast indicating that Patient details and clincsAfm are already correlated
            Toast.makeText(this, "Ο ασθενής αυτός έχει ήδη καταχωρηθεί σε αυτό το φυσιοθεραπευτήριο", Toast.LENGTH_SHORT).show();
            return;
        } else if (areCorrelated==2){
            // Insert the correlation between Patient details and clincsAfm in the PHP file
            insertCorrelation(amka, name, surname, street, streetNo, city, zip, clincsAfm);
        } else{
            // Show a toast indicating that there was an error during the HTTP request
            Toast.makeText(this, "Υπήρξε σφάλμα κατα τη διάρκεια του αιτήματος", Toast.LENGTH_SHORT).show();
            return;
        }
        onBackPressed();
    }

    // This method checks the value the insertClinicPatients.php file returns to determine the actions that needs to be taken
    private int checkCorrelation(String amka, String name, String surname, String street, String streetNo, String city, String zip, String clAfm) {
        String url = "http://" + ip + "/myTherapy/insertClinicsPatients.php";

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            int response = okHttpHandler.insertPatient(url, name, surname, amka, street, streetNo, city, zip, clAfm);

            // Check the response value from the PHP file
            if (response == 1) {
                // The patient is already correlated to the clinic - returns true to indicate correlation
                return 1;
            } else if (response == 2) {
                // A new correlation needs to be created between patient and clinic - returns true to indicate correlation
                return 2;
            } else {
                // Therapy or Patient entry is not found - returns false to indicate no correlation
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // An error occurred during the HTTP request
            return 3;
        }
    }

    // This method inserts the correlation between the Patient and the clinic in the insertClinicPatients.php
    private void insertCorrelation(String amka, String name, String surname, String street, String streetNo, String city, String zip, String clAfm) {
        String url = "http://" + ip + "/myTherapy/insertClinicsPatients.php";

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            int result = okHttpHandler.insertPatient(url,  amka, name, surname, street, streetNo, city, zip, clAfm);

            if (result != 0) {
                // Successful response from the PHP file - Patient information inserted successfully and announce that it has been added to the database
                Toast.makeText(this, "Ο ασθενής έχει προστεθεί", Toast.LENGTH_SHORT).show();
            } else {
                // Unsuccessful response from the PHP file - failed to insert Patient information and announce that it hasn't been added to the database
                Toast.makeText(this, "Ο ασθενής δεν έχει προστεθεί, ξανα προσπαθήστε", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // This method checks if all the fields have input (and is correct) so that the button is enabled
    private void validateFields() {
        String name = Objects.requireNonNull(patientName.getEditText()).getText().toString().trim();
        String surname = Objects.requireNonNull(patientSurname.getEditText()).getText().toString().trim();
        String amka = Objects.requireNonNull(patientAmkaLayout.getEditText()).getText().toString().trim();
        String street = Objects.requireNonNull(patientStreet.getEditText()).getText().toString().trim();
        String streetNo = Objects.requireNonNull(patientStreetNo.getEditText()).getText().toString().trim();
        String city = Objects.requireNonNull(patientCity.getEditText()).getText().toString().trim();
        String zip = Objects.requireNonNull(patientZip.getEditText()).getText().toString().trim();

        // Creates boolean variables that will be used to check if they are false and enable the button
        boolean isNameValid = !TextUtils.isEmpty(name);
        boolean isSurnameValid = !TextUtils.isEmpty(surname);
        boolean isStreetValid = !TextUtils.isEmpty(street);
        boolean isStreetNoValid = !TextUtils.isEmpty(streetNo);
        boolean isCityValid = !TextUtils.isEmpty(city);
        boolean isZipValid = !TextUtils.isEmpty(zip);
        boolean isAmkaValid = validateAMKA(amka);

        if (!isAmkaValid) {
            // Displays the error message
            patientAmkaLayout.setError("Μη έγκυρος αριθμός ΑΜΚΑ");
        } else {
            // Clears the error message if the AMKA is valid
            patientAmkaLayout.setError(null);
        }

        button.setEnabled(isNameValid && isSurnameValid && isAmkaValid && isStreetValid &&
                isStreetNoValid && isCityValid && isZipValid);
    }


    // This method checks the validity of the Patients' AMKA
    public static boolean validateAMKA(@NonNull String amka) {
        if (!amka.matches("^\\d{11}$") || amka.equals("00000000000"))
            return false;
        int iSum = 0;
        for (int i = 1; i <= amka.length(); i++) {
            int iDigit = Integer.parseInt(Character.toString(amka.charAt(i - 1)));
            if (i % 2 == 0) {
                iDigit *= 2;
                if (iDigit > 9) {
                    iDigit -= 9;
                }
            }
            iSum += iDigit;
        }
        return (iSum % 10 == 0);
    }

    // This method overrides the default menu with the new one created especially for the R3 Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.patient_r3_app_bar_layout, menu);
        return true;
    }

    // This method sets up a toolbar where clicking the back button calls onBackPressed()
    private void setupToolbarWithBackButton() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    // This method overrides the default onBackPressed() function and includes an exit animation
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_slide_in_or_out, R.anim.slide_out_from_top);
    }
}
