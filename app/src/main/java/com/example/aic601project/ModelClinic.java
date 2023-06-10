package com.example.aic601project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ModelClinic implements Parcelable{
    String physioAFM, physioName, physioEmail, physioAddress, physioAddressNumber, physioPostCode, physioCity;

    public ModelClinic(String physioAFM, String physioName, String physioEmail, String physioAddress,
                       String physioAddressNumber, String physioPostCode, String physioCity) {
        this.physioAFM = physioAFM;
        this.physioName = physioName;
        this.physioEmail = physioEmail;
        this.physioAddress = physioAddress;
        this.physioAddressNumber = physioAddressNumber;
        this.physioPostCode = physioPostCode;
        this.physioCity = physioCity;
    }

    protected ModelClinic(Parcel in) {
        physioAFM = in.readString();
        physioName = in.readString();
        physioEmail = in.readString();
        physioAddress = in.readString();
        physioAddressNumber = in.readString();
        physioPostCode = in.readString();
        physioCity = in.readString();
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
        return physioAFM;
    }

    public String getPhysioName() {
        return physioName;
    }

    public String getPhysioEmail() {
        return physioEmail;
    }

    public String getPhysioAddress() {
        return physioAddress;
    }

    public String getPhysioAddressNumber() {
        return physioAddressNumber;
    }

    public String getPhysioPostCode() {
        return physioPostCode;
    }

    public String getPhysioCity() {
        return physioCity;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(physioAFM);
        parcel.writeString(physioName);
        parcel.writeString(physioEmail);
        parcel.writeString(physioAddress);
        parcel.writeString(physioAddressNumber);
        parcel.writeString(physioPostCode);
        parcel.writeString(physioCity);
    }

}