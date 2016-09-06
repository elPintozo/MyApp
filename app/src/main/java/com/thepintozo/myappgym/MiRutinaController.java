package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Model.CapsulaEjercicio;
import Model.RecyclerAdapterMiRutina;
import Resource.Informacion;


public class MiRutinaController extends AppCompatActivity {

    private Button btnTerminePorHoy;
    private Button btnNuevoEjercicio;
    private int idRutina;
    private Extra extra;
    private Informacion info;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_rutina_controller);
        info = new Informacion(this);
        /******************************************************************************************/
        //recibo info de la seleccion anterior
        Intent i = getIntent();
        Bundle recibir = i.getExtras();
        if(recibir!=null){
            idRutina = recibir.getInt("idRutina");
        }
        else{
            idRutina = 1;
        }
        //inicializo los cardview
        /*----------------------------------------------------------------------------------------*/
        /*cargo el recyclerView con el cardview de los musculos*/
        recycler = (RecyclerView) findViewById(R.id.recyclerMiRutina);
        //layoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);


        String fecha = extra.getFechaCompleta(1);
        //pido a la base de datos info de los musculo ejercitados junto a la cantidad de repeticiones
        //que han llevado a cabo con diferentes ejericios
        ArrayList<CapsulaEjercicio> ultimos = info.EjerciciosRutinaActual(getApplicationContext(),fecha);
        adapter = new RecyclerAdapterMiRutina(ultimos, this);
        recycler.setAdapter(adapter);

        /*----------------------------------------------------------------------------------------*/
        //Inicializo los botones correspondientes a la vista
        btnNuevoEjercicio = (Button)findViewById(R.id.btnAnadirEjercicio);
        btnTerminePorHoy  = (Button)findViewById(R.id.btnNoMasPorHoy);

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
                i2.putExtra("idRutina",idRutina);
                startActivity(i2);
            }
        });
    }
}
