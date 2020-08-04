package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaQR extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_q_r);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapaQr);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(gMap.MAP_TYPE_NORMAL);
        LatLng sydney = new LatLng(-19.0384737, -65.2563851);
        LatLng sydney2 = new LatLng(-19.039738956997944, -65.25693297738495);
        LatLng sydney3 = new LatLng(-19.046389552262934, -65.26603362908325);
        gMap.addMarker(new MarkerOptions().position(sydney)
                .title("Alberth"));
        gMap.addMarker(new MarkerOptions().position(sydney2)
                .title("Facultad de Tecnologia"));
        gMap.addMarker(new MarkerOptions().position(sydney3)
                .title("Facultad de Medicina"));
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.setMinZoomPreference(15f);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}