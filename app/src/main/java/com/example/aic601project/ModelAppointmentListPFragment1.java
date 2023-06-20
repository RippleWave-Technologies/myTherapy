package com.example.aic601project;

import java.util.ArrayList;

public class ModelAppointmentListPFragment1 {

    private ArrayList<ModelAppointmentPFragment1> appointments;

    public ModelAppointmentListPFragment1(String ip, String afm) {
        appointments = new ArrayList<>();
        String url = "http://" + ip + "/myTherapy/fetchClinicConfirmedAppointmentsPastCurrent.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            appointments = okHttpHandler.fetchClinicConfirmedAppointmentsPastCurrent(url, afm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ModelAppointmentPFragment1> getAppointments() { return appointments; }
}
