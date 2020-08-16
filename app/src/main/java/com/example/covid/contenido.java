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
        dato = findViewById(R.id.datoQR);
        titulo = findViewById(R.id.titulo);
        String idUsuario = datoQR.getString("idUsuario");
        String idUbicacion = datoQR.getString("idUbicacion");
        String lugar = datoQR.getString("lugar");
        String estado = datoQR.getString("estado");
        String ejeX = datoQR.getString("ejeX");
        String ejeY = datoQR.getString("ejeY");
        String servidor = "https://covid-qr.tk/php/controlador/ControladorUbicacionUsuario.php";
        String servidorLocal = "https://192.168.1.2:8080/covid-qr/php/controlador/ControladorUbicacionUsuario.php";
        insertarUbicacionUsuario(servidor,idUbicacion,idUsuario);
        Ubicacion u = new Ubicacion(Integer.parseInt(idUbicacion), lugar, "", estado, ejeX, ejeY, "1");
        getGravedad(servidor, u);

        final String ubicar = "https://www.google.com/maps/@" + ejeX + "," + ejeY +",18z";
        ubicacion = findViewById(R.id.ubicaciones);
//        datos final donde estara el link de las ubicaciones
        ubicacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*Uri uri = Uri.parse(ubicar);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);*/
                String servidor = "https://covid-qr.tk/php/controlador/ControladorUsuario.php";
                Bundle datoQR = getIntent().getExtras();
                String ejeX = datoQR.getString("ejeX");
                String ejeY = datoQR.getString("ejeY");
                getTodosUsuariosConfirmadosCercanosQR(servidor,ejeX,ejeY);
            }
        });
        titulo.setText("Estado de riesgo en " + lugar + ": ");
    }

    private void getTodosUsuariosConfirmadosCercanosQR(String url, final String ejeX, final String ejeY){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response == "empty")
                    Toast.makeText(getApplicationContext(),"NO EXISTEN CASOS POR LA ZONA",Toast.LENGTH_LONG).show();
                else{
                    Intent intent = new Intent(contenido.this,MapaInfeccion.class);
                    intent.putExtra("usuarios",response);
                    intent.putExtra("ejeX",ejeX);
                    intent.putExtra("ejeY",ejeY);
                    startActivity(intent);
                }
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
                parametros.put("request","getTodosUsuariosConfirmadosCercanosQR");
                parametros.put("ejeX",ejeX);
                parametros.put("ejeY",ejeY);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void insertarUbicacionUsuario(String url, final String idUbicacion, final String idUsuario){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("OK"))
                    Toast.makeText(getApplicationContext(),"QR REGISTRADO EXITOSAMENTE",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"HUBO UN ERROR, VUELVA A ESCANEAR\n" + response,Toast.LENGTH_LONG).show();
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

    private void getGravedad(String url, final Ubicacion ubicacion){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dato.setText("Gravedad : " + response);
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
                parametros.put("request","getGravedad");
                parametros.put("idUbicacion", "" + ubicacion.getIdUbicacion());
                parametros.put("ejeX",ubicacion.getEjeX());
                parametros.put("ejeY",ubicacion.getEjeY());
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
