package com.example.aic601project.R9_R10;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.aic601project.MainActivity;
import com.example.aic601project.ModelAppointment;
import com.example.aic601project.ModelClinic;
import com.example.aic601project.ModelClinicList;
import com.example.aic601project.OkHttpHandler;
import com.example.aic601project.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    private Spinner nearbyClinics;
    private AutoCompleteTextView physio, time;
    private TextView yes_text; //for the dialog box
    private TextView no_text; //for the dialog box
    private TextView userName_topAppBar; //search in the activity user main
    private OkHttpHandler okHttpHandler;
    private ModelAppointment requestedAppointment;

    private String selectedPhysioAFM;
    private String selectedDate;
    private String selectedTime;
    private String myIP = MainActivity.getIP();
    private String userAMKA = UserMainActivity.getAmka();
    private String userPostCode;
    private String userName;
    private String selectedClinicName;

    //autocomplete textView Physio, the values for the PHYSIOS will be taken from the db
    private ModelClinicList physioList;
    //private static final String[] PHYSIOS = new String[]{"Name1", "BName", "DName"};

    //autocomplete textView Time, the values of the time will be taken from doctor's available times from the db (??)
    private static final String[] APPOINTMENTS_HOURS = new String[]{"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
            "13:00", "13:30", "14:00", "14:30", "15:00"};

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

        //autocomplete text view: Physio
        physio = rootView.findViewById(R.id.user_r9_autoCompleteTextView_physio);

        //retrieve the names of the clinics from the OkHttpHandler
        physioList = new ModelClinicList(myIP);
        ArrayList<ModelClinic> clinics = physioList.getClinics();

        ArrayList<String> clinicNames = new ArrayList<>();

        //extract clinic names and add them to the ArrayList
        for (ModelClinic clinic : clinics) {
            clinicNames.add(clinic.getPhysioName());
        }

        //Initialize and set up the ArrayAdapter for the physio textView
        ArrayAdapter<String> physioAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, clinicNames);
        physio.setAdapter(physioAdapter);

        //when an item is Clicked I want to keep the AFM to associate with the appointment
        physio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedClinicName = (String) parent.getItemAtPosition(position);

                //find the corresponding ModelClinic object
                ModelClinic selectedClinic = null;
                for (ModelClinic clinic : clinics) {
                    if (clinic.getPhysioName().equals(selectedClinicName)) {
                        selectedClinic = clinic;
                        break;
                    }
                }

                if (selectedClinic != null) {
                    selectedPhysioAFM = selectedClinic.getPhysioAFM();
                }

            }
        });


        //autocomplete text view: Time
        time = rootView.findViewById(R.id.user_r9_autoCompleteTextView_time);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, APPOINTMENTS_HOURS);
        time.setAdapter(adapter2);


        //set an OnItemClickedListener to get the selected time
        time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTime = (String) parent.getItemAtPosition(position);
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
                //we retrieve the selected date in selectedDate
                selectedDate = year1 + "-" + String.format("%02d", month1) + "-" + String.format("%02d", dayOfMonth);
                date.setText(selectedDate);
                Log.d("Selected date: ", selectedDate);
            }, year, month, day);
            dialog.show();
        });

        // clickable text to clear the fields
        ((TextView) (rootView.findViewById(R.id.user_r9_textView_clear))).setOnClickListener(v -> {
            clearText(physio, time, date);
        });


        // dialog box to appear when button is clicked
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

                if (selectedTime != null && selectedDate != null && selectedPhysioAFM != null) {
                    //combine the time and date into a single string with the correct format
                    String combinedDateTime = selectedDate + " " + selectedTime + ":00";
                    //passing the String combinedDateTime and selectedPhysioAFM to a method that will create the appointment object
                    requestedAppointment = createAppointment(combinedDateTime, selectedPhysioAFM);

                    try {
                        insertApppointment(requestedAppointment);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    //in case the user hasn't filled all necessary text fields
                    Toast.makeText(getActivity(), "Παρακαλώ εισάγετε όλα τα πεδία.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    public ModelAppointment createAppointment(String dateTime, String selectedPhysioAFM) {
        String amka = userAMKA;
        String afm = selectedPhysioAFM;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar new_calendar = Calendar.getInstance();
        try {
            new_calendar.setTime(dateFormat.parse(dateTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date selectedDateTime = new_calendar.getTime();
        String formattedDateTime = dateFormat.format(selectedDateTime);

        String status = "1";
        String service = "uAS";

        return new ModelAppointment(amka, afm, formattedDateTime, status, service);

    }

    public void insertApppointment(ModelAppointment appointment) throws IOException {
        int result = 0;
        //create the url for the php file
        String url = "http://" + myIP + "/myTherapy/insertClinicPatientRequestedAppointment.php";

        okHttpHandler = new OkHttpHandler();

        result = okHttpHandler.insertClinicPatientRequestedAppointment(url, appointment.getDate(), appointment.getAmka(), appointment.getAfm());

        if(result == 0){
            Toast.makeText(getActivity(),"Η ώρα και η ημέρα που ζητήσατε ήδη υπάρχει, παρακαλώ διαλέξτε εκ νέου", Toast.LENGTH_SHORT ).show();
            clearText(physio, time, date);
        }else{
            Toast.makeText(getActivity(), "Το ραντεβού σας εκχωρήθηκε επιτυχώς", Toast.LENGTH_SHORT).show();
            clearText(physio, time, date);
        }
    }

    public void clearText(AutoCompleteTextView physio, AutoCompleteTextView time, TextInputEditText date){
        physio.setText("");
        time.setText("");
        date.setText("");
    }
}