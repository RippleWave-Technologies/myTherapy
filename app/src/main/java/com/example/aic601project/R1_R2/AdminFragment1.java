package com.example.aic601project.R1_R2;

import com.example.aic601project.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminFragment1 extends Fragment {

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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin1, container, false);

        // setOnClickListener for the admin_fragment1_floatingActionButton
        rootView.findViewById(R.id.admin_fragment1_floatingActionButton).setOnClickListener(v -> addPhysioToList());

        // setOnClickListener for the admin_fragment1_button_temp
        rootView.findViewById(R.id.admin_fragment1_button_temp).setOnClickListener(v -> viewUserData());

        return rootView;
    }

    // method for admin_fragment1_floatingActionButton
    public void addPhysioToList() {
        startActivity(new Intent(getActivity(), AdminR1Activity.class));
        requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }

    // method for adminR1_button_users
    public void viewUserData() {
        Intent i = new Intent(getActivity(), AdminR1Activity.class);
        i.putExtra("key", 2);
        startActivity(i);
        requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }

}