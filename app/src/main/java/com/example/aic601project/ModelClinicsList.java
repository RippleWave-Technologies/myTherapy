package com.example.aic601project;

import java.util.ArrayList;

public class ModelClinicsList {
    private ArrayList<ModelClinics> clinics;

    public ModelClinicsList(String ip) {
        clinics = new ArrayList<>();
        String url = "http://" + ip + "/myTherapy/fetchClinics.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            clinics = okHttpHandler.fetchClinics(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ModelClinics> getClinics() {
        return clinics;
    }
}