package com.example.aic601project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ModelR4Appointment implements Parcelable {
    private String date, amka, afm, status, service, lastName;

    public String getDate() { return date; }

    public String getAmka() { return amka; }
    public String getAfm() { return afm; }
    public String getStatus() { return status; }
    public String getService() { return service; }
    public String getLastName() { return lastName; }

    public ModelR4Appointment(String date, String amka, String afm, String status, String service, String lastName) {
        this.date = date;
        this.amka = amka;
        this.afm = afm;
        this.status = status;
        this.service = service;
        this.lastName = lastName;
    }

    protected ModelR4Appointment(Parcel in) {
        date = in.readString();
        amka = in.readString();
        afm = in.readString();
        status = in.readString();
        service = in.readString();
        lastName = in.readString();
    }

    public static final Creator<ModelR4Appointment> CREATOR = new Creator<ModelR4Appointment>() {
        @Override
        public ModelR4Appointment createFromParcel(Parcel in) {
            return new ModelR4Appointment(in);
        }

        @Override
        public ModelR4Appointment[] newArray(int size) {
            return new ModelR4Appointment[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(amka);
        parcel.writeString(afm);
        parcel.writeString(status);
        parcel.writeString(service);
        parcel.writeString(lastName);
    }
}
