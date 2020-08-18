package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MapaInfeccion extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap gMap;
    private SupportMapFragment mapFragmentHospitales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_infeccion);

        mapFragmentHospitales = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapaInfeccion);
        mapFragmentHospitales.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(gMap.MAP_TYPE_NORMAL);
        Bundle get = getIntent().getExtras();
        String usuarios = get.getString("usuarios");
        String ejeX = get.getString("ejeX");
        String ejeY = get.getString("ejeY");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Usuario>>(){}.getType();
        List<Usuario> usuarioList = gson.fromJson(usuarios, listType);
        for(Usuario usuario: usuarioList) {
            LatLng ubi = new LatLng(Float.parseFloat(usuario.getEjeX()), Float.parseFloat(usuario.getEjeY()));
            gMap.addCircle(new CircleOptions()
                    .center(ubi)
                    .radius(55)
                    .strokeColor(Color.RED)
                    .strokeWidth(4)
                    .fillColor(Color.rgb(150,50,50))
            );
        }
        LatLng sydney = new LatLng(Float.parseFloat(ejeX), Float.parseFloat(ejeY));
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.setMinZoomPreference(16f);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }



}
