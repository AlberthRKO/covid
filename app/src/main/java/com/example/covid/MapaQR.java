package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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
        Bundle get = getIntent().getExtras();
        String ubicaciones = get.getString("ubicaciones");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Ubicacion>>(){}.getType();
        List<Ubicacion> ubicacionList = gson.fromJson(ubicaciones, listType);
        for(Ubicacion ubicacion: ubicacionList) {
            LatLng ubi = new LatLng(Float.parseFloat(ubicacion.getEjeX()), Float.parseFloat(ubicacion.getEjeY()));
            gMap.addMarker(new MarkerOptions().position(ubi)
                    .position(ubi)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicacionqr))
                    .title(ubicacion.getNombre()));
        }
        LatLng sydney = new LatLng(-19.0384737, -65.2563851);
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.setMinZoomPreference(13f);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}