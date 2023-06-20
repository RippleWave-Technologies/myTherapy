package com.example.aic601project;

import android.util.Log;

import java.util.HashMap;

public class ModelAppointmentList {

    HashMap<ModelAppointment, ModelPatient> appointmentData;

    public ModelAppointmentList(String ip, String afm, String enterCase) {
        appointmentData = new HashMap<>();

        switch (enterCase) {
            case "PhysicianFragment1":
                String url = "http://" + ip + "/myTherapy/fetchClinicConfirmedAppointmentsPastCurrent.php";
                try {
                    OkHttpHandler okHttpHandler = new OkHttpHandler();
                    appointmentData = okHttpHandler.fetchClinicConfirmedAppointmentsPastCurrent(url, afm);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "R4":
                Log.d("R4", "R4");
                break;
        }

    }

    public HashMap<ModelAppointment, ModelPatient> getAppointmentData() {
        return appointmentData;
    }
}
