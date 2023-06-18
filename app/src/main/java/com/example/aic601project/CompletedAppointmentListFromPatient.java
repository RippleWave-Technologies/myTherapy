package com.example.aic601project;

import com.example.aic601project.R3_R8.PhysicianMainActivity;

import java.util.ArrayList;

public class CompletedAppointmentListFromPatient {
    private ArrayList<Appointment> appointments;

    public CompletedAppointmentListFromPatient(String ip, Patient patient) {
        String url = "http://" + ip + "/myTherapy/fetchAppointments.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            ArrayList<Appointment> tempApp = okHttpHandler.fetchCompletedAppoinments(url);

            for (Appointment app : tempApp){
                if(app.getAFM().equals(PhysicianMainActivity.getAfm()) && app.getAmka().equals(patient.getAmka())){
                    appointments.add(app);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Appointment> getAppointments(){
        return  this.appointments;
    }
}
