package com.example.aic601project;

import java.util.ArrayList;

public class AppointmentList {
    private ArrayList<Appointment> appointments;

    public AppointmentList(String ip) {
        String url = "http://" + ip + "/myTherapy/fetchAppointments.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            appointments = okHttpHandler.fetchAppoinments(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
