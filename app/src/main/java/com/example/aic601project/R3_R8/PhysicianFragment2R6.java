package com.example.aic601project.R3_R8;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import com.example.aic601project.MainActivity;
import com.example.aic601project.ModelAppointment;
import com.example.aic601project.ModelAppointmentList;
import com.example.aic601project.ModelPatient;
import com.example.aic601project.ModelService;
import com.example.aic601project.R;
import com.example.aic601project.RecyclerViewInterface;
import com.google.android.material.textfield.TextInputLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhysicianFragment2R6#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhysicianFragment2R6 extends Fragment implements RecyclerViewInterface {

    // ModelAppointmentList - used to get the appointments from the database
    private ModelAppointmentList appointmentList;
    // String - used to get the ip address from the MainActivity, the AFM from the PhysicianMainActivity and the date from the Calendar dialog
    private String ip, afm, date;
    // SwipeRefreshLayout - used to refresh the RecyclerView
    private SwipeRefreshLayout swipeRefreshLayout;
    // RecyclerView - used to display the clinics
    private RecyclerView recyclerView;
    // PhysicianFragment1And2Adapter - used to provide the data for the RecyclerView
    private PhysicianFragment1And2Adapter adapter;

    private HashMap<HashMap<ModelAppointment, ModelPatient>, ModelService> appointmentPatientServiceData;
    private HashMap<ModelAppointment, ModelPatient> appointmentPatientData;
    private ArrayList<HashMap<ModelAppointment, ModelPatient>> innerHashMap;
    private ArrayList<ModelAppointment> appointments;
    private ArrayList<ModelPatient> patients;
    private ArrayList<ModelService> services;

    private TextInputLayout calendarLayout;
    private Calendar calendar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhysicianFragment2R6() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhysicianFragment2R6.
     */
    // TODO: Rename and change types and number of parameters
    public static PhysicianFragment2R6 newInstance(String param1, String param2) {
        PhysicianFragment2R6 fragment = new PhysicianFragment2R6();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_physician2, container, false);

        calendarLayout = rootView.findViewById(R.id.physician_r6_textInputLayout_calendar);
        calendar = Calendar.getInstance();
        rootView.findViewById(R.id.imageButtonCalendar).setOnClickListener(v -> showDatePicker());

        calendarLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Do nothing */ }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { /* Do nothing */ }
            @Override
            public void afterTextChanged(Editable s) {
                if (!calendarLayout.getEditText().getText().toString().isEmpty()){
                    date = calendarLayout.getEditText().getText().toString();
                    showAppointments(date);
                }
            }
        });

        return rootView;
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, year1, month1, dayOfMonth1) -> {
                    String selectedDate = String.format(Locale.getDefault(), "%04d/%02d/%02d", year1,
                            (month1 + 1), dayOfMonth1);
                    calendarLayout.getEditText().setText(selectedDate);
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void showAppointments(String date) {
        // gets the IP address from the MainActivity
        ip = MainActivity.getIP();

        // gets the AFM from the PhysicianMainActivity
        afm = PhysicianMainActivity.getAfm();

        fetchAndHash();

        // initiates the RecyclerView
        recyclerView = requireActivity().findViewById(R.id.physician_fragment2_recyclerView);
        adapter = new PhysicianFragment1And2Adapter(requireActivity(), appointments, patients, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        // initiates the SwipeRefreshLayout and sets the onRefreshListener
        swipeRefreshLayout = requireActivity().findViewById(R.id.physician_fragment2_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {

            fetchAndHash();

            adapter = new PhysicianFragment1And2Adapter(requireActivity(), appointments, patients, this);
            recyclerView.setAdapter(adapter);
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    // method for the RecyclerViewInterface / physician_fragment1_recyclerView
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(requireActivity(), PhysicianActivityAppointmentInformationView.class);

        intent.putExtra("name", patients.get(position).getName());
        intent.putExtra("surname", patients.get(position).getSurname());
        intent.putExtra("date", appointments.get(position).getDate());
        intent.putExtra("service", services.get(position).getName());

        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }

    private void fetchAndHash(){
        // fetches the appropriate appointments from the myTherapy database
        appointmentList = new ModelAppointmentList(ip, afm, date, "PhysicianFragment2");
        appointmentPatientServiceData = appointmentList.getAppointmentPatientServiceData();

        innerHashMap = new ArrayList<>();
        services = new ArrayList<>();

        // HashMap<HashMap<ModelAppointment, ModelPatient>, ModelService>
        for(HashMap<ModelAppointment, ModelPatient> hashMap : appointmentPatientServiceData.keySet()) {
            ModelService service = appointmentPatientServiceData.get(hashMap);
            services.add(service);
            innerHashMap.add(hashMap);
        }

        appointments = new ArrayList<>();
        patients = new ArrayList<>();

        for (int i = 0; i < innerHashMap.size(); i++){
            appointmentPatientData = innerHashMap.get(i);
            // HashMap<ModelAppointment, ModelPatient>
            for(ModelAppointment appointment: appointmentPatientData.keySet()) {
                ModelPatient patient = appointmentPatientData.get(appointment);
                appointments.add(appointment);
                patients.add(patient);
            }
        }
    }
}
