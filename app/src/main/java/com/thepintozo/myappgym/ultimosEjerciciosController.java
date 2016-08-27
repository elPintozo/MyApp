package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Resource.InfoRutinaSinConexion;
import Resource.Informacion;

public class ultimosEjerciciosController extends AppCompatActivity {

    private Button btnComenzarRutina;
    private Button btnVolverAMain;
    private ListView listaUltimosEjercicios;

    private InfoRutinaSinConexion infoRutina;
    private Extra extra;
    private int idRutina;
    private Informacion info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimos_ejercicios_controller);
        infoRutina = new InfoRutinaSinConexion(this);
        extra =  new Extra();
        info = new Informacion(this);
        /******************************************************************************************/
        //recibo info de la activity anterior
        Intent i = getIntent();
        Bundle recibir = i.getExtras();

        if(recibir!=null){
            idRutina = recibir.getInt("idRutina");
        }
        else{
            idRutina=0;
        }
        /******************************************************************************************/
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
                //crear un registro de la rutina y enviar id de ella
                int id = infoRutina.idProximaRutina();
                i.putExtra("idRutina",id);
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
        ArrayAdapter<String> adaptador;
        ArrayList<String> ejercicios = new ArrayList<>();
        ejercicios.add("No has ejercitado nada por ahora");
        String fecha = extra.getFechaCompleta(1);

        ArrayList<String> ultimosEjercicio = info.ultimosEjercicios(getApplicationContext(),fecha);

        if(ultimosEjercicio.size()!=0){
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ultimosEjercicio);
        }else{
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ejercicios);
        }
        listaUltimosEjercicios.setAdapter(adaptador);
    }
}
