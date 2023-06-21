package com.example.aic601project.R3_R8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class PhysicianR6Activity extends AppCompatActivity {

    // textIntentInputLayoutArray - logs intent contents to a String[]
    private String[] intentStringArray;
    // textInputLayoutArray - logs all TextInputLayouts to a TextInputLayout[]
    private TextInputLayout[] textInputLayoutArray;
    // toolbar - physician_appointmentInformationView_topAppBar
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r6);
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        toolbar = findViewById(R.id.physician_appointmentInformationView_topAppBar);
        setupToolbarWithBackButton();

        Intent intent = getIntent();

        intentStringArray = setIntentStringArray(intent);
        textInputLayoutArray = setTextInputLayoutArray(intentStringArray);
        fieldsEnableDisable(false);
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

    // creates a String[] with String values from the ModelClinic object
    private String[] setIntentStringArray(Intent intent) {
        String[] intentStringArray = new String[4];
        intentStringArray[0] = intent.getStringExtra("name").concat(" " + intent.getStringExtra("surname"));
        intentStringArray[1] = intent.getStringExtra("service");
        intentStringArray[2] = intent.getStringExtra("date").substring(0, 11);
        intentStringArray[3] = intent.getStringExtra("date").substring(11, 16);
        return intentStringArray;
    }

    // creates a TextInputLayout[] with the Ids of all TextInputLayouts
    private TextInputLayout[] setTextInputLayoutArray(String[] intentStringArray) {
        TextInputLayout[] textInputLayoutArray = new TextInputLayout[4];
        textInputLayoutArray[0] = findViewById(R.id.physician_appointmentInformationView_textInputLayout_name);
        textInputLayoutArray[1] = findViewById(R.id.physician_appointmentInformationView_textInputLayout_actions);
        textInputLayoutArray[2] = findViewById(R.id.physician_appointmentInformationView_textInputLayout_date);
        textInputLayoutArray[3] = findViewById(R.id.physician_appointmentInformationView_textInputLayout_time);

        for (int i = 0; i < 4; i++) {
            Objects.requireNonNull(textInputLayoutArray[i].getEditText()).setText(intentStringArray[i]);}
        return textInputLayoutArray;
    }

    // enables/disables all TextInputLayouts
    private void fieldsEnableDisable(boolean enDis) {
        for (int i = 0; i < 4; i++) {
            textInputLayoutArray[i].setEnabled(enDis);
        }

    }
}