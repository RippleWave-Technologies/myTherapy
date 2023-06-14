package com.example.aic601project.R9_R10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aic601project.R;
import com.example.aic601project.R10ListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment2R10#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment2R10 extends Fragment {

    private RecyclerView recyclerView;
    private UserFragment2R10Adapter buttonAdapter;
    private List<R10ListItem> itemList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment2R10() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment2R10.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment2R10 newInstance(String param1, String param2) {
        UserFragment2R10 fragment = new UserFragment2R10();
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
        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user2, container, false);

        recyclerView = rootView.findViewById(R.id.R10recyclerView);

        // Create the list of List_Item objects
        itemList = new ArrayList<>();
        itemList.add(new R10ListItem("2023-06-03", "Παπανικολάου", "42$"));
        itemList.add(new R10ListItem("2023-06-04", "Παπαγεωργίου", "24$"));
        itemList.add(new R10ListItem("2023-06-05", "Παπανικολάου", "12$"));
        itemList.add(new R10ListItem("2023-06-05", "Παπανικολάου", "12$"));
        itemList.add(new R10ListItem("2023-06-05", "Παπανικολάου", "12$"));
        itemList.add(new R10ListItem("2023-06-05", "Παπανικολάου", "12$"));
        itemList.add(new R10ListItem("2023-06-05", "Παπανικολάου", "12$"));


        // Create the adapter with the list of items
        buttonAdapter = new UserFragment2R10Adapter(itemList);

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(buttonAdapter);

        // Set the layout manager for the RecyclerView (e.g., LinearLayoutManager, GridLayoutManager)
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return rootView;
    }
}