package com.example.aic601project.R1_R2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

public class AdminR2Activity extends AppCompatActivity {

    private Button add;


    // toolbar - admin_r2_topAppBar
    private MaterialToolbar toolbar;
    private TextInputEditText nameText;
    private TextInputEditText costText;
    private TextInputEditText codeText;
    private TextInputEditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_r2);
        //δημιουργω τα text πεδια
       nameText = findViewById(R.id.admin_r2_textInputLayout_name_editText);
       costText = findViewById(R.id.admin_r2_textInputLayout_cost_editText);
       codeText = findViewById(R.id.admin_r2_textInputLayout_code_editText);
       descriptionText = findViewById((R.id.admin_r2_textInputLayout_description_editText));
       add=findViewById(R.id.admin_r2_button);
// προσθετω την διαδικασια
       nameText.addTextChangedListener(longinTextWatcher);
       costText.addTextChangedListener(longinTextWatcher);
       codeText.addTextChangedListener(longinTextWatcher);
       descriptionText.addTextChangedListener(longinTextWatcher);
       add = findViewById(R.id.admin_r2_button);


        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        toolbar = findViewById(R.id.admin_r2_topAppBar);
        setupToolbarWithBackButton();

        add = findViewById(R.id.admin_r2_button);
        add.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Η παροχή προστέθηκε", Toast.LENGTH_SHORT).show());
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

    /* overrides the onCreateOptionsMenu because by calling setSupportActionBar
     * the menu will be populated with standard system menu items */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_r2_app_bar_layout, menu);
        return true;
    }

    //εδω φτιαχνω την διαδικασια TextWatcher η οποια θα κανει το κουμπι enable οταν συμπληρωνονται τα πεδια
    private TextWatcher longinTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = nameText.getText().toString().trim();
            String costInput = costText.getText().toString().trim();
            String codeInput = codeText.getText().toString().trim();
            String descriptionInput = descriptionText.getText().toString().trim();

            add.setEnabled( !nameInput.isEmpty() && !costInput.isEmpty() && !codeInput.isEmpty() && !descriptionInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}

// TODO
// clicking admin_r2_button when in "Νέα Παροχή" saves the data to the database
// create a recycleview for AdminFragment2 that displays the services from the database
// clicking on a service in the recycleview will display the service details in AdminR1Activity
// when in "Επεξεργασία" the system checks for changes in the data and if there are and admin_r2_button gets clicked it saves the changes to the database