package com.example.aic601project.R3_R8;

import com.example.aic601project.MainActivity;
import com.example.aic601project.ModelAppointment;
import com.example.aic601project.ModelAppointmentList;
import com.example.aic601project.ModelPatient;
import com.example.aic601project.R;
import com.example.aic601project.RecyclerViewInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhysicianFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhysicianFragment1 extends Fragment implements RecyclerViewInterface  {
    // ModelAppointmentList - used to get the appointments from the database
    private ModelAppointmentList appointmentList;
    // String - used to get the ip address from the MainActivity and the AFM from the PhysicianMainActivity
    private String ip, afm;
    // SwipeRefreshLayout - used to refresh the RecyclerView
    private SwipeRefreshLayout swipeRefreshLayout;
    // RecyclerView - used to display the clinics
    private RecyclerView recyclerView;
    // PhysicianFragment1And2Adapter - used to provide the data for the RecyclerView
    private PhysicianFragment1And2Adapter adapter;

    private HashMap<ModelAppointment, ModelPatient> appointmentPatientData;
    private ArrayList<ModelAppointment> appointments;
    private ArrayList<ModelPatient> patients;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhysicianFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhysicianFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static PhysicianFragment1 newInstance(String param1, String param2) {
        PhysicianFragment1 fragment = new PhysicianFragment1();
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
        View rootView = inflater.inflate(R.layout.fragment_physician1, container, false);

        // onClick listener for physician_imageButton_notifications
        rootView.findViewById(R.id.physician_imageButton_notifications).setOnClickListener(v -> launchPhysicianR7NotificationsActivity());
        // onClick listener for physician_imageButton_exit
        rootView.findViewById(R.id.physician_imageButton_exit).setOnClickListener(v -> goBackToLoginScreen());

        // gets the IP address from the MainActivity
        ip = MainActivity.getIP();

        // gets the AFM from the PhysicianMainActivity
        afm = PhysicianMainActivity.getAfm();

        fetchAndHash();

        // initiates the RecyclerView
        recyclerView = rootView.findViewById(R.id.physician_fragment1_recyclerView);
        adapter = new PhysicianFragment1And2Adapter(requireActivity(), appointments, patients, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        // initiates the SwipeRefreshLayout and sets the onRefreshListener
        swipeRefreshLayout = rootView.findViewById(R.id.physician_fragment1_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {

            fetchAndHash();

            adapter = new PhysicianFragment1And2Adapter(requireActivity(), appointments, patients, this);
            recyclerView.setAdapter(adapter);
            swipeRefreshLayout.setRefreshing(false);
        });

        return rootView;
    }

    // method for physician_imageButton_notifications
    private void launchPhysicianR7NotificationsActivity() {
        startActivity(new Intent(getActivity(), PhysicianR7Activity1.class));
        getActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }


    private void goBackToLoginScreen() {
        startActivity(new Intent(requireActivity(), MainActivity.class));
        requireActivity().finish();
    }

    // method for the RecyclerViewInterface / physician_fragment1_recyclerView
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(requireActivity(), PhysicianR8Activity.class);

        intent.putExtra("amka", patients.get(position).getAmka());
        intent.putExtra("name", patients.get(position).getName());
        intent.putExtra("surname", patients.get(position).getSurname());
        intent.putExtra("date", appointments.get(position).getDate());

        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }

    private void fetchAndHash(){
        // fetches the appropriate appointments from the myTherapy database
        appointmentList = new ModelAppointmentList(ip, afm, "", "PhysicianFragment1");
        appointmentPatientData = appointmentList.getAppointmentPatientData();

        appointments = new ArrayList<>();
        patients = new ArrayList<>();

        for(ModelAppointment appointment: appointmentPatientData.keySet()) {
            ModelPatient patient = appointmentPatientData.get(appointment);
            appointments.add(appointment);
            patients.add(patient);
        }
    }
}