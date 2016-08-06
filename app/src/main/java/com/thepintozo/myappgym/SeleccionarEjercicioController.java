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
    private ListView listaSeleccionarEjercicio;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
            idMusculo =  Integer.parseInt(o);
            //Toast toast = Toast.makeText(this, o, Toast.LENGTH_LONG);
            //toast.show();
        }
        else{
            //Toast toast = Toast.makeText(this, "no aporta nada", Toast.LENGTH_LONG);
            //toast.show();
            idMusculo = 1;
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SeleccionarEjercicioController Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.thepintozo.myappgym/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SeleccionarEjercicioController Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.thepintozo.myappgym/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
