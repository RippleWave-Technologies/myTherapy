package com.example.aic601project;

import java.util.ArrayList;

public class PatientList {
    private ArrayList<Patient> patients;

    public PatientList(String ip) {
        String url = "http://" + ip + "/myTherapy/fetchPatients.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            patients = okHttpHandler.fetchPatients(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
