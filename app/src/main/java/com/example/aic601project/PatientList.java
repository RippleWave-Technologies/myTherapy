package com.example.aic601project;

import static com.example.aic601project.R3_R8.PhysicianMainActivity.getAfm;

import java.util.ArrayList;

public class PatientList {
    private ArrayList<Patient> patients;

    public PatientList(String ip) {
        patients = new ArrayList<>();
        String url = "http://" + ip + "/myTherapy/fetchClinicPatients.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            patients = okHttpHandler.fetchClinicPatients(url, getAfm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }
}
