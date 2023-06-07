package com.example.aic601project;

public class Appointment {
    private String date;
    private String amka;
    private String name;
    private String status;
    private String service;

    public Appointment(String date, String amka, String name, String status, String service) {
        this.date = date;
        this.amka = amka;
        this.name = name;
        this.status = status;
        this.service = service;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
