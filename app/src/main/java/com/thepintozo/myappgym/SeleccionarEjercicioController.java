package com.thepintozo.myappgym;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import Resource.InfoEjerciciosSinConexion;
import Resource.InfoMusculoSinConexion;

public class SeleccionarEjercicioController extends AppCompatActivity {

    private Button btnVolver;
    private int idMusculo;
    private int idRutina;
    private int idRepeticion;
    private ListView listaSeleccionarEjercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_ejercicio_controller);
        /******************************************************************************************/
        //recibo info de la seleccion anterior
        Intent i = getIntent();
        Bundle recibir = i.getExtras();
        if(recibir!=null){
            String o = recibir.getString("idMusculo");
            idRepeticion = recibir.getInt("idRepeticion");
            idMusculo =  Integer.parseInt(o);
        }
        else{
            idMusculo = 1;
            idRutina = 1;
        }
        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnVolver = (Button) findViewById(R.id.btnVolverSeleccionarEjercicio);

        /******************************************************************************************/
        //Inicializo lista correspondientes a la vista
        listaSeleccionarEjercicio = (ListView) findViewById(R.id.listSeleccionarEjercicio);
        cargarList(this);
        listaSeleccionarEjercicio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //--(i+1)
                finish();
                Intent i2 = new Intent(SeleccionarEjercicioController.this, ComenzarEjercicioController.class);
                i2.putExtra("idMusculo",idMusculo);
                i2.putExtra("idEjercicio",i+1);
                i2.putExtra("idRutina",idRutina);
                startActivity(i2);
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
                Intent i = new Intent(SeleccionarEjercicioController.this, SeleccionarMusculoController.class);
                startActivity(i);
            }
        });
    }

    private void cargarList(Context context) {
        ArrayAdapter<String> adaptador;
        InfoEjerciciosSinConexion info = new InfoEjerciciosSinConexion(context);
        //------------------------------------------------------------------------------------------
        /*ArrayList<Ejercicio> s = info.obtenerEjercicios();
        String salida="";
        for (Ejercicio e :s) {
            salida = salida+e.idEjercicio+"\n";
        }

        Toast toast = Toast.makeText(this, "Id: "+salida, Toast.LENGTH_LONG);
        toast.show();*/
        //------------------------------------------------------------------------------------------
        ArrayList<Ejercicio> ejercicios = info.obtenerEjerciciosDeUnMusculo(idMusculo);
        ArrayList<String> listaEjerciciosOffLine = new ArrayList<>();


        if (ejercicios != null) {
            for (Ejercicio e :ejercicios) {
                listaEjerciciosOffLine.add(e.nombreEjercicio);
            }
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaEjerciciosOffLine);
        } else {
            String[] listaEjercicios = {"Ejercicio 1"};//, "Ejercicio 2", "Ejercicio 3"};
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaEjercicios);
        }
        listaSeleccionarEjercicio.setAdapter(adaptador);
    }
}
