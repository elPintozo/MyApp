package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ultimosEjerciciosController extends AppCompatActivity {

    private Button btnComenzarRutina;
    private Button btnVolverAMain;
    private ListView listaUltimosEjercicios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimos_ejercicios_controller);

        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnComenzarRutina = (Button)findViewById(R.id.btnComenzarRutina);
        btnVolverAMain = (Button)findViewById(R.id.btnVolverAMain);
        /******************************************************************************************/
        //Inicializo lista correspondientes a la vista
        listaUltimosEjercicios = (ListView)findViewById(R.id.listUltimosEjerciciosRealizados);
        cargarList();
        /******************************************************************************************/
        //asignacion Onclick a los botones
        /***************************
         BOTON COMENZAR RUTINA
         ***************************/
        btnComenzarRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(ultimosEjerciciosController.this, MiRutinaController.class);
                startActivity(i);
            }
        });
        /***************************
         BOTON VOLVER AL INICIO
         ***************************/
        btnVolverAMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(ultimosEjerciciosController.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    /**
     * Funcion encargada de listar musculos ejercitados la rutina anterior
     */
    private void cargarList() {
        String[] Ejercicios = {"Ejercicio 1", "Ejercicio 2", "Ejercicio 3"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Ejercicios);
        listaUltimosEjercicios.setAdapter(adaptador);
    }
}
