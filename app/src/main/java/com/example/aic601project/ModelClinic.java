package com.example.aic601project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ModelClinic implements Parcelable{
    String afm, name, email, address, addressNumber, postCode, city;

    public ModelClinic(String afm, String name, String email, String address,
                       String addressNumber, String postCode, String city) {
        this.afm = afm;
        this.name = name;
        this.email = email;
        this.address = address;
        this.addressNumber = addressNumber;
        this.postCode = postCode;
        this.city = city;
    }

    protected ModelClinic(Parcel in) {
        afm = in.readString();
        name = in.readString();
        email = in.readString();
        address = in.readString();
        addressNumber = in.readString();
        postCode = in.readString();
        city = in.readString();
    }

    public static final Creator<ModelClinic> CREATOR = new Creator<ModelClinic>() {
        @Override
        public ModelClinic createFromParcel(Parcel in) {
            return new ModelClinic(in);
        }

        @Override
        public ModelClinic[] newArray(int size) {
            return new ModelClinic[size];
        }
    };

    public String getPhysioAFM() {
        return afm;
    }

    public String getPhysioName() {
        return name;
    }

    public String getPhysioEmail() {
        return email;
    }

    public String getPhysioAddress() {
        return address;
    }

    public String getPhysioAddressNumber() {
        return addressNumber;
    }

    public String getPhysioPostCode() {
        return postCode;
    }

    public String getPhysioCity() {
        return city;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(afm);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(address);
        parcel.writeString(addressNumber);
        parcel.writeString(postCode);
        parcel.writeString(city);
    }

}