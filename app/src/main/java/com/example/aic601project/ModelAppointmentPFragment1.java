package com.example.aic601project;

public class ModelAppointmentPFragment1 {

    String aptAMKA, aptName, aptSurname, aptDate;

    public ModelAppointmentPFragment1(String aptAMKA, String aptName, String aptSurname, String aptDate) {
        this.aptAMKA = aptAMKA;
        this.aptName = aptName;
        this.aptSurname = aptSurname;
        this.aptDate = aptDate;
    }

    public String getAptAMKA() { return aptAMKA; }
    public String getAptName() { return aptName; }
    public String getAptSurname() { return aptSurname; }
    public String getAptDate() { return aptDate; }
}
