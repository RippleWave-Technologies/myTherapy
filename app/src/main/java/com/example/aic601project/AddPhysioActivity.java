package com.example.aic601project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddPhysioActivity extends AppCompatActivity {

    /* hI short for hasInput - variables used to log whether a TextInputLayout has input
    * hNE short for hasNumberError - variable used to log whether there is a SSN error */
    private boolean hI1 = false, hI2 = false, hI3 = false, hI4 = false, hI5 = false,  hI6 = false, hI7 = false, hNE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_physio);

        // sets the status bar color
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        // returns to the main activity when the back button is clicked
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /* Starts with disabling the button by default
        * Then checks every field and logs to its' hI var whether it has input or not
        * Then calls enableButtonIfAllInputIsTrue */
        findViewById(R.id.button).setEnabled(false);
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.physio_name))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hI1 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.button), hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.physio_ssn))).getEditText()).addTextChangedListener(new TextWatcher() {
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
                        ((TextInputLayout)(findViewById(R.id.physio_ssn))).setError(getString(R.string.ssn_error_message));
                        hNE = true;
                    }
                } else {
                    ((TextInputLayout)(findViewById(R.id.physio_ssn))).setErrorEnabled(false);
                    hNE = false;
                }

                hI2 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.button), hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.physio_email))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hI3 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.button), hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.physio_street))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hI4 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.button), hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.physio_stnumber))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hI5 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.button), hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.physio_city))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hI6 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.button), hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ((TextInputEditText)((TextInputLayout)(findViewById(R.id.physio_zip))).getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hI7 = !(charSequence.toString().isEmpty());
                enableButtonIfAllInputIsTrue(findViewById(R.id.button), hNE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_slide_in_or_out, R.anim.slide_out_from_top);
    }

    // onClick for form_clear
    public void clearForm(View v){
        ((TextInputEditText)(((TextInputLayout)(findViewById(R.id.physio_name))).getEditText())).setText("");
        ((TextInputEditText)(((TextInputLayout)(findViewById(R.id.physio_ssn))).getEditText())).setText("");
        ((TextInputEditText)(((TextInputLayout)(findViewById(R.id.physio_email))).getEditText())).setText("");
        ((TextInputEditText)(((TextInputLayout)(findViewById(R.id.physio_street))).getEditText())).setText("");
        ((TextInputEditText)(((TextInputLayout)(findViewById(R.id.physio_stnumber))).getEditText())).setText("");
        ((TextInputEditText)(((TextInputLayout)(findViewById(R.id.physio_city))).getEditText())).setText("");
        ((TextInputEditText)(((TextInputLayout)(findViewById(R.id.physio_zip))).getEditText())).setText("");
    }

    // method for enabling the button if every field has input +
    private void enableButtonIfAllInputIsTrue(Button button, boolean hNE) {
        button.setEnabled(hI1 && hI2 && hI3 && hI4 && hI5 && hI6 && hI7 && !(hNE)
                && ((TextInputLayout)(findViewById(R.id.physio_ssn))).getEditText().getText().length() == 9
                && ((TextInputLayout)(findViewById(R.id.physio_zip))).getEditText().getText().length() == 5);
    }

    // onClick for button
    public void addPhysio(View v){
        // need to save the data to a database
        Toast.makeText(AddPhysioActivity.this, "Το Φυσιοθεραπευτήριο έχει προστεθεί", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

}