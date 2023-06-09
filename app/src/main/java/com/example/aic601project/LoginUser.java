package com.example.aic601project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.aic601project.R9_R10.UserMainActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginUser#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class LoginUser extends Fragment {
    TextInputLayout parameter1, parameter2;
    private Button button, shortcut;
    private String ip;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginUser.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginUser newInstance(String param1, String param2) {
        LoginUser fragment = new LoginUser();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LoginUser() {
        // Required empty public constructor
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
        View rootView = inflater.inflate(R.layout.fragment_login_user, container, false);

        // gets the IP from the MainActivity
        ip = MainActivity.getIP();

        button = rootView.findViewById(R.id.login_user_button);
//        shortcut = rootView.findViewById(R.id.shortcut_login_user_button);

        parameter1 = rootView.findViewById(R.id.login_user_parameter1);
        parameter2 = rootView.findViewById(R.id.login_user_parameter2);

        final boolean[] input = {false, false};
        Objects.requireNonNull(parameter1.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* do nothing */ }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input[0] = !(s.toString().isEmpty());
                button.setEnabled(input[0] && input[1]);
            }
            @Override
            public void afterTextChanged(Editable s) { /* do nothing */ }
        });
        Objects.requireNonNull(parameter2.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* do nothing */ }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input[1] = !(s.toString().isEmpty());
                button.setEnabled(input[0] && input[1]);
            }
            @Override
            public void afterTextChanged(Editable s) { /* do nothing */ }
        });

        button.setOnClickListener(view -> attemptUserLogin());
//        shortcut.setOnClickListener(view -> shortcutClick());

        return rootView;
    }

    private void attemptUserLogin() {
        int result = 0;

        String url = "http://" + ip + "/myTherapy/loginUser.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            result = okHttpHandler.loginUser(url, Objects.requireNonNull(parameter1.getEditText()).getText().toString(),
                    Objects.requireNonNull(parameter2.getEditText()).getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == 0){
            Objects.requireNonNull(parameter1.getEditText()).setText("");
            Objects.requireNonNull(parameter2.getEditText()).setText("");
            Toast.makeText(getContext(), "Login has failed", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(requireActivity(), UserMainActivity.class);
            intent.putExtra("AMKA", parameter1.getEditText().getText().toString());
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.no_slide_in_or_out);
            requireActivity().finish();
        }
    }

//    private void shortcutClick() {
//        Objects.requireNonNull(parameter1.getEditText()).setText("02070301851");
//        Objects.requireNonNull(parameter2.getEditText()).setText("12345678");
//        attemptUserLogin();
//    }
}