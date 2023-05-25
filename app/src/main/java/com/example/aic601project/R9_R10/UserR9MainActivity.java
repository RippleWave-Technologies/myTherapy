package com.example.aic601project.R9_R10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import java.util.Calendar;


import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

public class UserR9MainActivity extends AppCompatActivity {

    private AutoCompleteTextView date;
    private AutoCompleteTextView physio;
    private AutoCompleteTextView time;

    private TextInputLayout textDate, textPhysio, textTime;

    //autocomplete textView Physio, the values for the PHYSIOS will be taken from the db
    private static final String[] PHYSIOS = new String[]{
            "Name1", "BName", "DName"
    };

    //autocomplete textView Time, the values of the time will e taken from doctor's available times from the db (??)
    private static final String[] APPOINTMENTS_HOURS_AVAILABLE = new String[]{
            "9:00", "10:00", "11:00"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);


        /**assign variables to the textinputlayout
        textDate = findViewById(R.id.textInputLayout_date);
        textPhysio = findViewById(R.id.textInputLayout_physio);
        textTime = findViewById(R.id.textInputLayout_time);

        //pop up calendar
        date = findViewById(R.id.text_Date);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_WEEK);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(UserR9MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String new_date = day + "/" + month + "/" + year;
                        date.setText(new_date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        /**autocomplete text view: Physio
        physio = findViewById(R.id.textView_physio);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, PHYSIOS);
        physio.setAdapter(adapter);

        /**autocomplete text view: Time
        time = findViewById(R.id.text_Time);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, APPOINTMENTS_HOURS_AVAILABLE);
        time.setAdapter(adapter2);


        /** clickable text to clear the fields, void will clear all fields
        TextView txtClear = (TextView) findViewById(R.id.textView_clear);

        txtClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });**/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notif_bell:
                /**startActivity(new Intent(getActivity(), UserR9MainActivity.class));
                requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);**/
                Toast.makeText(this, "Item Selected", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}










