package com.example.aic601project.R3_R8;

import com.example.aic601project.MainActivity;
import com.example.aic601project.ModelServiceList;
import com.example.aic601project.OkHttpHandler;
import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PhysicianR8Activity extends AppCompatActivity {
    // toolbar - physician_r8_topAppBar
    private MaterialToolbar toolbar;
    String[] serviceNames, serviceCodes;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    // ModelServiceList - used to get the services from the database
    ModelServiceList servicesList;
    // String - used to get the ip address from the MainActivity and the AFM from the PhysicianMainActivity
    private String ip, afm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r8);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));
        toolbar = findViewById(R.id.physician_r8_topAppBar);
        setupToolbarWithBackButton();

        Intent intent = getIntent();
        String amka = intent.getStringExtra("amka");
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String date = intent.getStringExtra("date");

        ((TextInputEditText)(findViewById(R.id.physician_r8_textInputEditText_patient))).setText(name.concat(" " + surname));
        ((TextInputEditText)(findViewById(R.id.physician_r8_textInputEditText_date))).setText(date.substring(0, 10));
        ((TextInputEditText)(findViewById(R.id.physician_r8_textInputEditText_time))).setText(date.substring(11, 16));

        // gets the IP address from the MainActivity
        ip = MainActivity.getIP();

        // gets the AFM from the PhysicianMainActivity
        afm = PhysicianMainActivity.getAfm();

        // fetches the clinics from the myTherapy database
        servicesList = new ModelServiceList(ip);

        String[] serviceNames = new String[servicesList.getServices().size()];
        String[] serviceCodes = new String[servicesList.getServices().size()];
        for (int i = 0; i < serviceNames.length; i++) {
            serviceNames[i] = servicesList.getServices().get(i).getName();
            serviceCodes[i] = servicesList.getServices().get(i).getCode();
        }

        autoCompleteTxt = findViewById(R.id.physician_r8_autoCompleteTextView_actions);
        adapterItems = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, serviceNames);
        autoCompleteTxt.setAdapter(adapterItems);

        findViewById(R.id.physician_r8_Button_reject).setOnClickListener(v -> { updateAppointmentStatus(amka, afm, date, "0",
                serviceNames, serviceCodes, autoCompleteTxt.getText().toString()); });
        findViewById(R.id.physician_r8_Button_accept).setOnClickListener(v -> { updateAppointmentStatus(amka, afm, date, "1",
                serviceNames, serviceCodes, autoCompleteTxt.getText().toString()); });

    }

    private void updateAppointmentStatus(String amka, String afm, String date, String act, String[] serviceNames,
                                         String[] serviceCodes, String selectedService) {

        if (selectedService.equals("") && act.equals("1")) {
            Toast.makeText(this, "Please select a service", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < serviceNames.length; i++) {
                if (serviceNames[i].equals(selectedService)) {
                    selectedService = serviceCodes[i];
                    break;
                }
            }
            if (act.equals("0")) { selectedService = "uAS"; }
            String url = "http://" + ip + "/myTherapy/updateClinicPatientRequestedOrConfirmedAppointments.php";
            try {
                OkHttpHandler okHttpHandler = new OkHttpHandler();
                okHttpHandler.updateClinicPatientRequestedOrConfirmedAppointments(url, amka, afm, date, act, selectedService);
            } catch (Exception e) {
                e.printStackTrace();
            }
            onBackPressed();
        }
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
        getMenuInflater().inflate(R.menu.physician_r8_app_bar_layout, menu);
        return true;
    }
}