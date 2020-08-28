package com.example.ieprojectv2.fragment.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ieprojectv2.R;
import com.example.ieprojectv2.storeClass.hospital;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MapsFragment extends Fragment {
    private LatLng userlocation;
    private List<hospital> hospitalList;
    private List<hospital> clinicList;

    private Handler handler;

    private Button near_btn;
    private Button search_btn;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(final GoogleMap googleMap) {
            userlocation = hospital.getUserLocation();

            new Thread(){
                @Override
                public void run() {
                    hospitalList = hospital.getHospitalList();
                    clinicList = hospital.getClinicList();
                    List<List<hospital>> list = new ArrayList<>();
                    list.add(hospitalList);
                    list.add(clinicList);

                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = list;
                    handler.sendMessage(message);
                }
            }.start();

            handler = new Handler(Looper.myLooper()){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    List<List<hospital>> list = (List<List<hospital>>) msg.obj;
                    final List<hospital> l = list.get(0);
                    final List<hospital> l1 = list.get(1);
                    final List<hospital> resultlist = new ArrayList<>();
                    final float zoomLevel = (float) 10.0;

                    //mark user location
                    googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).
                            position(userlocation).title(getString(R.string.current_location)));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userlocation,zoomLevel));

                    near_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //mark nearest hospital
                            for(int i = 0; i < 3 && i < l.size(); i++){
                                //record total list
                                resultlist.add(l.get(i));

                                float[] result = new float[10];
                                //calculate the distance between two points
                                Location.distanceBetween(userlocation.latitude,userlocation.longitude,
                                        l.get(i).getLatLng().latitude, l.get(i).getLatLng().longitude,result);
                                googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).
                                        position(l.get(i).getLatLng()).title(l.get(i).getName() + " (Hospital)").
                                        snippet("Distance: " + result[0] + "meters"));
                            }

                            //click the window to generate card
                            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                @Override
                                public void onInfoWindowClick(Marker marker) {
                                    if(!marker.getId().equals("m0")){
                                        int id = Integer.valueOf(marker.getId().split("")[1]);
                                        //alert message
                                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                        View view = LayoutInflater.from(getActivity()).inflate(R.layout.call_taxi_layout,null);
                                        TextView taxi_tv = view.findViewById(R.id.taxi_tv);
                                        taxi_tv.setText("Sorry I can't speak English, I want to go to the " +
                                                resultlist.get(id-1).getName()+ " which is locates at "+
                                                resultlist.get(id-1).getAddress() + ". Thank you so much");

                                        builder.setView(view).show();

                                    }
                                    else{
                                        Toast.makeText(getActivity(),"This is you" ,Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        }
                    });

                    search_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //mark nearest hospital
                            for(int i = 0; i < 3 && i < l1.size(); i++){
                                //record total list
                                resultlist.add(l1.get(i));

                                float[] result = new float[10];
                                //calculate the distance between two points
                                Location.distanceBetween(userlocation.latitude,userlocation.longitude,
                                        l1.get(i).getLatLng().latitude, l1.get(i).getLatLng().longitude,result);
                                googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).
                                        position(l1.get(i).getLatLng()).title(l1.get(i).getName() + " (Clinic)").
                                        snippet("Distance: " + result[0] + "meters"));
                            }

                            //click the window to generate card
                            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                @Override
                                public void onInfoWindowClick(Marker marker) {
                                    if(!marker.getId().equals("m0")){
                                        int id = Integer.valueOf(marker.getId().split("")[1]);
                                        //alert message
                                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                        View view = LayoutInflater.from(getActivity()).inflate(R.layout.call_taxi_layout,null);
                                        TextView taxi_tv = view.findViewById(R.id.taxi_tv);
                                        taxi_tv.setText("Sorry I can't speak English, I want to go to the " +
                                                resultlist.get(id-1).getName()+ " which is locates at "+
                                                resultlist.get(id-1).getAddress() + ". Thank you so much");

                                        builder.setView(view).show();

                                    }
                                    else{
                                        Toast.makeText(getActivity(),"This is you" ,Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        }
                    });
                }
            };


        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        near_btn = view.findViewById(R.id.near_btn);
        search_btn = view.findViewById(R.id.search_btn);
    }

}