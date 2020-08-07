package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class contenido extends AppCompatActivity {

    TextView dato;
    Button ubicacion;
    TextView titulo;
    ImageView confir, recu, muer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);
        recibirQr();
        confir = (ImageView) findViewById(R.id.conf);
        recu = (ImageView) findViewById(R.id.rec);
        muer = (ImageView) findViewById(R.id.mue);
        rotarImagen(confir);
        rotarImagen(recu);
        rotarImagen(muer);





    }

    public void recibirQr(){
//        jalamos los datos del activiti anterior
        Bundle datoQR = getIntent().getExtras();
//        recibimos el dato
        String qr = datoQR.getString("CodigoQR");
        String idUsuario = datoQR.getString("idUsuario");
        dato = findViewById(R.id.datoQR);
        titulo = findViewById(R.id.titulo);
//        separamos los datos para mostrar diferentes
        String[] parts = qr.split(":");
        //1|PARADA RAVELO|ALTO|-19.0258807|-65.2782766    Esa es la cadena que muestra un código QR
        String idUbicacion = parts[0];
        String lugar = parts[1];
        String estado = parts[2];
        String ejeX = parts[3];
        String ejeY = parts[4];
        String servidor = "https://covid-qr.tk/php/controlador/ControladorUbicacionUsuario.php";
        String servidorLocal = "https://192.168.1.2:8080/covid-qr/php/controlador/ControladorUbicacionUsuario.php";
        insertarUbicacionUsuario(servidor,idUbicacion,idUsuario);

        final String ubicar = "https://www.google.com/maps/@" + ejeX + "," + ejeY +",18z";
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
        titulo.setText("Estado de riesgo en " + lugar + ": ");
        dato.setText("Gravedad : " + estado);
    }

    private void insertarUbicacionUsuario(String url, final String idUbicacion, final String idUsuario){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("OK"))
                    Toast.makeText(getApplicationContext(),"QR REGISTRADO EXITOSAMENTE",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"HUBO UN ERROR, VUELVA A ESCANEAR",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("request","insertar");
                parametros.put("idUsuario", idUsuario);
                parametros.put("idUbicacion",idUbicacion);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void rotarImagen(View view){
        RotateAnimation animation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(10000);
        animation.setRepeatCount(Animation.INFINITE);
//        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);
    }

}
