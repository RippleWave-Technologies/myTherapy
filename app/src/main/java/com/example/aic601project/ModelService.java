package com.example.aic601project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ModelService implements Parcelable {
    private String code, name, price, description;

    public ModelService(String code, String name, String price, String description) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    protected ModelService(Parcel in) {
        code = in.readString();
        name = in.readString();
        price = in.readString();
        description = in.readString();
    }

    public static final Creator<ModelService> CREATOR = new Creator<ModelService>() {
        @Override
        public ModelService createFromParcel(Parcel in) {
            return new ModelService(in);
        }

        @Override
        public ModelService[] newArray(int size) {
            return new ModelService[size];
        }
    };

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }

    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(code);
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(description);
    }
}
