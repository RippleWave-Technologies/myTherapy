package com.example.aic601project;

public class ModelAppointment {

    private String amka, afm, date, status, service;

    public ModelAppointment(String amka, String afm, String date, String status, String service) {
        this.amka = amka;
        this.afm = afm;
        this.date = date;
        this.status = status;
        this.service = service;
    }

    public String getAmka() { return amka; }
    public String getAfm() { return afm; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public String getService() { return service; }
}
