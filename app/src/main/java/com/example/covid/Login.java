package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Login extends AppCompatActivity {

//    Button iniciar;
//    TextInputLayout user,pass;
    EditText ci;
    EditText contrasena;
    Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ci = (EditText) findViewById(R.id.txtBoxCi);
        contrasena = (EditText) findViewById(R.id.txtBoxContrasena);
        btnIniciar = (Button)findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUsuario("http://192.168.1.2:8080/covid-qr/php/controlador/ControladorUsuario.php");
            }
        });


        onAndrea();
    }
    private void validarUsuario(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("empty")){
                    Toast.makeText(getApplicationContext(),"C.I. O CONTRASEÑA INCORRECTOS",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),qr.class);
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
                parametros.put("ci", ci.getText().toString()+"CH.");
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
                Uri uri = Uri.parse("https://covid-qr.netlify.app/registro.html");
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
