package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Model.CapsulaEjercicio;
import Resource.Informacion;


public class MiRutinaController extends AppCompatActivity {

    private Button btnTerminePorHoy;
    private Button btnNuevoEjercicio;
    private int idRutina;
    private Extra extra;
    private ListView listaEjerciciosRealizados;
    private Informacion info;
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
        /******************************************************************************************/
        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnNuevoEjercicio = (Button)findViewById(R.id.btnAnadirEjercicio);
        btnTerminePorHoy  = (Button)findViewById(R.id.btnNoMasPorHoy);

        /******************************************************************************************/
        //Inicializo lista correspondientes a la vista
        listaEjerciciosRealizados = (ListView)findViewById(R.id.listEjerciciosMiRutinaActual);
        cargarList();

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

    /**
     * Funcion encargada de listar los musculos ejercitados  durante la rutina que se esta llevando
     * a cabo en conjunto con la cantidad de repeticiones que han ido haciendo independiente del
     * ejercicio que haya hecho
     */
    private void cargarList() {
        ArrayAdapter<String> adaptador;
        ArrayList<String> Ejercicios =  new ArrayList<>();
        Ejercicios.add("No has realizado ningun ejericio aún");
        String fecha = extra.getFechaCompleta(1);
        //pido a la base de datos info de los musculo ejercitados junto a la cantidad de repeticiones
        //que han llevado a cabo con diferentes ejericios
        ArrayList<CapsulaEjercicio> ultimos = info.EjerciciosRutinaActual(getApplicationContext(),fecha);

        //se listan los musculo que se han ejercitados en conjunto con la cantidad de repeticiones que han
        //realizado en cada uno de ellos
        if(ultimos.size()!=0){
            ArrayList<String> salida = new ArrayList<>();
            for (CapsulaEjercicio c:ultimos) {
                salida.add(c.nombreEjercicio+" -> Ejercicios realizados: "+c.numeroRepeticiones);
            }
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, salida);
        }
        else {
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Ejercicios);
        }
        listaEjerciciosRealizados.setAdapter(adaptador);
    }
}
