package com.thepintozo.myappgym;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Repeticion;
import Model.Serie;
import Resource.InfoRepeticionSinConexion;
import Resource.InfoRutinaSinConexion;

public class ComenzarEjercicioController extends AppCompatActivity {

    private Button btnDescanzarEjercicio;
    private Button btnComenzarEjercicio;
    private Button btnTermineEjericio;
    private Button btnUnidad;
    private EditText txtpeso;
    private EditText txtseries;

    private Chronometer tiempoDescanso;
    private Chronometer tiempoEjericio;

    private Extra ayuda;
    private ListView listRepeticiones;
    private int idMusculo;
    private int idEjercicio;
    private int idRutina;
    private int Umedida=1;
    private int repeticionesInicial;
    private int tiempoEjercicioInicial;
    private int tiempoDescansoInicial;
    private int repeticionesFinal;
    private ArrayList<Serie> series;

    private InfoRepeticionSinConexion infoRepeticion;
    private InfoRutinaSinConexion infoRutina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comenzar_ejercicio_controller);
        series = new ArrayList<>();
        infoRepeticion = new InfoRepeticionSinConexion(this);
        infoRutina =  new InfoRutinaSinConexion(this);
        /******************************************************************************************/
        Intent i = getIntent();
        Bundle recibir = i.getExtras();

        if(recibir!=null){
            idMusculo = recibir.getInt("idMusculo");
            idEjercicio = recibir.getInt("idEjercicio");
            idRutina = recibir.getInt("idRutina");
        }
        else{
            idMusculo = 0;
            idEjercicio = 0;
            idRutina=0;
        }
        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnDescanzarEjercicio = (Button)findViewById(R.id.btnDescanzar);
        btnComenzarEjercicio = (Button)findViewById(R.id.btnContinuarConEjercicio);
        btnTermineEjericio = (Button)findViewById(R.id.btnTermineEjercicio);
        btnUnidad  = (Button)findViewById(R.id.btnUmedida);
        btnComenzarEjercicio.setFocusable(true);
        btnComenzarEjercicio.setFocusableInTouchMode(true);///add this line
        btnComenzarEjercicio.requestFocus();
        /******************************************************************************************/
        //Inicializar EditText en la vista
        txtpeso = (EditText)findViewById(R.id.txtpeso);
        txtseries = (EditText)findViewById(R.id.txtRepeticiones);

        //Inicializar cronometro en la vista
        tiempoDescanso = (Chronometer)findViewById(R.id.txtSegDescanso);
        tiempoEjericio = (Chronometer)findViewById(R.id.txtSegComenzarEjercicio);

        /******************************************************************************************/
        //Inicializo lista correspondientes a la vista
        listRepeticiones = (ListView)findViewById(R.id.listRepeticiones);

        /******************************************************************************************/

        //asignacion Onclick a los botones
        /***************************
         BOTON DESCANZO
         ***************************/
        btnDescanzarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            tiempoEjercicioInicial = ayuda.enSegundos(String.valueOf(tiempoEjericio.getText()));

            if(tiempoEjercicioInicial!=0){
                tiempoDescanso.setBase(SystemClock.elapsedRealtime());
                tiempoDescanso.start();
                tiempoEjericio.stop();
                tiempoEjericio.setBase(SystemClock.elapsedRealtime());
                ayuda.Mensaje(getApplicationContext(),"Tómate un respiro!");
            }
            }
        });
        /***************************
         BOTON CONTINUAR
         ***************************/
        btnComenzarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiempoDescansoInicial= ayuda.enSegundos(String.valueOf(tiempoDescanso.getText()));
                if(tiempoDescansoInicial!=0 ){
                    actualizarSeries();
                }
                if(!txtseries.getText().toString().equals("")){
                    tiempoEjericio.setBase(SystemClock.elapsedRealtime());
                    tiempoEjericio.start();
                    tiempoDescanso.stop();
                    tiempoDescanso.setBase(SystemClock.elapsedRealtime());
                    ayuda.Mensaje(getApplicationContext(),"Adelante!");
                }
                else {
                    if(txtpeso.getText().toString().equals("")){
                        ayuda.Mensaje(getApplicationContext(),"Debes ingresar el Peso que levantaras");
                    }
                    if(txtseries.getText().toString().equals("")){
                        ayuda.Mensaje(getApplicationContext(),"Debes ingresar la cantidad de veces que lo harás");
                    }
                }
            }
        });
        /***************************
         BOTON UNIDAD DE MEDIDA
         ***************************/
        btnUnidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnUnidad.getText().equals("Kilo")){
                    btnUnidad.setText("Libra");
                    Umedida=0;
                }
                else{
                    btnUnidad.setText("Kilo");
                    Umedida=1;
                }
            }
        });
        /***************************
         BOTON FINALIZAR
         ***************************/
        btnTermineEjericio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiempoDescansoInicial = ayuda.enSegundos(String.valueOf(tiempoDescanso.getText()));

                if(tiempoEjercicioInicial==0 && tiempoDescansoInicial==0){
                    ayuda.Mensaje(getApplicationContext(),"Debes precionar el botón Comenzar \n" +
                                                          "para comenzar la serie.");
                }
                else {
                    if(tiempoEjercicioInicial!=0){}
                    if(tiempoDescansoInicial!=0){
                        actualizarSeries();
                    }
                    finish();
                    Intent i = new Intent(ComenzarEjercicioController.this, MiRutinaController.class);
                    i.putExtra("idRutina",idRutina);
                    startActivity(i);
                }
            }
        });
    }
    private void actualizarSeries() {
        nuevaRepeticion();
        ArrayList<String> seriess = new ArrayList<>();
        if(series.size()!=0){
            for (Serie s: series ) {
                seriess.add("Series: "+s.veces+" Peso: "+s.peso+" Tiempo D: "+s.tDescanso+" T Ejercicio: "+s.tEjercicio);
            }
        }
        else{
            seriess.add("Vamos con todo.");
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, seriess);
        listRepeticiones.setAdapter(adaptador);
    }

    public void nuevaRepeticion(){

        int id = infoRepeticion.proximaRepeticion();
        int peso = Integer.parseInt(txtpeso.getText().toString());
        int s = Integer.parseInt(txtseries.getText().toString());

        infoRepeticion.cargarDatosDeRepeticion(id,idEjercicio,peso,s,tiempoDescansoInicial,tiempoEjercicioInicial,Umedida);
        infoRutina.cargarDatosDeRutinaRepeticion(idRutina,id);

        Serie serie = new Serie(id,peso,s,tiempoDescansoInicial,tiempoEjercicioInicial);
        series.add(serie);
    }
}

