package com.example.covid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class qr extends AppCompatActivity {

    Button btnScanner, btnUbiQr;
    TextView resp;
    ImageView confir, recup, muer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        btnScanner = findViewById(R.id.scanear);
        btnUbiQr = findViewById(R.id.ubicacionQR);


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
                Intent i = new Intent(qr.this,MapaQR.class);
                startActivity(i);
            }
        });

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
