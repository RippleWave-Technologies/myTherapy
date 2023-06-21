package com.example.aic601project.R1_R2;

import com.example.aic601project.MainActivity;
import com.example.aic601project.OkHttpHandler;
import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AdminR2Activity1 extends AppCompatActivity {

    private Button add;

    // String - used to get the ip address from the MainActivity
    private String ip;

    // toolbar - admin_r2_1_topAppBar
    private MaterialToolbar toolbar;
    private TextInputEditText nameText;
    private TextInputEditText costText;
    private TextInputEditText codeText;
    private TextInputEditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_r2_1);

        // gets the IP from the MainActivity
        ip = MainActivity.getIP();

        // δημιουργω τα text πεδια
        nameText = findViewById(R.id.admin_r2_1_textInputLayout_name_editText);
        costText = findViewById(R.id.admin_r2_1_textInputLayout_cost_editText);
        codeText = findViewById(R.id.admin_r2_1_textInputLayout_code_editText);
        descriptionText = findViewById((R.id.admin_r2_1_textInputLayout_description_editText));
        add = findViewById(R.id.admin_r2_1_button);
        // προσθετω την διαδικασια
        nameText.addTextChangedListener(longinTextWatcher);
        costText.addTextChangedListener(longinTextWatcher);
        codeText.addTextChangedListener(longinTextWatcher);
        descriptionText.addTextChangedListener(longinTextWatcher);

        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        toolbar = findViewById(R.id.admin_r2_1_topAppBar);
        setupToolbarWithBackButton();
    }

    // sets up a toolbar where clicking the back button calls onBackPressed()
    private void setupToolbarWithBackButton() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    // overrides the default onBackPressed() function and includes an exit animation
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

    // εδω φτιαχνω την διαδικασια TextWatcher η οποια θα κανει το κουμπι enable οταν
    // συμπληρωνονται τα πεδια
    private TextWatcher longinTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = nameText.getText().toString();
            String costInput = costText.getText().toString();
            String codeInput = codeText.getText().toString();
            String descriptionInput = descriptionText.getText().toString();

            add.setEnabled(!nameInput.isEmpty() && !costInput.isEmpty() && !codeInput.isEmpty()
                    && !descriptionInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    // onClick for admin_r2_1_button Button
    public void addService2_1(View v) {
        int result = 0;

        Log.d("imtestingbro", "mphke");

        String url = "http://" + ip + "/myTherapy/insertService.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            result = okHttpHandler.insertOrUpdateService(url, Objects.requireNonNull(codeText.getText()).toString(),
                    Objects.requireNonNull(nameText.getText()).toString(),
                    Objects.requireNonNull(costText.getText()).toString(),
                    Objects.requireNonNull(descriptionText.getText()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == 0) {
            Toast.makeText(AdminR2Activity1.this, "Ανεπιτυχής προσθήκη! Ο κωδικός παροχής αυτός υπάρχει ήδη.", Toast.LENGTH_LONG).show();
            onBackPressed();

        } else {
            Toast.makeText(AdminR2Activity1.this, "Η παροχή έχει προστεθεί.", Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }
}