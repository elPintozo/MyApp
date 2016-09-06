package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import Model.RecyclerAdapterUltimosMusculosEjercitados;
import Resource.InfoRutinaSinConexion;
import Resource.Informacion;

public class ultimosEjerciciosController extends AppCompatActivity {

    private Button btnComenzarRutina;
    private Button btnVolverAMain;

    private InfoRutinaSinConexion infoRutina;
    private Extra extra;
    private int idRutina;
    private Informacion info;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

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
        //inicializo los cardview
        /*----------------------------------------------------------------------------------------*/
        /*cargo el recyclerView con el cardview de los musculos*/
        recycler = (RecyclerView) findViewById(R.id.recyclerUltimosEjercicios);
        layoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
        //layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        String fecha = extra.getFechaCompleta(1);
        ArrayList<String> ultimosEjercicio = info.ultimosEjercicios(getApplicationContext(),fecha);
        adapter = new RecyclerAdapterUltimosMusculosEjercitados(ultimosEjercicio, fecha, this);
        recycler.setAdapter(adapter);
        /*----------------------------------------------------------------------------------------*/
        /******************************************************************************************/
        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnComenzarRutina = (Button)findViewById(R.id.btnComenzarRutina);
        btnVolverAMain = (Button)findViewById(R.id.btnVolverAMain);
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
}
