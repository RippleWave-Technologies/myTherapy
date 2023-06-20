package com.example.aic601project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ModelPatient implements Parcelable {
    private String amka, name, surname, address, addressNumber, city, postCode;

    private ArrayList<ModelAppointment> completedAppointments;

    public ModelPatient(String amka, String name, String surname, String city, String address, String addressNumber,
                        String postCode) {
        this.amka = amka;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.address = address;
        this.addressNumber = addressNumber;
        this.postCode = postCode;

        CompletedAppointmentListFromPatient comApp = new CompletedAppointmentListFromPatient(MainActivity.getIP(), this);

        this.completedAppointments = comApp.getAppointments();

    }

    protected ModelPatient(Parcel in) {
        amka = in.readString();
        name = in.readString();
        surname = in.readString();
        city = in.readString();
        address = in.readString();
        addressNumber = in.readString();
        postCode = in.readString();
    }

    public static final Creator<ModelPatient> CREATOR = new Creator<ModelPatient>() {
        @Override
        public ModelPatient createFromParcel(Parcel in) {
            return new ModelPatient(in);
        }

        @Override
        public ModelPatient[] newArray(int size) {
            return new ModelPatient[size];
        }
    };

    // Getters and Setters
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getPostcode() {
        return postCode;
    }

    public void setPostcode(String postcode) {
        this.postCode = postcode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ArrayList<ModelAppointment> getCompletedAppointments(){
        return this.completedAppointments;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(amka);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(city);
        parcel.writeString(address);
        parcel.writeString(addressNumber);
        parcel.writeString(postCode);
    }
}
