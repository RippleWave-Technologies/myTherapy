package com.example.aic601project;

public class Service {
    private String code;
    private String name;
    private String price;
    private String discription;

    public Service(String code, String name, String price, String discription) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.discription = discription;
    }

    //Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
