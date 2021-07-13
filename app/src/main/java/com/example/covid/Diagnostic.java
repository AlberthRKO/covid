package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.security.ProviderInstaller;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Diagnostic extends AppCompatActivity {

    private Spinner spinner;
    private ProgressBar progressBar;
    private Button cancelBtn, sendBtn;
    RadioButton coughRdo,feverRdo,headAcheRdo,soreThroatRdo,shortnessBreathRdo,overSixtyRdo;
    String cough,fever,headAche,soreThroat,shortnessBreath,overSixty;
    int information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic);

        coughRdo = findViewById(R.id.coughYes);
        feverRdo = findViewById(R.id.feverYes);
        headAcheRdo = findViewById(R.id.headAcheYes);
        soreThroatRdo = findViewById(R.id.soreThroatYes);
        shortnessBreathRdo = findViewById(R.id.shortnessBreathYes);
        overSixtyRdo = findViewById(R.id.overSixtyYes);

        progressBar = findViewById(R.id.progreso);
        progressBar.setVisibility(View.INVISIBLE);

        cancelBtn = findViewById(R.id.cancelBtn);
        sendBtn = findViewById(R.id.btnSend);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Diagnostic.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.information));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                cough = coughRdo.isChecked() ? "1":"0";
                fever = feverRdo.isChecked() ? "1":"0";
                headAche = headAcheRdo.isChecked() ? "1":"0";
                soreThroat = soreThroatRdo.isChecked() ? "1":"0";
                shortnessBreath = shortnessBreathRdo.isChecked() ? "1":"0";
                overSixty = overSixtyRdo.isChecked() ? "1":"0";
                information = spinner.getSelectedItemPosition();


                String urlHosting = "https://covid-qr.tk/php/controlador/ControladorUsuario.php";
                String urlEmuladorLocal = "https://10.0.0.2:8080/covid-qr/php/controlador/ControladorUsuario.php";
                String urlLocal = "http://192.168.1.7:4000/api/diagnostics/add";
                sendDiagnostic(urlLocal);
            }
        });


    }

    private void sendDiagnostic(String url){

        progressBar.setVisibility(View.VISIBLE);
        Bundle get = getIntent().getExtras();
        final String idUsuario = get.getString("idUsuario");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String result = response.split("\"")[3];
                Intent intent = new Intent(getApplicationContext(),DiagnosticResult.class);
                intent.putExtra("result", "" + result);
                startActivity(intent);
                finish();
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
                ///api/diagnostics/add
                parametros.put("idUsuario", idUsuario);
                parametros.put("cough", cough);
                parametros.put("fever", fever);
                parametros.put("sixty", overSixty);
                parametros.put("headAche", headAche);
                parametros.put("soreThroat", soreThroat);
                parametros.put("shortnessBreath", shortnessBreath);
                parametros.put("information", String.valueOf(information));
                return parametros;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void updateAndroidSecurityProvider(Activity callingActivity) {
        try {
            ProviderInstaller.installIfNeeded(this);
        } catch (GooglePlayServicesRepairableException e) {
            // Thrown when Google Play Services is not installed, up-to-date, or enabled
            // Show dialog to allow users to install, update, or otherwise enable Google Play services.
            GooglePlayServicesUtil.getErrorDialog(e.getConnectionStatusCode(), callingActivity, 0);
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.e("SecurityException", "Google Play Services not available.");
        }
    }
}
