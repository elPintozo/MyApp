package com.thepintozo.myappgym;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

    private int idMusculo;
    private int idRutina;
    private ArrayList<Ejercicio> ejercicios;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_ejercicio_controller);

        toolbar = (Toolbar) findViewById(R.id.toolbar_seleccionar_ejercicio);
        this.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeleccionarEjercicioController.this, SeleccionarMusculoController.class);
                startActivity(i);
            }
        });
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
        /*cargo el recyclerView con el cardview de los ejercicios*/
        recycler = (RecyclerView) findViewById(R.id.recyclerEjercicio);
        layoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
        //layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);


        InfoEjerciciosSinConexion info = new InfoEjerciciosSinConexion(this);
        //pido el listado de los ejercicios almacenados en la base de datos, asociados a un musculo
        //previamente seleccionado
        ejercicios = info.obtenerEjerciciosDeUnMusculo(idMusculo);
        adapter = new RecyclerAdapterEjercicio(ejercicios,this,idRutina,idMusculo);
        recycler.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_toolbar_seleccionar_ejercicio,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            /*case R.id.volver_seleccionar_ejercicio:
                finish();
                Intent i = new Intent(SeleccionarEjercicioController.this, SeleccionarMusculoController.class);
                startActivity(i);*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
