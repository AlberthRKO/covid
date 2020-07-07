package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

//    Button iniciar;
//    TextInputLayout user,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        onAndrea();
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
