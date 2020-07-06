package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

//    Button iniciar;
//    TextInputLayout user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
}
