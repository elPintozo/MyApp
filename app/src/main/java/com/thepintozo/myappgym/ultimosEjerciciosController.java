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

import Model.RecyclerAdapterUltimosMusculosEjercitados;
import Resource.InfoRutinaSinConexion;
import Resource.Informacion;

public class ultimosEjerciciosController extends AppCompatActivity {

    private InfoRutinaSinConexion infoRutina;
    private Extra extra;
    private int idRutina;
    private Informacion info;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimos_ejercicios_controller);
        infoRutina = new InfoRutinaSinConexion(this);
        extra =  new Extra();
        info = new Informacion(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar_ultimos_ejercicios);
        this.setSupportActionBar(toolbar);
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
        String fechaa = info.diaAnterior(fecha);
        adapter = new RecyclerAdapterUltimosMusculosEjercitados(ultimosEjercicio, fechaa, this);
        recycler.setAdapter(adapter);
        /*----------------------------------------------------------------------------------------*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_toolbar_rutina_anterior,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.volver_menu_principal:
                finish();
                Intent i = new Intent(ultimosEjerciciosController.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.comenzar_rutina:
                finish();
                Intent i2 = new Intent(ultimosEjerciciosController.this, MiRutinaController.class);
                //crear un registro de la rutina y enviar id de ella
                int id = infoRutina.idProximaRutina();
                i2.putExtra("idRutina",id);
                startActivity(i2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
