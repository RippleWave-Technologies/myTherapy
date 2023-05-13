package com.example.aic601project.R1_R2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AdminR1Activity extends AppCompatActivity {
    // hI short for hasInput - logs whether a TextInputLayout has input
    private final boolean[] hI = {false, false, false, false, false, false, false};
    // hNE short for hasNumberError - logs whether there is an AFM error
    private boolean hNE = false;
    // textInputLayoutArray - logs all TextInputLayouts to a TextInputLayout[]
    private TextInputLayout[] textInputLayoutArray;
    // toolbar - admin_r1_topAppBar
    private MaterialToolbar toolbar;
    // button - admin_r1_button
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_r1);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        textInputLayoutArray = setTextInputLayoutArray();
        button = findViewById(R.id.admin_r1_button);
        toolbar = findViewById(R.id.admin_r1_topAppBar);
        setupToolbarWithBackButton();

        /* We can figure out what launched the activity using the putExtra key
         * if key == 1 then activity was launched by admin_button_add
         * if key == 2 then activity was launched by adminR1_button_users */
        int key = getIntent().getIntExtra("key", 1);
        switch (key) {
            case 1:
                button.setEnabled(false);
                checkForInput();
                break;
            case 2:
                changeLayoutForKey2();
                break;
        }
    }

    // creates an int[] with the Ids of all TextInputLayouts
    private TextInputLayout[] setTextInputLayoutArray(){
        TextInputLayout[] textInputLayoutArray = new TextInputLayout[7];
        textInputLayoutArray[0] = findViewById(R.id.admin_r1_textInputLayout_name);
        textInputLayoutArray[1] = findViewById(R.id.admin_r1_textInputLayout_ssn);
        textInputLayoutArray[2] = findViewById(R.id.admin_r1_textInputLayout_email);
        textInputLayoutArray[3] = findViewById(R.id.admin_r1_textInputLayout_street);
        textInputLayoutArray[4] = findViewById(R.id.admin_r1_textInputLayout_stNumber);
        textInputLayoutArray[5] = findViewById(R.id.admin_r1_textInputLayout_city);
        textInputLayoutArray[6] = findViewById(R.id.admin_r1_textInputLayout_zip);
        return textInputLayoutArray;
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

    // checks every TextInputLayout for changed text (and more) and calls enableButtonIfAllInputIsTrue
    private void checkForInput(){
        for (int i = 0; i <= 6; i++){
            final int index = i;
            Objects.requireNonNull(textInputLayoutArray[i].getEditText()).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (index == 1){
                        checkAfm(charSequence);
                    }
                    hI[index] = !(charSequence.toString().isEmpty());
                    enableButtonIfAllInputIsTrue();
                }
                @Override
                public void afterTextChanged(Editable editable) {}
            });
        }
    }

    // checks the validity of the AFM
    private void checkAfm(CharSequence charSequence){
        if (charSequence.length() == 9) {
            int exponent = charSequence.length() - 1;
            int lastDigit = Integer.parseInt(Character.toString(charSequence.charAt(charSequence.length() - 1)));
            int sum = 0;
            for (int count = 0; count < charSequence.length() - 1; count++) {
                int digit = Integer.parseInt(Character.toString(charSequence.charAt(count)));
                sum += digit * (int) Math.pow(2, exponent);
                exponent--;
            }
            if (((sum % 11) % 10) != lastDigit) {
                textInputLayoutArray[1].setError(getString(R.string.ssn_error_message));
                AdminR1Activity.this.hNE = true;
            }
        } else {
            textInputLayoutArray[1].setErrorEnabled(false);
            AdminR1Activity.this.hNE = false;
        }
    }

    // enables admin_r1_button if every TextInputLayout has input (and more)
    private void enableButtonIfAllInputIsTrue() {
        button.setEnabled(hI[0] && hI[1] && hI[2] && hI[3] && hI[4] && hI[5] && hI[6] && !(hNE)
                && Objects.requireNonNull(textInputLayoutArray[1].getEditText()).getText().length() == 9
                && Objects.requireNonNull(textInputLayoutArray[6].getEditText()).getText().length() == 5);
    }

    // onClick for admin_r1_textview_clear_form TextView
    public void clearForm(View v){
        for (int i = 0; i <=6; i++){
            Objects.requireNonNull(textInputLayoutArray[i].getEditText()).getText().clear();
        }
    }

    // enables/disables all TextInputLayouts
    private void fieldsEnableDisable(boolean enDis){
        for (int i = 0; i <=6; i++){
            textInputLayoutArray[i].setEnabled(enDis);
        }
    }

    // onClick for admin_r1_button Button
    public void addPhysio(View v){
        int key = getIntent().getIntExtra("key", 1);
        switch (key) {
            case 1:
                // need to save the data to the database
                Toast.makeText(AdminR1Activity.this, "Το Φυσιοθεραπευτήριο έχει προστεθεί", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
            case 2:
                switch (button.getText().toString()) {
                    case "Επεξεργασία":
                        changeLayoutForKey2Edit();
                        break;
                    case "Αποθήκευση":
                        // make a method that checks whether new changes have been made and saves the data to the database
                        onBackPressed();
                        break;
                }
                break;
        }
    }

    // changes the layout to display user details
    private void changeLayoutForKey2(){
        // changes the toolbar title
        toolbar.setTitle("Πληροφορίες");
        // disables all TextInputLayout fields
        fieldsEnableDisable(false);
        // disables the counter for physio_ssn and physio_zip
        textInputLayoutArray[1].setCounterEnabled(false);
        textInputLayoutArray[6].setCounterEnabled(false);
        // sets the form_clear TextView to invisible
        findViewById(R.id.admin_r1_textview_clear_form).setVisibility(View.INVISIBLE);
        // sets the button to enabled, changes the text and removes the icon
        button.setEnabled(true);
        button.setText("Επεξεργασία");
        button.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    // changes the layout edit user details
    private void changeLayoutForKey2Edit(){
        // changes the toolbar title
        toolbar.setTitle("Επεξεργασία");
        // enables all TextInputLayout fields
        fieldsEnableDisable(true);
        // enables the counter for physio_ssn and physio_zip
        textInputLayoutArray[1].setCounterEnabled(true);
        textInputLayoutArray[6].setCounterEnabled(true);
        // sets the form_clear TextView to visible
        findViewById(R.id.admin_r1_textview_clear_form).setVisibility(View.VISIBLE);
        // sets the button to disabled and changes the text
        button.setEnabled(false);
        button.setText("Αποθήκευση");

        checkForInput();
    }
}

// TO-DO
// με κλικ του admin_r1_button στη Προσθήκη Φυσιοθεραπευτηρίου -> αποθήκευση των δεδομένων στη ΒΔ
// υλοποίηση recyclerview για τα δεδομένα που αντλούνται από τη ΒΔ
// υλοποίηση ελέγχου για το αν έχουν γίνει αλλαγές στα στοιχεία του φυσιοθεραπευτηρίου και αποθήκευση των αλλαγών στη ΒΔ
// αλλαγή του AppBarLayout και των εικονιδίων που αυτό έχει
// αφαίρεση των style από τα TextInputLayout ;