package com.example.proyectofinal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.proyectofinal.Activity_menu;
import com.example.proyectofinal.R;

public class GraciasporComprarActivity extends AppCompatActivity {

    LottieAnimationView animation_view;

    TextView txtcompra,txtgracias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graciaspor_comprar);
        animation_view = findViewById(R.id.animation_view);
        txtcompra= findViewById(R.id.txtcompra);
        txtgracias=findViewById(R.id.txtgracias);

        txtcompra.setVisibility(View.GONE);
        txtgracias.setVisibility(View.GONE);


        //btnCrearCuenta.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animation_view.pauseAnimation();
                animation_view.setAnimation(R.raw.checkedred);
                animation_view.playAnimation(); // Ocultar la ProgressBar después de 2 segundos
                txtcompra.setVisibility(View.VISIBLE);
                txtgracias.setVisibility(View.VISIBLE);

                finish();

            }
        }, 3000);



    }
}