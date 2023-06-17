package com.example.aic601project;

public class R10ListItem {
    private String date;
    private String name;
    private String number;

    public R10ListItem(String date, String name, String number) {
        this.date = date;
        this.name = name;
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
