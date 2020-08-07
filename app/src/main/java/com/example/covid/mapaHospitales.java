package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
        LatLng ubi = new LatLng(-19.0384737, -65.2563851);
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.addMarker(new MarkerOptions().position(ubi)
                .title("Alberth"));
        gMap.setMinZoomPreference(13f);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(ubi));

    }
}