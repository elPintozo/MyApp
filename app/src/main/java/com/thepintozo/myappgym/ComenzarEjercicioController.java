package com.thepintozo.myappgym;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    private Button btnComenzarEjercicio;
    private Button btnUnidad;

    private Chronometer tiempoDescanso;
    private Chronometer tiempoEjericio;

    private Extra ayuda;
    private ListView listRepeticiones;
    private int idMusculo;
    private int idEjercicio;
    private int idRutina;
    private int Umedida=0;
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

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comenzar_ejercicio_controller);
        series = new ArrayList<>();
        infoRepeticion = new InfoRepeticionSinConexion(this);
        infoRutina =  new InfoRutinaSinConexion(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar_comenzar_ejercicio);
        this.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Para saber si se debe o no realizar un registro basta con saber si cuando termino,
                se encontraba activo el tiempo de descanso, no cuenta el tiempo de ejercicio porque
                contaria como ejercicio incompleto al no haber realizado el descanso correspondiente*/
                tiempoDescansoInicial = ayuda.enSegundos(String.valueOf(tiempoDescanso.getText()));
                if(tiempoDescansoInicial!=0){
                    /*lleva a cabo el registro en la base de datos*/
                    actualizarSeries();
                }
                finish();
                Intent i = new Intent(ComenzarEjercicioController.this, MiRutinaController.class);
                i.putExtra("idRutina",idRutina);
                startActivity(i);
            }
        });
        /******************************************************************************************/
        /*Recibo las variables de la vista anterior*/
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
        btnComenzarEjercicio = (Button)findViewById(R.id.btnContinuarConEjercicio);
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
         BOTON CONTINUAR
         ***************************/
        btnComenzarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnComenzarEjercicio.getText().equals("Comenzar")){
                    btnComenzarEjercicio.setText(R.string.btnBreakExercise);//cambio el texto del boton
                    btnComenzarEjercicio.setBackgroundResource(R.color.colorPrimary);
                    /*Para saber si se ha realizado o no una repeticion, basta con analizar el valor
                    que posee la variable tiempo de descanso, ya que, de haber registrado segundo, da
                    pie para llevar a cabo el registro*/
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
                else{
                    btnComenzarEjercicio.setText(R.string.btnStartExercise);//cambio el texto del boton
                    btnComenzarEjercicio.setBackgroundResource(R.color.colorSecondaryText);
                    tiempoEjercicioInicial = ayuda.enSegundos(String.valueOf(tiempoEjericio.getText()));
                    if(tiempoEjercicioInicial!=0){
                        tiempoDescanso.setBase(SystemClock.elapsedRealtime());
                        tiempoDescanso.start();
                        tiempoEjericio.stop();
                        tiempoEjericio.setBase(SystemClock.elapsedRealtime());
                        ayuda.Mensaje(getApplicationContext(),"Tómate un respiro!");
                    }
                }
                //tiempoDescansoInicial= ayuda.enSegundos(String.valueOf(tiempoDescanso.getText()));
                /*Para saber si se ha realizado o no una repeticion, basta con analizar el valor
                que posee la variable tiempo de descanso, ya que, de haber registrado segundo, da
                pie para llevar a cabo el registro*/
                /*if(tiempoDescansoInicial!=0 ){
                    actualizarSeries();
                }
                tiempoEjericio.setBase(SystemClock.elapsedRealtime());
                tiempoEjericio.start();
                tiempoDescanso.stop();
                tiempoDescanso.setBase(SystemClock.elapsedRealtime());
                ayuda.Mensaje(getApplicationContext(),"Adelante!");*/
            }
        });
        /***************************
         BOTON UNIDAD DE MEDIDA
         ***************************/
        btnUnidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*aqui por defecto trae por default kilo*/
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

    /**
     * Funcion que carga los snipper correspondiente a la seleccion del peso a usar en el ejercicio
     * más la cantidad de repeticiones que hara por serie
     */
    private void cargarSpinners() {

        Lpesos = new ArrayList<String>();
        Lseries = new ArrayList<String>();

        for(int x =1; x<100 ; x++){
            Lpesos.add(String.valueOf(x));
        }
        for(int y=1 ; y<51 ; y++){
            Lseries.add(String.valueOf(y));
        }
        ArrayAdapter<String> dataAdapterPeso = new ArrayAdapter<String>(this, R.layout.spinner_item, Lpesos);
        ArrayAdapter<String> dataAdapterSerie = new ArrayAdapter<String>(this, R.layout.spinner_item, Lseries);

        dataAdapterPeso.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dataAdapterSerie.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinnerPesos.setAdapter(dataAdapterPeso);
        spinnerSeries.setAdapter(dataAdapterSerie);
    }

    /**
     * Funcion que se encarga de actualizar el listado de las series que lleva del ejercicio que selecciono
     */
    private void actualizarSeries() {
        /*se llama a la funcion para hacer el registro en la base de datos*/
        nuevaRepeticion();
        ArrayList<String> seriess = new ArrayList<>();

        /*Realiza el registro para mostrar en la vista lo que lleva hasta el momento*/
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
    /**
     * Funcion encargada de realizar el registro de la serie que ha realizando una vez presionado el
     * boton descanso luego de un ejercicio
     */
    public void nuevaRepeticion(){
        //solicitu un id
        int id = infoRepeticion.proximaRepeticion();
        //cargo la serie
        infoRepeticion.cargarDatosDeRepeticion(id,idEjercicio,pesoF,seriesF,tiempoDescansoInicial,tiempoEjercicioInicial,Umedida);
        //asocio la serie a la rutina que esta llevando en ese momento
        infoRutina.cargarDatosDeRutinaRepeticion(idRutina,id);

        //añado la serie al array que aparece en la vista
        Serie serie = new Serie(id,pesoF,seriesF,tiempoDescansoInicial,tiempoEjercicioInicial);
        series.add(serie);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_toolbar_comenzar_ejercicios,menu);
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

