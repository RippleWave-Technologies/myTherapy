package com.example.aic601project.R3_R8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aic601project.MainActivity;
import com.example.aic601project.OkHttpHandler;
import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class PhysicianR3Activity extends AppCompatActivity {

    // hI short for hasInput - logs whether a TextInputLayout has input
    private final boolean[] hI = {false, false, false, false, false, false, false};
    private boolean hNE = false;
    // textInputLayoutArray - logs all TextInputLayouts to a TextInputLayout[]
    private TextInputLayout[] textInputLayoutArray;

    // The variables to store the input from the user for the Patients details
    private TextInputLayout patientName, patientSurname, patientAmkaLayout, patientStreet,
            patientStreetNo, patientCity, patientZip;

    // The Patients' (R3) Button that adds the Patients details
    private Button button;
    // The Patients' (R3) Toolbar
    private MaterialToolbar toolbar;
    // String - used to get the ip address from the MainActivity, the AFM from the PhysicianMainActivity
    private String ip, afm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r3);
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        button = findViewById(R.id.r3_add_button);
        button.setEnabled(false);
        toolbar = findViewById(R.id.r3_topAppBar);
        setupToolbarWithBackButton();

        // Gets the IP from the MainActivity
        ip = MainActivity.getIP();

        // gets the AFM from the PhysicianMainActivity
        afm = PhysicianMainActivity.getAfm();

        textInputLayoutArray = setTextInputLayoutArray();
        checkForInput();
    }

    // creates a TextInputLayout[] with the Ids of all TextInputLayouts
    private TextInputLayout[] setTextInputLayoutArray() {
        TextInputLayout[] textInputLayoutArray = new TextInputLayout[7];
        textInputLayoutArray[0] = findViewById(R.id.r3_textInputLayout_name);
        textInputLayoutArray[1] = findViewById(R.id.r3_textInputLayout_surname);
        textInputLayoutArray[2] = findViewById(R.id.r3_textInputLayout_amka);
        textInputLayoutArray[3] = findViewById(R.id.r3_textInputLayout_street);
        textInputLayoutArray[4] = findViewById(R.id.r3_textInputLayout_streetno);
        textInputLayoutArray[5] = findViewById(R.id.r3_textInputLayout_city);
        textInputLayoutArray[6] = findViewById(R.id.r3_textInputLayout_zip);
        return textInputLayoutArray;
    }

    // checks every TextInputLayout for changed text (and more) and calls
    // enableButtonIfAllInputIsTrue
    private void checkForInput() {
        for (int i = 0; i <= 6; i++) {
            final int index = i;
            Objects.requireNonNull(textInputLayoutArray[i].getEditText()).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (index == 2) {
                        hNE = validateAMKA(charSequence.toString());
                        if (!hNE && charSequence.toString().length() == 11) {
                            textInputLayoutArray[index].setError("Το ΑΜΚΑ πρέπει να είναι 11 ψηφία.");
                        }
                        else {
                            textInputLayoutArray[index].setErrorEnabled(false);
                        }
                    }
                    hI[index] = !(charSequence.toString().isEmpty());
                    enableButtonIfAllInputIsTrue();
                }
                @Override
                public void afterTextChanged(Editable editable) {}
            });
        }
    }

    // enables admin_r1_1_button if every TextInputLayout has input (and more)
    private void enableButtonIfAllInputIsTrue() {
        button.setEnabled(hI[0] && hI[1] && hI[2] && hI[3] && hI[4] && hI[5] && hI[6] && hNE
                && Objects.requireNonNull(textInputLayoutArray[2].getEditText()).getText().length() == 11
                && Objects.requireNonNull(textInputLayoutArray[6].getEditText()).getText().length() == 5);
    }

    // onClick for r3_add_button Button
    public void addPatient(View v) {
        int result = 0;

        String url = "http://" + ip + "/myTherapy/insertClinicPatients.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            result = okHttpHandler.insertClinicPatients(url, Objects.requireNonNull(textInputLayoutArray[2].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[0].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[1].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[3].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[4].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[5].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[6].getEditText()).getText().toString(), afm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == 0) {
            Toast.makeText(PhysicianR3Activity.this, "Τα στοιχεία που έχουν εισηχθεί είναι λάθος.", Toast.LENGTH_LONG).show();
            onBackPressed();
        } else if (result == 1) {
            Toast.makeText(PhysicianR3Activity.this, "Ο ασθενής αυτός έχει ήδη καταχωρηθεί σε αυτό το φυσιοθεραπευτήριο.", Toast.LENGTH_LONG).show();
            onBackPressed();
        } else if (result == 2){
            Toast.makeText(PhysicianR3Activity.this, "Ο ασθενής προστέθηκε επιτυχώς.", Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }

    public boolean validateAMKA(String amka) {
        if (!amka.matches("[0-9]{11}") || amka.equals("00000000000")) {
            return false;
        }

        int checksum = 0;
        for (int i = 1; i <= amka.length(); i++) {
            int digit = Integer.parseInt(String.valueOf(amka.charAt(i - 1)));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            checksum += digit;
        }

        return checksum % 10 == 0;
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

    /*
     * overrides the onCreateOptionsMenu because by calling setSupportActionBar
     * the menu will be populated with standard system menu items
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.physician_r3_app_bar_layout, menu);
        return true;
    }
}