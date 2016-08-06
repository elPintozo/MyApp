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
    private int Umedida=1;
    private int tiempoEjercicioInicial;
    private int tiempoDescansoInicial;
    private int tiempoEjercicioFinal;
    private int tiempoDescansoFinal;
    private int repeticionesInicial;
    private int repeticionesFinal;
    private ArrayList<Repeticion> repeticiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comenzar_ejercicio_controller);
        repeticiones = new ArrayList<>();
        /******************************************************************************************/
        Intent i = getIntent();
        Bundle recibir = i.getExtras();

        if(recibir!=null){
            idMusculo = recibir.getInt("idMusculo");
            idEjercicio = recibir.getInt("idEjercicio");
            //Toast toast = Toast.makeText(this, o, Toast.LENGTH_LONG);
            //toast.show();
        }
        else{
            //Toast toast = Toast.makeText(this, "no aporta nada", Toast.LENGTH_LONG);
            //toast.show();
            idMusculo = 0;
            idEjercicio = 0;
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
        cargarList();

        /******************************************************************************************/

        //asignacion Onclick a los botones
        /***************************
         BOTON DESCANZO
         ***************************/
        btnDescanzarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiempoDescanso.setBase(SystemClock.elapsedRealtime());
                tiempoDescanso.start();
                tiempoEjericio.stop();
                tiempoEjercicioInicial = ayuda.enSegundos(String.valueOf(tiempoEjericio.getText()));
                tiempoEjercicioFinal = tiempoDescansoFinal + tiempoEjercicioInicial;

                tiempoEjericio.setBase(SystemClock.elapsedRealtime());
            }
        });
        /***************************
         BOTON CONTINUAR
         ***************************/
        btnComenzarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiempoDescansoInicial= ayuda.enSegundos(String.valueOf(tiempoDescanso.getText()));
                tiempoDescansoFinal =  tiempoEjercicioFinal+tiempoDescansoFinal;
                repeticionesInicial = Integer.parseInt(txtseries.getText().toString());
                repeticionesFinal = repeticionesFinal + repeticionesInicial;
                tiempoEjericio.setBase(SystemClock.elapsedRealtime());
                tiempoEjericio.start();
                tiempoDescanso.stop();
                tiempoDescanso.setBase(SystemClock.elapsedRealtime());
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
                tiempoDescansoInicial= ayuda.enSegundos(String.valueOf(tiempoDescanso.getText()));
                tiempoDescansoFinal =  tiempoEjercicioFinal+tiempoDescansoFinal;
                //finish();
                //Intent i = new Intent(ComenzarEjercicioController.this, MiRutinaController.class);
                ayuda.Mensaje(getApplicationContext(),"IdEjercicio : "+idEjercicio+"\n"+
                                                      "Peso        : "+txtpeso.getText()+"\n"+
                                                      "Repeticiones: "+repeticionesFinal+"\n"+
                                                      "T Descanso  : "+tiempoDescansoInicial+"\n"+
                                                      "T Ejercicio : "+tiempoEjercicioInicial+"\n"+
                                                      "U. Medida   : "+Umedida+"\n");
                //startActivity(i);
            }
        });
    }
    private void cargarList() {
        String[] Ejercicios = {"Ejercicio 1", "Ejercicio 2", "Ejercicio 3"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Ejercicios);
        listRepeticiones.setAdapter(adaptador);
    }
}

