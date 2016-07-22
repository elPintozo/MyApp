package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SeleccionarMusculoController extends AppCompatActivity {

    private Button btnVolver;

    private ListView listaSeleccionarMusculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_musculo_controller);

        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnVolver = (Button)findViewById(R.id.btnVolverSeleccionarMusculo);

        /******************************************************************************************/
        //Inicializo lista correspondientes a la vista
        listaSeleccionarMusculo = (ListView)findViewById(R.id.listSeleccionarMusculo);
        cargarList();
        /******************************************************************************************/
        //asignacion OnItemClickL a la lista
        listaSeleccionarMusculo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //--(i+1)
                finish();
                Intent i2 = new Intent(SeleccionarMusculoController.this, SeleccionarEjercicioController.class);
                startActivity(i2);
                //--
            }
        });
        /******************************************************************************************/
        //asignacion Onclick a los botones
        /***************************
         BOTON VOLVER
         ***************************/
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(SeleccionarMusculoController.this, MiRutinaController.class);
                startActivity(i);
            }
        });
    }

    private void cargarList() {
        String[] Ejercicios = {"Ejercicio 1", "Ejercicio 2", "Ejercicio 3"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Ejercicios);
        listaSeleccionarMusculo.setAdapter(adaptador);
    }
}
