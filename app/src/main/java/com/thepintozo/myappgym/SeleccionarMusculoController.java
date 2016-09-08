package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import Model.Musculo;
import Model.RecyclerAdapterMusculo;
import Resource.InfoMusculoSinConexion;

public class SeleccionarMusculoController extends AppCompatActivity {

    private int idRutina;
    private ArrayList<Musculo> musculos;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_musculo_controller);

        toolbar = (Toolbar) findViewById(R.id.toolbar_seleccionar_musculo);
        this.setSupportActionBar(toolbar);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_toolbar_seleccionar_musculo,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.volver_seleccionar_musculo:
                finish();
                Intent i = new Intent(SeleccionarMusculoController.this, MiRutinaController.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
