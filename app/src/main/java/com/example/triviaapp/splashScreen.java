package com.example.triviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;


public class splashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Configurar el tiempo de duracion del splash (3 segs)

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();//CIERRA LA ACTIVIADAD DEL SPLASH PARA QUE NO VUELVA A HACER BACK
            }
        }, 3000);
    }
}