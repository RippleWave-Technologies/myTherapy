package com.example.aic601project.R9_R10;

import com.example.aic601project.ModelClinic;
import com.example.aic601project.OkHttpHandler;
import com.example.aic601project.R10ListItem;
import com.example.aic601project.Service;

import java.util.ArrayList;

public class R10_Economic_List {
    private ArrayList<R10ListItem> information;

    public R10_Economic_List(String ip) {
        String url = "http://" + ip + "/myTherapy/fetchClinicPatientCompletedAppointment.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            information = okHttpHandler.fetchClinics(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<R10ListItem> getInformation() {
        return information;
    }

}
