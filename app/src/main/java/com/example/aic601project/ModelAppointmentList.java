package com.example.aic601project;

import java.util.HashMap;

public class ModelAppointmentList {

    HashMap<ModelAppointment, ModelPatient> appointmentPatientData;
    HashMap<HashMap<ModelAppointment, ModelPatient>, ModelService> appointmentPatientServiceData;

    public ModelAppointmentList(String ip, String afm, String date, String enterCase) {
        appointmentPatientData = new HashMap<>();
        appointmentPatientServiceData = new HashMap<>();
        String url;

        switch (enterCase) {
            case "PhysicianFragment1":
                url = "http://" + ip + "/myTherapy/fetchClinicConfirmedAppointmentsPastCurrent.php";
                try {
                    OkHttpHandler okHttpHandler = new OkHttpHandler();
                    appointmentPatientData = okHttpHandler.fetchClinicConfirmedAppointmentsPastCurrent(url, afm);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "PhysicianFragment2":
                url = "http://" + ip + "/myTherapy/fetchClinicConfirmedAppointments.php";
                try {
                    OkHttpHandler okHttpHandler = new OkHttpHandler();
                    appointmentPatientServiceData = okHttpHandler.fetchClinicConfirmedAppointments(url, date, afm);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    public HashMap<ModelAppointment, ModelPatient> getAppointmentPatientData() {
        return appointmentPatientData;
    }

    public HashMap<HashMap<ModelAppointment, ModelPatient>, ModelService> getAppointmentPatientServiceData() {
        return appointmentPatientServiceData;
    }
}
