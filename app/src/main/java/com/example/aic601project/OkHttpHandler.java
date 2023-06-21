package com.example.aic601project;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    ArrayList<ModelPatient> fetchClinicPatients(String url, String afm) throws Exception {

        ArrayList<ModelPatient> patients = new ArrayList<>();

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

                patients.add(new ModelPatient(amka, name,surname,city, address, addressNumber, postcode));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public ArrayList<ModelR4Appointment> fetchCompletedAppointments(String url) throws Exception {

        ArrayList<ModelR4Appointment> appointments = new ArrayList<>();

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

                JSONObject appointmentObject = json.getJSONObject(date);

                String amka = appointmentObject.getString("amka");
                String afm = appointmentObject.getString("afm");
                String service = appointmentObject.getString("serviceName");;
                String lastName = appointmentObject.getString("surname");;

                appointments.add(new ModelR4Appointment(date, amka,afm,"3", service,lastName));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public HashMap<ModelAppointment, ModelPatient> fetchClinicConfirmedAppointmentsPastCurrent(String url, String afm) throws IOException {
        HashMap<ModelAppointment, ModelPatient> appointmentPatientData = new HashMap<>();

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        RequestBody body = new FormBody.Builder()
                .add("afm", afm)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject clinicObject = json.getJSONObject(key);

                String amka = key.substring(0, 11);
                String date = key.substring(11);

                String name = clinicObject.getString("name");
                String surname = clinicObject.getString("surname");

                appointmentPatientData.put(new ModelAppointment(amka, "", date, "", ""),
                        new ModelPatient(amka, name, surname, "", "", "", ""));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return appointmentPatientData;
    }

    public HashMap<HashMap<ModelAppointment, ModelPatient>, ModelService> fetchClinicConfirmedAppointments(String url, String date, String afm) throws IOException {
        HashMap<HashMap<ModelAppointment, ModelPatient>, ModelService> appointmentPatientServiceData = new HashMap<>();

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        RequestBody body = new FormBody.Builder()
                .add("date", date)
                .add("afm", afm)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject clinicObject = json.getJSONObject(key);

                String amka = key.substring(0, 11);
                String dateTime = key.substring(11);

                String name = clinicObject.getString("name");
                String surname = clinicObject.getString("surname");
                String code = clinicObject.getString("code");
                String serviceName = clinicObject.getString("service_name");

                appointmentPatientServiceData.put(new HashMap<ModelAppointment, ModelPatient>() {{
                        put(new ModelAppointment(amka, "", dateTime, "", ""),
                                new ModelPatient(amka, name, surname, "", "", "", ""));}},
                    new ModelService(code, serviceName, "", ""));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return appointmentPatientServiceData;
    }

    public int insertClinicPatientRequestedAppointment(String url, String date, String amka, String afm) throws IOException{
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("date", date)
                .add("amka", amka)
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

    public void updateClinicPatientRequestedOrConfirmedAppointments(String url, String amka,
                                                                    String afm, String date, String act,
                                                                    String service) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        RequestBody body = new FormBody.Builder()
                .add("amka", amka)
                .add("afm", afm)
                .add("date", date)
                .add("act", act)
                .add("service", service)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).execute();
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

    ArrayList<ModelService> fetchServices(String url) throws Exception {

        ArrayList<ModelService> services = new ArrayList<>();

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
                String price = clinicObject.getString("price");
                String description = clinicObject.getString("description");

                services.add(new ModelService(key, name, price, description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return services;
    }

    public int insertOrUpdateService(String url, String code, String name, String price, String description) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Log.d("imtestingbro", code + " " + name + " " + price + " " + description);

        RequestBody body = new FormBody.Builder()
                .add("code", code)
                .add("name", name)
                .add("price", price)
                .add("description", description)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return Integer.parseInt(response.body().string());
    }

    public int deleteService(String url, String code) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("code", code)
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
