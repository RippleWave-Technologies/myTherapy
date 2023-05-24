package com.example.aic601project.R9_R10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aic601project.R;

public class UserR9MainActivity extends AppCompatActivity {

    private AutoCompleteTextView date;
    private AutoCompleteTextView physio;
    private AutoCompleteTextView time;

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

        /**autocomplete text view: Physio*/
         physio = findViewById(R.id.textView_physio);
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
         android.R.layout.simple_list_item_1, PHYSIOS);
         physio.setAdapter(adapter);

         /**autocomplete text view: Time*/
         time = findViewById(R.id.text_Time);
         ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
         android.R.layout.simple_list_item_1, APPOINTMENTS_HOURS_AVAILABLE);
         time.setAdapter(adapter2);



         /** clickable text to clear the fields, void will clear all fields*/
         TextView txtClear = (TextView)findViewById(R.id.textView_clear);

         txtClear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
        });

    }




}