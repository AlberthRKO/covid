package com.example.covid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class qr extends AppCompatActivity {

    Button btnScanner;
    TextView resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        btnScanner = findViewById(R.id.scanear);

        btnScanner.setOnClickListener(mOnclickListener);

    }

//    funcion de la camara que obtendremos el codigo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                resp.setText("Error al escanear el QR");
            }
        }
    }


    public View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case  R.id.scanear:
//                    Mandamos a llamar a la camara
                    new IntentIntegrator(qr.this).initiateScan();
                    break;
            }
        }
    };
}
