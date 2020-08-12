package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mapaHospitales extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private SupportMapFragment mapFragmentHospitales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_hospitales);


        mapFragmentHospitales = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapaHospitales);
        mapFragmentHospitales.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(gMap.MAP_TYPE_NORMAL);
        Bundle get = getIntent().getExtras();
        String hospitales = get.getString("hospitales");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Hospital>>(){}.getType();
        List<Hospital> hospitalList = gson.fromJson(hospitales, listType);
        for(Hospital hospital: hospitalList) {
            LatLng ubi = new LatLng(Float.parseFloat(hospital.getEjeX()), Float.parseFloat(hospital.getEjeY()));
            gMap.addMarker(new MarkerOptions().position(ubi)
                    .title(hospital.getNombre()));
        }
        LatLng sydney = new LatLng(-19.0384737, -65.2563851);
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.setMinZoomPreference(13f);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


}