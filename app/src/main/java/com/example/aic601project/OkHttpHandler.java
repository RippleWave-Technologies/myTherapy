package com.example.aic601project;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    ArrayList<Patient> fetchClinicPatients(String url, String afm) throws Exception {

        ArrayList<Patient> patients = new ArrayList<>();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = new FormBody.Builder().add("afm", afm).build();
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String amka = keys.next();
                JSONObject patientObject = json.getJSONObject(amka);

                String name = patientObject.getString("name");
                String surname = patientObject.getString("surname");
                String city = patientObject.getString("city");
                String address = patientObject.getString("address");
                String addressNumber = patientObject.getString("addressNumber");
                String postcode = patientObject.getString("postcode");

                patients.add(new Patient(amka, name,surname,city, address, addressNumber, postcode));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return patients;
    }

    ArrayList<Appointment> fetchCompletedAppoinments(String url) throws Exception {

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
                if (status.equals("3")){
                    appoinments.add(new Appointment(date, amka,afm,status, service));
                }

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

    public ArrayList<ModelClinic> fetchClinics(String url) throws Exception {
        ArrayList<ModelClinic> clinics = new ArrayList<>();

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

                clinics.add(new ModelClinic(key, name, email, address, addressNumber, postcode, city));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return clinics;
    }

    public int insertOrUpdateClinic(String url, String afm, String name, String email, String address,
                                    String addressNumber, String postcode, String city) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("afm", afm)
                .add("name", name)
                .add("email", email)
                .add("address", address)
                .add("addressNumber", addressNumber)
                .add("postcode", postcode)
                .add("city", city)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return Integer.parseInt(response.body().string());
    }

    public int deleteClinic(String url, String afm) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("afm", afm)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return Integer.parseInt(response.body().string());
    }

    public int loginAdmin(String url, String id, String password) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        RequestBody body = new FormBody.Builder()
                .add("id", id)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return Integer.parseInt(response.body().string());
    }

    public int loginPhysician(String url, String afm, String password) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        RequestBody body = new FormBody.Builder()
                .add("afm", afm)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return Integer.parseInt(response.body().string());
    }

    public int loginUser(String url, String amka, String password) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        RequestBody body = new FormBody.Builder()
                .add("amka", amka)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return Integer.parseInt(response.body().string());
    }
}
