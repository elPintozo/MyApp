package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

import Model.Musculo;
import Model.RecyclerAdapterMusculo;
import Resource.InfoMusculoSinConexion;

public class SeleccionarMusculoController extends AppCompatActivity {

    private Button btnVolver;
    private int idRutina;
    private ArrayList<Musculo> musculos;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_musculo_controller);
        /******************************************************************************************/
        //recibo info de la activity anterior
        Intent i = getIntent();
        Bundle recibir = i.getExtras();

        if(recibir!=null){
            idRutina = recibir.getInt("idRutina");
        }
        else{
            idRutina = 0;
        }
        /******************************************************************************************/
        //inicializo los cardview
        /*----------------------------------------------------------------------------------------*/
        /*cargo el recyclerView con el cardview de los musculos*/
        recycler = (RecyclerView) findViewById(R.id.recyclerMusculo);
        layoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
        //layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);


        InfoMusculoSinConexion inf = new InfoMusculoSinConexion(this);
        //pido el listado de los musculos almacenados en la base de datos
        musculos = inf.obtenerMusculos();
        adapter = new RecyclerAdapterMusculo(musculos, idRutina, this);
        recycler.setAdapter(adapter);
        /*----------------------------------------------------------------------------------------*/
        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnVolver = (Button)findViewById(R.id.btnVolverSeleccionarMusculo);

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
}
