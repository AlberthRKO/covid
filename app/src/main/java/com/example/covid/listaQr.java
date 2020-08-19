package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.view.ViewGroup.LayoutParams;

public class listaQr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_qr);

        String servidor = "https://covid-qr.tk/php/controlador/ControladorUbicacionUsuario.php";
        getQRRegistrados(servidor);
    }

    private void getQRRegistrados(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("empty")){
                    Toast.makeText(getApplicationContext(),"NO TIENES REGISTRADO NINGUN CÓDIGO QR",Toast.LENGTH_LONG).show();
                }
                else{
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<UbicacionUsuario>>(){}.getType();
                    List<UbicacionUsuario> ubicacionUsuarioList = gson.fromJson(response, listType);
                    TableLayout listaQRs = findViewById(R.id.listaQRs);
                    for(int i=0; i<ubicacionUsuarioList.size(); i++){
                        if(i==20)
                            break;
                        TableRow tableRow = (TableRow) listaQRs.getChildAt(i + 1);
                        if(i%2==0)//Puedes quitar este If si no te gustan los plomos
                            tableRow.setBackgroundColor(Color.GRAY);
                        String []fecha = ubicacionUsuarioList.get(i).getFecha().split(" ");
                        TextView v1 = (TextView)tableRow.getChildAt(0);
                        TextView v2 = (TextView)tableRow.getChildAt(1);
                        TextView v3 = (TextView)tableRow.getChildAt(2);
                        v1.setText(ubicacionUsuarioList.get(i).getNombre());
                        v2.setText(fecha[0]);
                        v3.setText(fecha[1]);
                    }
                    /*TextView textView;
                    for(UbicacionUsuario ubicacionUsuario : ubicacionUsuarioList){
                        String []fecha = ubicacionUsuario.getFecha().split(" ");
                        TableRow row = new TableRow(getBaseContext());
                        textView = new TextView(getBaseContext());
                        textView.setHeight(75);
                        textView.setWidth(180);
                        textView.setTextSize(12);
                        textView.setTextColor(Color.BLACK);
                        textView.setGravity(Gravity.CENTER);
                        textView.setText(ubicacionUsuario.getNombre());
                        row.addView(textView);
                        textView = new TextView(getBaseContext());
                        textView.setHeight(75);
                        textView.setWidth(180);
                        textView.setTextSize(12);
                        textView.setTextColor(Color.BLACK);
                        textView.setGravity(Gravity.CENTER);
                        textView.setText(fecha[0]);
                        row.addView(textView);
                        textView = new TextView(getBaseContext());
                        textView.setHeight(75);
                        textView.setWidth(180);
                        textView.setTextSize(12);
                        textView.setTextColor(Color.BLACK);
                        textView.setGravity(Gravity.CENTER);
                        textView.setText(fecha[1]);
                        row.addView(textView);
                        listaQRs.addView(row);
                    }*/
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
                Bundle get = getIntent().getExtras();
                //        recibimos el dato
                String idUsuario = get.getString("idUsuario");
                parametros.put("request","getQRRegistrados");
                parametros.put("idUsuario", "" + idUsuario);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}