package com.example.aic601project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Patient implements Parcelable {
    private String amka;
    private String name;
    private String surname;
    private String city;
    private String address;
    private String addressNumber;
    private String postcode;

    public Patient(String amka, String name, String surname, String city, String address, String addressNumber,
            String postcode) {
        this.amka = amka;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.address = address;
        this.addressNumber = addressNumber;
        this.postcode = postcode;
    }

    protected Patient(Parcel in) {
        amka = in.readString();
        name = in.readString();
        surname = in.readString();
        city = in.readString();
        address = in.readString();
        addressNumber = in.readString();
        postcode = in.readString();
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
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
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(amka);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(city);
        parcel.writeString(address);
        parcel.writeString(addressNumber);
        parcel.writeString(postcode);
    }
}
