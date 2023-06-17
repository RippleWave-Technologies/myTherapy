package com.example.aic601project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Economic_View  implements Parcelable {
    String physioName, physioDate, physioAction, physioHour, physioCost;

    public Economic_View(String physioAFM, String physioName, String physioEmail, String physioAddress,
                       String physioAddressNumber, String physioPostCode, String physioCity) {
        this.physioName = physioName;
        this.physioAction = physioAction;
        this.physioDate = physioDate;
        this.physioHour = physioHour;
        this.physioCost = physioCost;
    }

    protected Economic_View(Parcel in) {
        physioName = in.readString();
        physioAction = in.readString();
        physioDate = in.readString();
        physioHour = in.readString();
        physioCost = in.readString();
    }

    public static final Creator<com.example.aic601project.ModelClinic> CREATOR = new Creator<com.example.aic601project.ModelClinic>() {
        @Override
        public com.example.aic601project.ModelClinic createFromParcel(Parcel in) {
            return new com.example.aic601project.ModelClinic(in);
        }

        @Override
        public com.example.aic601project.ModelClinic[] newArray(int size) {
            return new com.example.aic601project.ModelClinic[size];
        }
    };

    public String getPhysioAction() {
        return physioAction;
    }

    public String getPhysioName() {
        return physioName;
    }

    public String getPhysioDate() {
        return physioDate;
    }

    public String getPhysioHour() {
        return physioHour;
    }

    public String getPhysioCost() {
        return physioCost;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(physioName);
        parcel.writeString(physioAction);
        parcel.writeString(physioDate);
        parcel.writeString(physioHour);
        parcel.writeString(physioCost);
    }

    }

