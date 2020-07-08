package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class contenido extends AppCompatActivity {

    TextView dato;
    Button ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);
        recibirQr();

    }

    public void recibirQr(){
//        jalamos los datos del activiti anterior
        Bundle datoQR = getIntent().getExtras();
//        recibimos el dato
        String qr = datoQR.getString("Estado");
        dato = findViewById(R.id.datoQR);
//        separamos los datos para mostrar diferentes
        String[] parts = qr.split("-");
        String estado = parts[0];
        final String ubicar = parts[1];
        ubicacion = findViewById(R.id.ubicaciones);
//        datos final donde estara el link de las ubicaciones
        ubicacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(ubicar);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        dato.setText("Gravedad : " + estado);
    }

}
