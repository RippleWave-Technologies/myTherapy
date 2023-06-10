package com.example.aic601project;

public class ModelClinics {
    int physioAFM, physioAddressNumber, physioPostCode;
    String physioName, physioEmail, physioAddress, physioCity;

    public ModelClinics(int physioAFM, String physioName, String physioEmail, String physioAddress,
                        int physioAddressNumber, int physioPostCode, String physioCity) {
        this.physioAFM = physioAFM;
        this.physioName = physioName;
        this.physioEmail = physioEmail;
        this.physioAddress = physioAddress;
        this.physioAddressNumber = physioAddressNumber;
        this.physioPostCode = physioPostCode;
        this.physioCity = physioCity;
    }

    public int getPhysioAFM() {
        return physioAFM;
    }

    public int getPhysioAddressNumber() {
        return physioAddressNumber;
    }

    public int getPhysioPostCode() {
        return physioPostCode;
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

    public String getPhysioCity() {
        return physioCity;
    }
}