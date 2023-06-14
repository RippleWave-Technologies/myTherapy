package com.example.aic601project.R1_R2;

import java.util.Objects;

import com.example.aic601project.MainActivity;
import com.example.aic601project.OkHttpHandler;
import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminR1Activity1 extends AppCompatActivity {
    // hI short for hasInput - logs whether a TextInputLayout has input
    private final boolean[] hI = {false, false, false, false, false, false, false};
    // hNE short for hasNumberError - logs whether there is an AFM error
    private boolean hNE = false;
    // textInputLayoutArray - logs all TextInputLayouts to a TextInputLayout[]
    private TextInputLayout[] textInputLayoutArray;
    // toolbar - admin_r1_1_topAppBar
    private MaterialToolbar toolbar;
    // button - admin_r1_1_button
    private Button button;
    // String - used to get the ip address from the MainActivity
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_r1_1);
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        // gets the IP address from the MainActivity
        ip = MainActivity.getIP();

        textInputLayoutArray = setTextInputLayoutArray();

        button = findViewById(R.id.admin_r1_1_button);
        toolbar = findViewById(R.id.admin_r1_1_topAppBar);
        setupToolbarWithBackButton();

        checkForInput();
    }

    // creates a TextInputLayout[] with the Ids of all TextInputLayouts
    private TextInputLayout[] setTextInputLayoutArray() {
        TextInputLayout[] textInputLayoutArray = new TextInputLayout[7];
        textInputLayoutArray[0] = findViewById(R.id.admin_r1_1_textInputLayout_name);
        textInputLayoutArray[1] = findViewById(R.id.admin_r1_1_textInputLayout_ssn);
        textInputLayoutArray[2] = findViewById(R.id.admin_r1_1_textInputLayout_email);
        textInputLayoutArray[3] = findViewById(R.id.admin_r1_1_textInputLayout_street);
        textInputLayoutArray[4] = findViewById(R.id.admin_r1_1_textInputLayout_stNumber);
        textInputLayoutArray[5] = findViewById(R.id.admin_r1_1_textInputLayout_city);
        textInputLayoutArray[6] = findViewById(R.id.admin_r1_1_textInputLayout_zip);
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
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (index == 1) {checkAfm(charSequence);}
                    hI[index] = !(charSequence.toString().isEmpty());
                    enableButtonIfAllInputIsTrue();
                }
                @Override
                public void afterTextChanged(Editable editable) {}
            });
        }
    }

    // checks the validity of the AFM
    private void checkAfm(CharSequence charSequence) {
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
                textInputLayoutArray[1].setError(getString(R.string.admin_ssnError));
                AdminR1Activity1.this.hNE = true;
            }
        } else {
            textInputLayoutArray[1].setErrorEnabled(false);
            AdminR1Activity1.this.hNE = false;
        }
    }

    // enables admin_r1_1_button if every TextInputLayout has input (and more)
    private void enableButtonIfAllInputIsTrue() {
        button.setEnabled(hI[0] && hI[1] && hI[2] && hI[3] && hI[4] && hI[5] && hI[6] && !(hNE)
                && Objects.requireNonNull(textInputLayoutArray[1].getEditText()).getText().length() == 9
                && Objects.requireNonNull(textInputLayoutArray[6].getEditText()).getText().length() == 5);
    }

    // onClick for admin_r1_1_button Button
    public void addPhysio1(View v) {
        int result = 0;

        String url = "http://" + ip + "/myTherapy/insertClinic.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            result = okHttpHandler.insertOrUpdateClinic(url, Objects.requireNonNull(textInputLayoutArray[1].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[0].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[2].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[3].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[4].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[6].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[5].getEditText()).getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == 0) {
            Toast.makeText(AdminR1Activity1.this, "Ανεπιτυχής προσθήκη! Το ΑΦΜ αυτό υπάρχει ήδη.", Toast.LENGTH_LONG).show();
            onBackPressed();

        } else {
            Toast.makeText(AdminR1Activity1.this, "Το Φυσιοθεραπευτήριο έχει προστεθεί.", Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }
}