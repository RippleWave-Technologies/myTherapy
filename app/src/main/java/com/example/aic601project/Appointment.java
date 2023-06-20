package com.example.aic601project;

public class Appointment {
    private String date;
    private String amka;
    private String afm;
    private String status;
    private String service;
    private String lastName;

    public Appointment(String date, String amka, String afm, String status, String service, String lastName) {
        this.date = date;
        this.amka = amka;
        this.afm = afm;
        this.status = status;
        this.service = service;
        this.lastName = lastName;
    }


    public String getLastName(){
        return this.lastName;
    }
    //Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getAFM() {
        return afm;
    }

    public void setAFM(String afm) {
        this.afm = afm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

}
