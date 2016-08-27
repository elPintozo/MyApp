package com.thepintozo.myappgym;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import Model.Ejercicio;
import Model.Musculo;
import Model.RecyclerAdapterEjercicio;
import Resource.InfoEjerciciosSinConexion;
import Resource.InfoMusculoSinConexion;

public class SeleccionarEjercicioController extends AppCompatActivity {

    private Button btnVolver;
    private int idMusculo;
    private int idRutina;
    private ArrayList<Ejercicio> ejercicios;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_ejercicio_controller);
        /******************************************************************************************/
        //recibo info de la seleccion anterior
        Intent i = getIntent();
        Bundle recibir = i.getExtras();
        if(recibir!=null){
            idRutina = recibir.getInt("idRutina");
            idMusculo =  recibir.getInt("idMusculo");
        }
        else{
            idMusculo = 1;
            idRutina = 1;
        }
        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnVolver = (Button) findViewById(R.id.btnVolverSeleccionarEjercicio);

        /******************************************************************************************/
        /*cargo el recyclerView con el cardview de los ejercicios*/
        recycler = (RecyclerView) findViewById(R.id.recyclerEjercicio);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);


        InfoEjerciciosSinConexion info = new InfoEjerciciosSinConexion(this);
        //pido el listado de los ejercicios almacenados en la base de datos, asociados a un musculo
        //previamente seleccionado
        ejercicios = info.obtenerEjerciciosDeUnMusculo(idMusculo);
        adapter = new RecyclerAdapterEjercicio(ejercicios,this,idRutina,idMusculo);
        recycler.setAdapter(adapter);

        /******************************************************************************************/
        //asignacion Onclick a los botones
        /***************************
         BOTON VOLVER
         ***************************/
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(SeleccionarEjercicioController.this, SeleccionarMusculoController.class);
                startActivity(i);
            }
        });
    }
}
