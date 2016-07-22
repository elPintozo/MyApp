package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComenzarEjercicioController extends AppCompatActivity {

    private Button btnDescanzarEjercicio;
    private Button btnComenzarEjercicio;
    private Button btnTermineEjericio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comenzar_ejercicio_controller);

        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnDescanzarEjercicio = (Button)findViewById(R.id.btnDescanzar);
        btnComenzarEjercicio = (Button)findViewById(R.id.btnContinuarConEjercicio);
        btnTermineEjericio = (Button)findViewById(R.id.btnTermineEjercicio);

        /******************************************************************************************/
        //asignacion Onclick a los botones
        /***************************
         BOTON DESCANZO
         ***************************/
        btnDescanzarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        /***************************
         BOTON CONTINUAR
         ***************************/
        btnComenzarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        /***************************
         BOTON FINALIZAR
         ***************************/
        btnTermineEjericio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(ComenzarEjercicioController.this, MiRutinaController.class);
                startActivity(i);
            }
        });

    }
}
