package com.example.aic601project.R4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

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
        showDialog();

    }

    private void showDialog(){
        final Dialog dialog  = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_history_sheet_layout);

        LinearLayout editLayout = dialog.findViewById(R.id.layoutEdit);
        LinearLayout editLayout2 = dialog.findViewById(R.id.layoutEdit2);
        LinearLayout editLayout3 = dialog.findViewById(R.id.layoutEdit3);
        editLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Toast.makeText(PhysicianR4Activity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;

        dialog.getWindow().setGravity(Gravity.BOTTOM);

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
    public void clearForm(View v){

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