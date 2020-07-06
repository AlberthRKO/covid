package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  static  int Screen = 5000;

    //Variables
    Animation topAnim, bottomAnim;
    ImageView logo,logaso;
    TextView nombre, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animacion creamos la animacion
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.boton_animation);

        logo = findViewById(R.id.imageView);
        nombre = findViewById(R.id.textView);
        slogan= findViewById(R.id.textView2);
        logaso = findViewById(R.id.logaso);

//        aplicamos la animacion
        logo.setAnimation(topAnim);
        nombre.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);
        logaso.setAnimation(bottomAnim);


//        cambiamos a la vista del login
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Login.class);
//                asemos una transiscion para que la imagen se una a la otra
//                Pair[] pairs= new Pair[2];
//                pairs[0] = new Pair<View,String>(logo,"logo_imagen");
//                pairs[1] = new Pair<View,String>(nombre,"logo_texto");
//                ActivityOptions options = new ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                //iniciamos el activiti
                startActivity(intent);
                finish();
            }
        }, Screen);


    }
}
