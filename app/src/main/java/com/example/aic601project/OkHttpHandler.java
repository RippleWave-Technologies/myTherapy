package com.example.aic601project;

import java.util.*;

import org.json.*;

import android.os.*;

import okhttp3.*;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public ArrayList<ModelClinics> fetchClinics(String url) throws Exception {
        ArrayList<ModelClinics> clinics = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject clinicObject = json.getJSONObject(key);

                String name = clinicObject.getString("name");
                String email = clinicObject.getString("email");
                String address = clinicObject.getString("address");
                String addressNumber = clinicObject.getString("addressNumber");
                String postcode = clinicObject.getString("postcode");
                String city = clinicObject.getString("city");

                clinics.add(new ModelClinics(Integer.valueOf(key), name, email, address, Integer.valueOf(addressNumber), Integer.valueOf(postcode), city));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return clinics;
    }

    ArrayList<Patient> fetchPatients(String url) throws Exception {

        ArrayList<Patient> patients = new ArrayList<>();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String amka = keys.next();

                String name = json.get("name").toString();
                String surname = json.get("surname").toString();
                String city = json.get("city").toString();
                String address = json.get("address").toString();
                String addressNumber = json.get("addressNumber").toString();
                String postcode = json.get("postcode").toString();

                patients.add(new Patient(amka, name,surname,city, address, Integer.valueOf(addressNumber), postcode));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return patients;
    }

    ArrayList<Appointment> fetchAppoinments(String url) throws Exception {

        ArrayList<Appointment> appoinments = new ArrayList<>();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String date = keys.next();

                String amka = json.get("amka").toString();
                String afm = json.get("afm").toString();
                String status = json.get("status").toString();
                String service = json.get("service").toString();

                appoinments.add(new Appointment(date, amka,afm,status, service));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return appoinments;
    }


    ArrayList<Service> fetchServices(String url) throws Exception {

        ArrayList<Service> services = new ArrayList<>();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String code = keys.next();

                String name = json.get("name").toString();
                String price = json.get("price").toString();
                String description = json.get("description").toString();

                services.add(new Service(code,name, price, description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return services;
    }

}
