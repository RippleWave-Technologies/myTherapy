package com.example.aic601project.R9_R10;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aic601project.R;
import com.example.aic601project.R1_R2.AdminR1Activity;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment1R9#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment1R9 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button button;
    private TextInputEditText date;
    private AutoCompleteTextView physio, time;
    private TextView yes_text; //for the dialog box
    private TextView no_text; //for the dialog box

    //autocomplete textView Physio, the values for the PHYSIOS will be taken from the db
    private static final String[] PHYSIOS = new String[]{ "Name1", "BName", "DName" };

    //autocomplete textView Time, the values of the time will be taken from doctor's available times from the db (??)
    private static final String[] APPOINTMENTS_HOURS_AVAILABLE = new String[]{ "9:00", "10:00", "11:00" };

    public UserFragment1R9() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment1R9.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment1R9 newInstance(String param1, String param2) {
        UserFragment1R9 fragment = new UserFragment1R9();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Enabling options menu in this fragment
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user1, container, false);


        //dialog box to appear when button is clicked
        button = rootView.findViewById(R.id.user_r9_button);
        View alertUserDialog = LayoutInflater.from(getActivity()).inflate(R.layout.user_r9_dialog, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setView(alertUserDialog);

        final AlertDialog dialog2 = alertDialog.create();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog2.show();

            }
        });

        //for the items I search the alertUserDialog view
        //when no is pressed in the dialog box
        TextView noText = alertUserDialog.findViewById(R.id.no_textfield);
        noText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.cancel();
            }
        });

        //when yes is pressed in the dialog box
        TextView yesText = alertUserDialog.findViewById(R.id.yes_textfield);
        yesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.cancel();
                Toast.makeText(getActivity(), "Yes text pressed", Toast.LENGTH_SHORT).show();
            }
        });

        //pop-up calendar
        date = rootView.findViewById(R.id.user_r9_textInputEditText_date);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_WEEK);

        date.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), (view, year1, month1, dayOfMonth) -> {
                month1 = month1 + 1;
                String new_date = day + "/" + month1 + "/" + year1;
                date.setText(new_date);
            }, year, month, day);
            dialog.show();
        });

        //autocomplete text view: Physio
        physio = rootView.findViewById(R.id.user_r9_autoCompleteTextView_physio);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, PHYSIOS);
        physio.setAdapter(adapter);

        //autocomplete text view: Time
        time = rootView.findViewById(R.id.user_r9_autoCompleteTextView_time);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, APPOINTMENTS_HOURS_AVAILABLE);
        time.setAdapter(adapter2);


        // clickable text to clear the fields
        ((TextView)(rootView.findViewById(R.id.user_r9_textView_clear))).setOnClickListener(v -> {
            physio.setText("");
            time.setText("");
            date.setText("");
        });

        return rootView;
    }
}