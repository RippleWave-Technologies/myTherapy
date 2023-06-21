package com.example.aic601project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ModelPatient implements Parcelable {
    private String amka, name, surname, address, addressNumber, city, postCode;
    public ModelPatient(String amka, String name, String surname, String city, String address, String addressNumber,
                        String postCode) {
        this.amka = amka;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.address = address;
        this.addressNumber = addressNumber;
        this.postCode = postCode;

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
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getCity() {
        return city;
    }
    public String getAddress() {
        return address;
    }
    public String getAddressNumber() {
        return addressNumber;
    }


    public String getPostcode() {
        return postCode;
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
        parcel.writeString(postCode);
    }
}
