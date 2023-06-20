package com.example.aic601project.R1_R2;

import com.example.aic601project.MainActivity;
import com.example.aic601project.ModelServiceList;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminFragment2 extends Fragment implements RecyclerViewInterface {

    // ModelServiceList - used to get the services from the database
    ModelServiceList servicesList;
    // String - used to get the ip address from the MainActivity
    private String ip;
    // SwipeRefreshLayout - used to refresh the RecyclerView
    SwipeRefreshLayout swipeRefreshLayout;
    // RecyclerView - used to display the clinics
    RecyclerView recyclerView;
    // AdminFragment2Adapter - used to provide the data for the RecyclerView
    AdminFragment2Adapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminFragment2 newInstance(String param1, String param2) {
        AdminFragment2 fragment = new AdminFragment2();
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
        View rootView = inflater.inflate(R.layout.fragment_admin2, container, false);

        // gets the IP address from the MainActivity
        ip = MainActivity.getIP();

        // setOnClickListener for the admin_fragment1_floatingActionButton
        rootView.findViewById(R.id.admin_fragment2_floatingActionButton).setOnClickListener(v -> addServiceToList());

        // fetches the clinics from the myTherapy database
        servicesList = new ModelServiceList(ip);

        // initiates the RecyclerView
        recyclerView = rootView.findViewById(R.id.admin_fragment2_recyclerView);
        adapter = new AdminFragment2Adapter(requireActivity(), servicesList.getServices(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        // initiates the SwipeRefreshLayout and sets the onRefreshListener
        swipeRefreshLayout = rootView.findViewById(R.id.admin_fragment2_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            servicesList = new ModelServiceList(ip);
            adapter = new AdminFragment2Adapter(requireActivity(), servicesList.getServices(), this);
            recyclerView.setAdapter(adapter);
            swipeRefreshLayout.setRefreshing(false);
        });

        return rootView;
    }

    // method for admin_fragment2_floatingActionButton
    public void addServiceToList() {
        startActivity(new Intent(getActivity(), AdminR2Activity.class));
        requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }

    // method for the RecyclerViewInterface / admin_fragment2_recyclerView
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(requireActivity(), AdminR1Activity2.class);
//        intent.putExtra("Service Info", servicesList.getServices().get(position));
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
    }
}