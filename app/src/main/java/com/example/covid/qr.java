package com.example.covid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class qr extends AppCompatActivity {

    Button btnScanner, btnUbiQr,paginaWeb;
    TextView resp;
    ImageView confir, recup, muer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        btnScanner = findViewById(R.id.scanear);
        btnUbiQr = findViewById(R.id.ubicacionQR);
        paginaWeb = findViewById(R.id.pagina);


        final String link = "https://covid-qr.tk";
        paginaWeb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        btnScanner.setOnClickListener(mOnclickListener);

        confir = (ImageView) findViewById(R.id.conf);
        recup= (ImageView) findViewById(R.id.rec);
        muer = (ImageView) findViewById(R.id.mue);
        rotarImagen(confir);
        rotarImagen(recup);
        rotarImagen(muer);

        btnUbiQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlHosting = "https://covid-qr.tk/php/controlador/ControladorUbicacion.php";
                setUbicaciones(urlHosting);

            }
        });

    }

    private void setUbicaciones(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("empty")){
                    Toast.makeText(getApplicationContext(),"UBICACIONES VACIAS",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent i = new Intent(qr.this,MapaQR.class);
                    i.putExtra("ubicaciones",response);
                    startActivity(i);
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
                parametros.put("request","getTodasUbicaciones");
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

//    funcion de la camara que obtendremos el codigo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

//        Capturamos el codigo
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
//        Validamos que no este vacio
        if (result !=null){
            if (result.getContents()!= null){

//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Estado de Covid");
//                builder.setMessage(result.getContents());
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//                resp.setText("Estado Covid :\n"+result.getContents());
                Bundle get = getIntent().getExtras();
        //        recibimos el dato
                String idUsuario = get.getString("idUsuario");
                Intent intent = new Intent(qr.this,contenido.class);
                intent.putExtra("CodigoQR", result.getContents());
                intent.putExtra("idUsuario", idUsuario);
                startActivity(intent);
            }else {
                Toast.makeText(qr.this,"Cancelaste el Escaneo", Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    public View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case  R.id.scanear:
//                    Mandamos a llamar a la camara
                    IntentIntegrator integrator = new IntentIntegrator(qr.this);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                    integrator.setPrompt("Escanea un QR de COVID");
                    integrator.setCameraId(0);
                    integrator.setOrientationLocked(false);
                    integrator.setBeepEnabled(false);
                    integrator.setCaptureActivity(CaptureActivityPortrait.class);
                    integrator.setBarcodeImageEnabled(false);
                    integrator.initiateScan();
                    break;
            }
        }
    };

    private void rotarImagen(View view){
        RotateAnimation animation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(10000);
        animation.setRepeatCount(Animation.INFINITE);
        view.startAnimation(animation);
    }
}
