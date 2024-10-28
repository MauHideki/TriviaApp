package com.example.triviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Opciones extends AppCompatActivity {

    Button btnFutbolAmericano;
    Button btnSoccer;
    Button btnBaseball;
    Button btnBasketball;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.opciones);

        //Boton de seleccion de futbol americano
        Button btnFutbolAmericano = findViewById(R.id.btnAmericano);
        btnFutbolAmericano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Opciones.this, AmericanoQ1.class);
                startActivity(intent);
            }
        });

        //Boton de seleccion de futbol soccer
        Button btnSoccer = findViewById(R.id.btnSoccer);
        btnSoccer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Opciones.this, SoccerQ1.class);
                startActivity(intent);
            }
        });

        //Boton de seleccion de Baseball
        Button btnBaseball = findViewById(R.id.btnBaseball);
        btnBaseball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Opciones.this, BaseballQ1.class);
                startActivity(intent);
            }
        });

        //Boton de seleccion de Basketball
        Button btnBasketball = findViewById(R.id.btnBasketball);
        btnBasketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Opciones.this, BasketballQ1.class);
                startActivity(intent);
            }
        });
    }
}