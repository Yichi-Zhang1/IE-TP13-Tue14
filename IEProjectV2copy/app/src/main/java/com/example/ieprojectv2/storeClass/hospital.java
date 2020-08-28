package com.example.ieprojectv2.storeClass;

import com.example.ieprojectv2.NetworkConnection;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class hospital {
    private String name;
    private LatLng latLng;
    private String address;
    private Double rating;
    private static NetworkConnection networkConnection = new NetworkConnection();



    private static List<hospital> hospitalList = new ArrayList<>();
    private static List<hospital> clinicList = new ArrayList<>();


    public hospital(String name, LatLng latLng, String address, Double rating) {
        this.name = name;
        this.latLng = latLng;

        this.address = address;
        this.rating = rating;
    }

    public static List<hospital> getHospitalList(){
        String result = networkConnection.searchHospital();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for(int i = 0; i < jsonArray.length();i++){
                JSONObject hosobject = new JSONObject(jsonArray.get(i).toString());
                //check if its open

                //name
                String hospitalname = hosobject.getString("name");
                Double rating = 0.0;
                if(hosobject.has("rating")){
                    rating = hosobject.getDouble("rating");
                }
                String address = hosobject.getString("vicinity");
                JSONObject locationobj = hosobject.getJSONObject("geometry").getJSONObject("location");
                Double latitude = locationobj.getDouble("lat");
                Double longitude = locationobj.getDouble("lng");
                LatLng latLng = new LatLng(latitude,longitude);
                hospital h = new hospital(hospitalname,latLng,address,rating);
                hospitalList.add(h);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hospitalList;
    }

    public static List<hospital> getClinicList(){
        String result = networkConnection.searchClinic();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for(int i = 0; i < jsonArray.length();i++){
                JSONObject hosobject = new JSONObject(jsonArray.get(i).toString());

                //name
                String hospitalname = hosobject.getString("name");
                Double rating = 0.0;
                if(hosobject.has("rating")){
                    rating = hosobject.getDouble("rating");
                }
                String address = hosobject.getString("vicinity");
                JSONObject locationobj = hosobject.getJSONObject("geometry").getJSONObject("location");
                Double latitude = locationobj.getDouble("lat");
                Double longitude = locationobj.getDouble("lng");
                LatLng latLng = new LatLng(latitude,longitude);
                hospital h = new hospital(hospitalname,latLng,address,rating);
                clinicList.add(h);



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return clinicList;
    }

    public static LatLng getUserLocation(){
        LatLng userlocation = new LatLng(-37.876663,145.045089);
        return userlocation;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}


