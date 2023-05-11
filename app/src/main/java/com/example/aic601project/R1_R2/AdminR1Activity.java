package com.example.aic601project.R1_R2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdminR1Activity extends AppCompatActivity {
    /* hI short for hasInput - variables used to log whether a TextInputLayout has input
    * hNE short for hasNumberError - variable used to log whether there is a SSN error */
    private boolean hI1 = false, hI2 = false, hI3 = false, hI4 = false, hI5 = false,  hI6 = false, hI7 = false, hNE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_r1);

        // sets the status bar color and the toolbar back button functionality
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        MaterialToolbar toolbar = findViewById(R.id.admin_r1_topAppBar);
        setupToolbarWithBackButton(toolbar);

        /* We can figure out what launched the activity using the putExtra key
         * if key == 1 then activity was launched by add_button
         * if key == 2 then activity was launched by show_user_details_button */
        int key = getIntent().getIntExtra("key", 1);
        if (key == 1){
            findViewById(R.id.admin_r1_button).setEnabled(false);
            checkForInput(hI1, hI2, hI3, hI4, hI5, hI6, hI7, hNE);
        } else if (key == 2) {
            changeLayoutForKey2();
        }
    }

    // sets up a toolbar that performs the default back action when clicked
    private void setupToolbarWithBackButton(MaterialToolbar toolbar){
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });
    }

    // overrides onBackPressed and includes a closing animation
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_slide_in_or_out, R.anim.slide_out_from_top);
    }

    // onClick for form_clear TextView
    public void clearForm(View v){
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_name))).getEditText().getText().clear();
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_ssn))).getEditText().getText().clear();
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_email))).getEditText().getText().clear();
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_street))).getEditText().getText().clear();
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_stNumber))).getEditText().getText().clear();
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_city))).getEditText().getText().clear();
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_zip))).getEditText().getText().clear();
    }

    // method for checking every TextInputLayout for changed text (and more) and calling enableButtonIfAllInputIsTrue
    private void checkForInput(boolean hI1, boolean hI2, boolean hI3, boolean hI4, boolean hI5, boolean hI6, boolean hI7, boolean hNE){
        /* Checks every TextInputLayout field and logs to its' hI var whether it has input or not
         * Then calls enableButtonIfAllInputIsTrue */
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_name))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AdminR1Activity.this.hI1 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.admin_r1_button), AdminR1Activity.this.hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_ssn))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // algorithm that checks for a SSN error
                if (charSequence.length() == 9) {
                    char[] digits = charSequence.toString().toCharArray();
                    int exponent = digits.length-1;
                    int lastDigit = Integer.parseInt(Character.toString(digits[digits.length-1]));
                    int sum = 0;
                    for (int count = 0; count < digits.length-1; count++){
                        int digit = Integer.parseInt(Character.toString(digits[count]));
                        sum += digit * (int)Math.pow(2, exponent);
                        exponent--;
                    }
                    if (((sum % 11) % 10) != lastDigit){
                        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_ssn))).setError(getString(R.string.ssn_error_message));
                        AdminR1Activity.this.hNE = true;
                    }
                } else {
                    ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_ssn))).setErrorEnabled(false);
                    AdminR1Activity.this.hNE = false;
                }

                AdminR1Activity.this.hI2 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.admin_r1_button), AdminR1Activity.this.hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_email))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AdminR1Activity.this.hI3 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.admin_r1_button), AdminR1Activity.this.hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_street))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AdminR1Activity.this.hI4 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.admin_r1_button), AdminR1Activity.this.hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_stNumber))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AdminR1Activity.this.hI5 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.admin_r1_button), AdminR1Activity.this.hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_city))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AdminR1Activity.this.hI6 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.admin_r1_button), AdminR1Activity.this.hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_zip))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AdminR1Activity.this.hI7 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.admin_r1_button), AdminR1Activity.this.hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    // method for enabling button if every TextInputLayout has input (and more)
    private void enableButtonIfAllInputIsTrue(Button button, boolean hNE) {
        button.setEnabled(hI1 && hI2 && hI3 && hI4 && hI5 && hI6 && hI7 && !(hNE)
                && ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_ssn))).getEditText().getText().length() == 9
                && ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_zip))).getEditText().getText().length() == 5);
    }

    // method for changing the layout design to display user details
    private void changeLayoutForKey2(){
        MaterialToolbar toolbar = findViewById(R.id.admin_r1_topAppBar);
        setupToolbarWithBackButton(toolbar);
        Button btn = findViewById(R.id.admin_r1_button);
        // sets the button to enabled, changes the text and removes the icon
        btn.setEnabled(true);
        btn.setText("Επεξεργασία");
        btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        // sets the form_clear TextView to invisible
        findViewById(R.id.admin_r1_textview_clear_form).setVisibility(View.INVISIBLE);
        // changes the toolbar title
        toolbar.setTitle("Πληροφορίες");
        // disables all TextInputLayout fields
        disableFields();
        // disables the counter for physio_ssn and physio_zip
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_ssn))).setCounterEnabled(false);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_zip))).setCounterEnabled(false);
    }

    // method for changing the layout design to edit user details
    private void changeLayoutForKey2Edit(){
        MaterialToolbar toolbar = findViewById(R.id.admin_r1_topAppBar);
        setupToolbarWithBackButton(toolbar);
        Button btn = findViewById(R.id.admin_r1_button);
        // changes the button text
        btn.setText("Αποθήκευση");
        // sets the form_clear TextView to visible
        findViewById(R.id.admin_r1_textview_clear_form).setVisibility(View.VISIBLE);
        // changes the toolbar title
        toolbar.setTitle("Επεξεργασία"); // αλλαγή και του trailing button
        // enables all TextInputLayout fields
        enableFields();
        // enables the counter for physio_ssn and physio_zip
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_ssn))).setCounterEnabled(true);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_zip))).setCounterEnabled(true);

        checkForInput(hI1, hI2, hI3, hI4, hI5, hI6, hI7, hNE);
    }

    // method for enabling all TextInputLayout fields
    private void enableFields(){
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_name))).setEnabled(true);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_ssn))).setEnabled(true);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_email))).setEnabled(true);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_street))).setEnabled(true);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_stNumber))).setEnabled(true);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_city))).setEnabled(true);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_zip))).setEnabled(true);
    }

    // method for disabling all TextInputLayout fields
    private void disableFields(){
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_name))).setEnabled(false);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_ssn))).setEnabled(false);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_email))).setEnabled(false);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_street))).setEnabled(false);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_stNumber))).setEnabled(false);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_city))).setEnabled(false);
        ((TextInputLayout)(findViewById(R.id.admin_r1_textInputLayout_zip))).setEnabled(false);
    }

    // onClick for button
    public void addPhysio(View v){
        Button btn = findViewById(R.id.admin_r1_button);
        int key = getIntent().getIntExtra("key", 1);
        if (key == 1){
            // need to save the data the database
            Toast.makeText(AdminR1Activity.this, "Το Φυσιοθεραπευτήριο έχει προστεθεί", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else if (key == 2) {
            if (btn.getText().toString().equals("Επεξεργασία")){
                changeLayoutForKey2Edit();
            } else if (btn.getText().toString().equals("Αποθήκευση")) {
                // make a method that checks whether new changes have been made
                onBackPressed();
            }
        }
    }

}

// TO-DO
// αποθήκευση των δεδομένων στη ΒΔ με κλικ του button στη προσθήκη φυσιοθεραπευτηρίου
// υλοποίηση recyclerview για τα δεδομένα που τραβάω από τη ΒΔ
// υλοποίηση μεθόδου έλεγχου για το αν έχουν γίνει αλλαγές στα στοιχεία του φυσιοθεραπευτηρίου και αποθήκευση των αλλαγών στη ΒΔ