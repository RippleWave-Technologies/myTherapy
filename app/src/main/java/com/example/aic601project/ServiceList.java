package com.example.aic601project;

import java.util.ArrayList;

public class ServiceList {
    private ArrayList<Service> services;

    public ServiceList(String ip) {
        String url = "http://" + ip + "/myTherapy/fetchServices.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            services = okHttpHandler.fetchServices(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
