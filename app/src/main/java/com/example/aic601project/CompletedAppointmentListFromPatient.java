package com.example.aic601project;

import com.example.aic601project.R3_R8.PhysicianMainActivity;

import java.util.ArrayList;

public class CompletedAppointmentListFromPatient {
    private ArrayList<ModelAppointment> appointments;

    public CompletedAppointmentListFromPatient(String ip, ModelPatient patient) {
        String url = "http://" + ip + "/myTherapy/fetchAppointments.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            ArrayList<ModelAppointment> tempApp = okHttpHandler.fetchCompletedAppoinments(url);

            for (ModelAppointment app : tempApp){
                if(app.getAfm().equals(PhysicianMainActivity.getAfm()) && app.getAmka().equals(patient.getAmka())){
                    appointments.add(app);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ModelAppointment> getAppointments(){
        return  this.appointments;
    }
}
