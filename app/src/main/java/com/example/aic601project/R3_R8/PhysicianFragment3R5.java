package com.example.aic601project.R3_R8;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.aic601project.MainActivity;
import com.example.aic601project.Patient;
import com.example.aic601project.PatientList;
import com.example.aic601project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhysicianFragment3R5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhysicianFragment3R5 extends Fragment {

    private String ip;
    private RecyclerView recyclerView;
    private PatientList patients;
    private ArrayList<Patient> filteredList;
    private PhysicianFragment3R5NewAdapter patientsAdapter;
    private SearchView searchView;
    private ImageView searchViewImage;
    private PhysicianFragment3R5NewAdapter.RecyclerViewClickListener listener;
    private FloatingActionButton button;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BottomNavigationView bottomBar;
    private final String TAG = "PhysicianFragment3R5";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhysicianFragment3R5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhysicianFragment3R5.
     */
    // TODO: Rename and change types and number of parameters
    public static PhysicianFragment3R5 newInstance(String param1, String param2) {
        PhysicianFragment3R5 fragment = new PhysicianFragment3R5();
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

        //For preventing the bottom navigation bar and the add button
        //to going up when the keyboard is showing
        KeyboardVisibilityEvent.setEventListener(
                getActivity(),
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        Log.d(TAG,"onVisibilityChanged: Keyboard visibility changed");
                        if(isOpen){
                            Log.d(TAG, "onVisibilityChanged: Keyboard is open");
                            bottomBar.setVisibility(View.GONE);
                            button.setVisibility(View.GONE);
                            Log.d(TAG, "onVisibilityChanged: NavBar got Invisible");
                        }else{
                            Log.d(TAG, "onVisibilityChanged: Keyboard is closed");
                            bottomBar.setVisibility(View.VISIBLE);
                            button.setVisibility(View.VISIBLE);
                            Log.d(TAG, "onVisibilityChanged: NavBar got Visible");
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_physician3, container, false);

        ip = MainActivity.getIP();

        patients = new PatientList(ip);
        filteredList = new ArrayList<>();
        for (Patient p : patients.getPatients()) {
            filteredList.add(p);
        }

        bottomBar = getActivity().findViewById(R.id.physician_bottom_navigation_view);
        button = rootView.findViewById(R.id.physician_r5_floatingActionButton);

        recyclerView = rootView.findViewById(R.id.physician_r5_recyclerView);
        recyclerView.setHasFixedSize(true);

        setOnClickListener();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        patientsAdapter = new PhysicianFragment3R5NewAdapter(patients.getPatients(), listener);
        recyclerView.setAdapter(patientsAdapter);
        recyclerView.requestFocus();

        searchViewImage = rootView.findViewById((R.id.physician_r5_imageview_icon));
        searchView = rootView.findViewById((R.id.physician_r5_searchView));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchViewImage.setVisibility(View.GONE);
                filteredList = new ArrayList<>();
                for (Patient p : patients.getPatients()) {
                    if (p.getName().toLowerCase().contains(newText.toLowerCase()) || p.getSurname().toLowerCase().contains(newText.toLowerCase())) {
                        filteredList.add(p);
                    }
                }

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                patientsAdapter = new PhysicianFragment3R5NewAdapter(filteredList, listener);
                recyclerView.setAdapter(patientsAdapter);

                if(newText.length()==0)
                    searchViewImage.setVisibility(View.VISIBLE);

                return true;
            }
        });

        button.setOnClickListener(view -> {
            // Intent intent = new Intent(getApplicationContext(),
            // PhysicianR3Acticity.class);
            // startActivity(intent);
            // requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom,
            // R.anim.no_slide_in_or_out);
        });

        swipeRefreshLayout = rootView.findViewById(R.id.physician_r5_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            patients = new PatientList(ip);
            patientsAdapter = new PhysicianFragment3R5NewAdapter(patients.getPatients(), listener);
            recyclerView.setAdapter(patientsAdapter);
            swipeRefreshLayout.setRefreshing(false);
        });

        return rootView;
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(getContext(), PhysicianR4Activity.class);
            intent.putExtra("patient", filteredList.get(position));
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
        };
    }
}