package com.example.aic601project.R1_R2;

import com.example.aic601project.ModelClinicsList;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminFragment1 extends Fragment implements RecyclerViewInterface {
    private final String ip = "temp";
    ModelClinicsList clinicsList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminFragment1 newInstance(String param1, String param2) {
        AdminFragment1 fragment = new AdminFragment1();
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
        // inflates the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin1, container, false);

        // setOnClickListener for the admin_fragment1_floatingActionButton
        rootView.findViewById(R.id.admin_fragment1_floatingActionButton).setOnClickListener(v -> addPhysioToList());

        // fetches the clinics from the myTherapy database
        clinicsList = new ModelClinicsList(ip);

        // initiates the RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.admin_fragment1_recyclerView);
        AdminFragment1Adapter adapter = new AdminFragment1Adapter(getActivity(), clinicsList.getClinics(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    // method for admin_fragment1_floatingActionButton
    public void addPhysioToList() {
        startActivity(new Intent(getActivity(), AdminR1Activity1.class));
        requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }

    // method for the RecyclerViewInterface / admin_fragment1_recyclerView
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), AdminR1Activity2.class);
        intent.putExtra("Clinic Info", clinicsList.getClinics().get(position));
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }
}