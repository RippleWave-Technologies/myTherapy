package com.example.aic601project.R1_R2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aic601project.MainActivity;
import com.example.aic601project.ModelService;
import com.example.aic601project.OkHttpHandler;
import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AdminR2Activity2 extends AppCompatActivity {
    // hI short for hasInput - logs whether a TextInputLayout has input
    private final boolean[] hI = {true, true, true, true, true, true, true};
    // textIntentInputLayoutArray - logs intent contents to a String[]
    private String[] intentStringArray;
    // textInputLayoutArray - logs all TextInputLayouts to a TextInputLayout[]
    private TextInputLayout[] textInputLayoutArray;
    // toolbar - admin_r2_2_topAppBar
    private MaterialToolbar toolbar;
    // button - admin_r2_2_button, deleteButton - admin_r2_2_deleteButton
    private Button button, deleteButton;
    // String - used to get the ip address from the MainActivity
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_r2_2);
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        // gets the IP from the MainActivity
        ip = MainActivity.getIP();

        // gets the intent and the ModelClinic object from the previous activity
        Intent intent = getIntent();
        ModelService service = intent.getParcelableExtra("Service Info");
        intentStringArray = setIntentStringArray(service);

        textInputLayoutArray = setTextInputLayoutArray(intentStringArray);

        button = findViewById(R.id.admin_r2_2_button);
        deleteButton = findViewById(R.id.admin_r2_2_deleteButton);
        toolbar = findViewById(R.id.admin_r2_2_topAppBar);
        setupToolbarWithBackButton();

        changeToViewLayout();

    }

    // creates a String[] with String values from the ModelClinic object
    private String[] setIntentStringArray(ModelService service) {
        String[] intentStringArray = new String[4];
        intentStringArray[0] = service.getName();
        intentStringArray[1] = service.getCode();
        intentStringArray[2] = service.getPrice();
        intentStringArray[3] = service.getDescription();
        return intentStringArray;
    }

    // creates a TextInputLayout[] with the Ids of all TextInputLayouts
    private TextInputLayout[] setTextInputLayoutArray(String[] intentStringArray) {
        TextInputLayout[] textInputLayoutArray = new TextInputLayout[4];
        textInputLayoutArray[0] = findViewById(R.id.admin_r2_2_textInputLayout_name);
        textInputLayoutArray[1] = findViewById(R.id.admin_r2_2_textInputLayout_code);
        textInputLayoutArray[2] = findViewById(R.id.admin_r2_2_textInputLayout_cost);
        textInputLayoutArray[3] = findViewById(R.id.admin_r2_2_textInputLayout_description);

        for (int i = 0; i < 4; i++) {
            Objects.requireNonNull(textInputLayoutArray[i].getEditText()).setText(intentStringArray[i]);}
        return textInputLayoutArray;
    }

    /*
     * sets up a toolbar where clicking the back button calls onBackPressed() or
     * changeToViewLayout() depending on the button's text
     */
    private void setupToolbarWithBackButton() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((View v) -> {
            switch (button.getText().toString()) {
                case "Επεξεργασία":
                    onBackPressed();
                    break;
                case "Αποθήκευση":
                    changeToViewLayout();
                    break;
            }
        });
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
        getMenuInflater().inflate(R.menu.admin_r2_app_bar_layout, menu);
        return true;
    }

    // checks every TextInputLayout for changed text and calls
    // enableButtonIfAllInputIsTrue
    private void checkForInput() {
        for (int i = 0; i < 4; i++) {
            final int index = i;
            Objects.requireNonNull(textInputLayoutArray[i].getEditText()).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    hI[index] = !(charSequence.toString().isEmpty());
                    enableButtonIfAllInputIsTrue();
                }
                @Override
                public void afterTextChanged(Editable editable) {}
            });
        }
    }

    // enables admin_r1_2_button if every TextInputLayout has input (and more)
    private void enableButtonIfAllInputIsTrue() {
        boolean allInput = (hI[0] && hI[1] && hI[2] && hI[3]);

        // checks if the input is different from the one passed in with the intent
        boolean newInput = false;
        for (int i = 0; i < 4; i++) {
            String txt = Objects.requireNonNull(textInputLayoutArray[i].getEditText()).getText().toString();
            if (!txt.equals(intentStringArray[i])) {newInput = true;}
        }
        button.setEnabled(allInput && newInput);
    }

    // onClick for admin_r2_2_button Button
    public void addService2_2(View v) {
        switch (button.getText().toString()) {
            case "Επεξεργασία":
                changeToEditLayout();
                break;
            case "Αποθήκευση":
                updateServiceData(ip);
                onBackPressed();
                break;
        }
    }

    // onClick for admin_r2_2_deleteButton Button
    public void deleteService(View v) {
        int result = 0;

        String url = "http://" + ip + "/myTherapy/deleteService.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            result = okHttpHandler.deleteService(url, Objects.requireNonNull(textInputLayoutArray[1].getEditText()).getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == 0) {
            Toast.makeText(AdminR2Activity2.this, "Η παροχή αυτή έχει προγραμματισμένα" +
                    " ραντεβού και δεν μπορεί να διαγραφεί.", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(AdminR2Activity2.this, "Η παροχή έχει διαγραφεί.", Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }

    // updates the service's data in the database
    private void updateServiceData(String ip) {
        String url = "http://" + ip + "/myTherapy/updateService.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            okHttpHandler.insertOrUpdateService(url, Objects.requireNonNull(textInputLayoutArray[1].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[0].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[2].getEditText()).getText().toString(),
                    Objects.requireNonNull(textInputLayoutArray[3].getEditText()).getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(AdminR2Activity2.this, "Τα στοιχεία της παροχής έχουν ενημερωθεί.", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    // enables/disables all TextInputLayouts
    private void fieldsEnableDisable(boolean enDis) {
        for (int i = 0; i < 4; i++) {
            textInputLayoutArray[i].setEnabled(enDis);
        }
    }

    // changes to the view styled layout
    private void changeToViewLayout() {
        // reverts any changes to the original input
        revertToOriginalInput();
        // changes the toolbar title and disables all TextInputLayout fields
        toolbar.setTitle("Πληροφορίες");
        fieldsEnableDisable(false);
        // sets the button to enable and changes it's text
        button.setEnabled(true);
        button.setText("Επεξεργασία");
        // sets the deleteButton to invisible
        deleteButton.setVisibility(View.INVISIBLE);
        // sets the navigation icon to back
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
    }

    // changes to the edit styled layout
    private void changeToEditLayout() {
        // changes the toolbar title and enables all** TextInputLayout fields
        toolbar.setTitle("Επεξεργασία");
        fieldsEnableDisable(true);
        textInputLayoutArray[1].setEnabled(false);
        // sets the button to disabled and changes it's text
        button.setEnabled(false);
        button.setText("Αποθήκευση");
        // sets the deleteButton to visible
        deleteButton.setVisibility(View.VISIBLE);
        // sets the navigation icon to close
        toolbar.setNavigationIcon(R.drawable.baseline_close_24);

        checkForInput();
    }


    // reverts any changes to the original input
    private void revertToOriginalInput() {
        for (int i = 0; i < 4; i++) {Objects.requireNonNull(textInputLayoutArray[i].getEditText()).setText(intentStringArray[i]);}
    }
}