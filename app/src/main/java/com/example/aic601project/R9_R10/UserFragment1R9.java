package com.example.aic601project.R9_R10;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.example.aic601project.R;
import com.example.aic601project.R1_R2.AdminR1Activity;

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

    private AutoCompleteTextView date;
    private AutoCompleteTextView physio;
    private AutoCompleteTextView time;

    //autocomplete textView Physio, the values for the PHYSIOS will be taken from the db
    private static final String[] PHYSIOS = new String[]{
            "Name1", "BName", "DName"
    };

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
       return inflater.inflate(R.layout.activity_user_main, container, false);


    }

    public void startActivity() {
        startActivity(new Intent(getActivity(), UserR9MainActivity.class));
        requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }

}