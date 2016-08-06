package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MiRutinaController extends AppCompatActivity {

    private Button btnTerminePorHoy;
    private Button btnNuevoEjercicio;
    private int idRutina;
    private Extra extra;
    private ListView listaEjerciciosRealizados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_rutina_controller);
        /******************************************************************************************/
        //recibo info de la seleccion anterior
        Intent i = getIntent();
        Bundle recibir = i.getExtras();
        if(recibir!=null){
            String o = recibir.getString("idRutina");
            idRutina =  Integer.parseInt(o);
            extra.Mensaje(this,"id: "+idRutina);
        }
        else{
            //Toast toast = Toast.makeText(this, "no aporta nada", Toast.LENGTH_LONG);
            //toast.show();
            idRutina = 1;
        }
        /******************************************************************************************/
        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnNuevoEjercicio = (Button)findViewById(R.id.btnAnadirEjercicio);
        btnTerminePorHoy  = (Button)findViewById(R.id.btnNoMasPorHoy);

        /******************************************************************************************/
        //Inicializo lista correspondientes a la vista
        listaEjerciciosRealizados = (ListView)findViewById(R.id.listEjerciciosMiRutinaActual);
        cargarList();

        /******************************************************************************************/
        //asignacion Onclick a los botones
        /***************************
         BOTON TERMINAR RUTINA
         ***************************/
        btnTerminePorHoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(MiRutinaController.this, ultimosEjerciciosController.class);
                startActivity(i);
            }
        });
        /***************************
         BOTON AÑADIR EJERCICIO
         ***************************/
        btnNuevoEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i2 = new Intent(MiRutinaController.this, SeleccionarMusculoController.class);
                startActivity(i2);
            }
        });
    }

    private void cargarList() {
        String[] Ejercicios = {"Ejercicio 1", "Ejercicio 2", "Ejercicio 3"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Ejercicios);
        listaEjerciciosRealizados.setAdapter(adaptador);
    }
}
