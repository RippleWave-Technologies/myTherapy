package com.example.aic601project;

public class Patient {
    private String amka;
    private String name;
    private String surname;
    private String city;
    private String address;
    private int addressNumber;
    private String postcode;
    private String password = "123455678";

    public Patient(String amka, String name, String surname, String city, String address, int addressNumber,
            String postcode) {
        this.amka = amka;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.address = address;
        this.addressNumber = addressNumber;
        this.postcode = postcode;
    }

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

    public int getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
