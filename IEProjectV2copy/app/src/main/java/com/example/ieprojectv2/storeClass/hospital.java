package com.example.ieprojectv2.storeClass;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class hospital {
    private String name;
    private LatLng latLng;
    private static List<hospital> hospitalList = new ArrayList<>();

    public hospital(String name, LatLng latLng ) {
        this.name = name;
        this.latLng = latLng;

    }

    public static List<hospital> getHospitalList(){
        hospitalList.add(new hospital("Cabrini Malvern",new LatLng(-37.860980, 145.033520)));
        hospitalList.add(new hospital("The Avenue Hospital",new LatLng(-37.854706, 144.998584)));
        hospitalList.add(new hospital("The Alfred", new LatLng(-37.845442, 144.981909)));
        hospitalList.add(new hospital("Epworth Richmond", new LatLng(-37.815341, 144.993153)));
        return hospitalList;
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
}
