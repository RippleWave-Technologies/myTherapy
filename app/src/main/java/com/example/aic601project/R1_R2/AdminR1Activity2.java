package com.example.aic601project.R1_R2;

import java.util.Objects;

import com.example.aic601project.ModelClinic;
import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminR1Activity2 extends AppCompatActivity {
    // hI short for hasInput - logs whether a TextInputLayout has input
    private final boolean[] hI = { true, true, true, true, true, true, true };
    // textIntentInputLayoutArray - logs intent contents to a String[]
    private String[] intentStringArray;
    // textInputLayoutArray - logs all TextInputLayouts to a TextInputLayout[]
    private TextInputLayout[] textInputLayoutArray;
    // toolbar - admin_r1_2_topAppBar
    private MaterialToolbar toolbar;
    // button - admin_r1_2_button
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_r1_2);
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        // gets the intent and the ModelClinic object from the previous activity
        Intent intent = getIntent();
        ModelClinic clinic = intent.getParcelableExtra("Clinic Info");
        intentStringArray = setIntentStringArray(clinic);

        textInputLayoutArray = setTextInputLayoutArray(intentStringArray);
        button = findViewById(R.id.admin_r1_2_button);
        toolbar = findViewById(R.id.admin_r1_2_topAppBar);
        setupToolbarWithBackButton();

        // changes the toolbar title and disables all TextInputLayout fields
        toolbar.setTitle("Πληροφορίες");
        fieldsEnableDisable(false);
        // disables the counter for physio_ssn and physio_zip
        textInputLayoutArray[1].setCounterEnabled(false);
        textInputLayoutArray[6].setCounterEnabled(false);
        // changes the button's text and removes it's icon
        button.setText("Επεξεργασία");
        button.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    // creates a String[] with String values from the ModelClinic object
    private String[] setIntentStringArray(ModelClinic clinic) {
        String[] intentStringArray = new String[7];
        intentStringArray[0] = clinic.getPhysioName();
        intentStringArray[1] = clinic.getPhysioAFM();
        intentStringArray[2] = clinic.getPhysioEmail();
        intentStringArray[3] = clinic.getPhysioAddress();
        intentStringArray[4] = clinic.getPhysioAddressNumber();
        intentStringArray[5] = clinic.getPhysioCity();
        intentStringArray[6] = clinic.getPhysioPostCode();
        return intentStringArray;
    }

    // creates a TextInputLayout[] with the Ids of all TextInputLayouts
    private TextInputLayout[] setTextInputLayoutArray(String[] intentStringArray) {
        TextInputLayout[] textInputLayoutArray = new TextInputLayout[7];
        textInputLayoutArray[0] = findViewById(R.id.admin_r1_2_textInputLayout_name);
        textInputLayoutArray[1] = findViewById(R.id.admin_r1_2_textInputLayout_ssn);
        textInputLayoutArray[2] = findViewById(R.id.admin_r1_2_textInputLayout_email);
        textInputLayoutArray[3] = findViewById(R.id.admin_r1_2_textInputLayout_street);
        textInputLayoutArray[4] = findViewById(R.id.admin_r1_2_textInputLayout_stNumber);
        textInputLayoutArray[5] = findViewById(R.id.admin_r1_2_textInputLayout_city);
        textInputLayoutArray[6] = findViewById(R.id.admin_r1_2_textInputLayout_zip);

        for (int i = 0; i < 7; i++){
            textInputLayoutArray[i].getEditText().setText(intentStringArray[i]);
        }

        return textInputLayoutArray;
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
        getMenuInflater().inflate(R.menu.admin_r1_app_bar_layout, menu);
        return true;
    }

    // checks every TextInputLayout for changed text (and more) and calls
    // enableButtonIfAllInputIsTrue
    private void checkForInput() {
        for (int i = 0; i <= 6; i++) {
            final int index = i;
            Objects.requireNonNull(textInputLayoutArray[i].getEditText()).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    hI[index] = !(charSequence.toString().isEmpty());
                    enableButtonIfAllInputIsTrue();
                }
                @Override
                public void afterTextChanged(Editable editable) { }
            });
        }
    }

    // enables admin_r1_2_button if every TextInputLayout has input (and more)
    private void enableButtonIfAllInputIsTrue() {
        boolean allInput = (hI[0] && hI[1] && hI[2] && hI[3] && hI[4] && hI[5] && hI[6]
                && Objects.requireNonNull(textInputLayoutArray[6].getEditText()).getText().length() == 5);

        boolean newInput = false;
        for (int i = 0; i < 7; i++){
            if (!textInputLayoutArray[i].getEditText().getText().toString().equals(intentStringArray[i])){
                newInput = true;
            }
        }

        button.setEnabled(allInput && newInput);
    }

    // enables/disables all TextInputLayouts
    private void fieldsEnableDisable(boolean enDis) {
        for (int i = 0; i <= 6; i++) {
            textInputLayoutArray[i].setEnabled(enDis);
        }
    }

    // onClick for admin_r1_2_button Button
    public void addPhysio(View v) {
        switch (button.getText().toString()) {
            case "Επεξεργασία":
                changeLayoutToEdit();
                break;
            case "Αποθήκευση":
                onBackPressed();
                break;
        }
    }

    // changes the layout edit user details
    private void changeLayoutToEdit() {
        // changes the toolbar title and enables all** TextInputLayout fields
        toolbar.setTitle("Επεξεργασία");
        fieldsEnableDisable(true);
        textInputLayoutArray[1].setEnabled(false);
        // enables the counter for physio_zip
        textInputLayoutArray[6].setCounterEnabled(true);
        // sets the button to disabled and changes the text
        button.setEnabled(false);
        button.setText("Αποθήκευση");

        checkForInput();
    }
}