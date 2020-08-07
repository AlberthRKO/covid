package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

//    Button iniciar;
//    TextInputLayout user,pass;
    EditText ci;
    EditText contrasena;
    Button btnIniciar;
    Spinner extensiones;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        extensiones = findViewById(R.id.extension);
        ci = (EditText) findViewById(R.id.txtBoxCi);
        contrasena = (EditText) findViewById(R.id.txtBoxContrasena);
        btnIniciar = (Button)findViewById(R.id.btnIniciar);
        progressBar = findViewById(R.id.progreso);

        progressBar.setVisibility(View.INVISIBLE);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Login.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.extensiones));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        extensiones.setAdapter(myAdapter);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlHosting = "https://covid-qr.tk/php/controlador/ControladorUsuario.php";
                String urlEmuladorLocal = "https://10.0.0.2:8080/covid-qr/php/controlador/ControladorUsuario.php";
                String urlLocal = "https://192.168.1.2:8080/covid-qr/php/controlador/ControladorUsuario.php";
                validarUsuario(urlHosting);

            }
        });


        onAndrea();
    }
    private void validarUsuario(String url){

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("empty")){
                    Toast.makeText(getApplicationContext(),"C.I. O CONTRASEÑA INCORRECTOS",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),qr.class);
                    Gson gson = new Gson();
                    Usuario usuario = gson.fromJson(response, Usuario.class);
                    intent.putExtra("idUsuario", "" + usuario.getIdUsuario());
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
                parametros.put("request","getByCiContrasena");
                parametros.put("ci", ci.getText().toString()+extensiones.getSelectedItem().toString());
                parametros.put("contrasena",contrasena.getText().toString());
                return parametros;

            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
//    private  Boolean validacionUser(){
//        String val = user.getEditText().getText().toString();
//        String sinEspacio = "\\A\\w{4,20}\\z";
//
//        if (val.isEmpty()){
//            user.setError("Campo Vacio");
//            return false;
//        }else if (val.length()>=15){
//            return false;
//        }else if (!val.matches(sinEspacio)){
//            user.setError("Usuario Largo");
//            return false;
//        }else{
//            user.setError(null);
//            user.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    public void loginUser (View view){
//        if (!validacionUser()){
//            return;
//        }
//    }

    public void onAndrea() {

        Button entry = (Button) findViewById(R.id.registrar);

        entry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://covid-qr.tk/registro.html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    public void contenido(View view){

        Intent intent = new Intent(Login.this,qr.class);
        startActivity(intent);
        finish();
    }

}
