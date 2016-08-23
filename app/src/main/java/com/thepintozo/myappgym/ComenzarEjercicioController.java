package com.thepintozo.myappgym;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import Model.Serie;
import Resource.InfoRepeticionSinConexion;
import Resource.InfoRutinaSinConexion;

public class ComenzarEjercicioController extends AppCompatActivity {

    private Button btnDescanzarEjercicio;
    private Button btnComenzarEjercicio;
    private Button btnTermineEjericio;
    private Button btnUnidad;

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

    private int pesoF=0;
    private int seriesF=0;
    private ArrayList<Serie> series;

    private InfoRepeticionSinConexion infoRepeticion;
    private InfoRutinaSinConexion infoRutina;

    private Spinner spinnerPesos;
    private Spinner spinnerSeries;
    private List<String> Lpesos;
    private List<String> Lseries;

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
        //Inicializo los spinner correspondientes a la vista
        spinnerPesos = (Spinner)findViewById(R.id.spinnerPeso);
        spinnerSeries = (Spinner)findViewById(R.id.spinnerSeries);
        cargarSpinners();

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
                tiempoEjericio.setBase(SystemClock.elapsedRealtime());
                tiempoEjericio.start();
                tiempoDescanso.stop();
                tiempoDescanso.setBase(SystemClock.elapsedRealtime());
                ayuda.Mensaje(getApplicationContext(),"Adelante!");
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
                if(tiempoDescansoInicial!=0){
                    actualizarSeries();
                }
                finish();
                Intent i = new Intent(ComenzarEjercicioController.this, MiRutinaController.class);
                i.putExtra("idRutina",idRutina);
                startActivity(i);
            }
        });
        /******************************************************************************************/
        //asignacion Onclick a los botones
        /***************************
         Spinner seleccion de Peso
         ***************************/
        spinnerPesos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pesoF = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /***************************
         Spinner seleccion de Serie
         ***************************/
        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seriesF = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void cargarSpinners() {
        Lpesos = new ArrayList<String>();
        Lseries = new ArrayList<String>();

        Lseries.add("0");
        Lpesos.add("0");

        for(int x =5, y=5 ; x<125 ; x=x+5,y++){
            Lseries.add(String.valueOf(y));
            Lpesos.add(String.valueOf(x));
        }
        ArrayAdapter<String> dataAdapterPeso = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Lpesos);
        ArrayAdapter<String> dataAdapterSerie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Lseries);

        dataAdapterPeso.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dataAdapterSerie.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinnerPesos.setAdapter(dataAdapterPeso);
        spinnerSeries.setAdapter(dataAdapterSerie);
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
        infoRepeticion.cargarDatosDeRepeticion(id,idEjercicio,pesoF,seriesF,tiempoDescansoInicial,tiempoEjercicioInicial,Umedida);
        infoRutina.cargarDatosDeRutinaRepeticion(idRutina,id);

        Serie serie = new Serie(id,pesoF,seriesF,tiempoDescansoInicial,tiempoEjercicioInicial);
        series.add(serie);
    }
}

