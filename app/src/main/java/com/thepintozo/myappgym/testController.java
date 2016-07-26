package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Model.Ejercicio;
import Model.Musculo;
import Resource.InfoEjerciciosSinConexion;
import Resource.InfoMusculoSinConexion;
import Resource.Informacion;

public class testController extends AppCompatActivity {

    private Button btnVolver;
    private TextView testMusculo;
    private TextView testEjercicio;
    private Informacion info ;
    public InfoMusculoSinConexion infoMusculo;
    public InfoEjerciciosSinConexion infoEjericio;

    public testController() {
        infoMusculo = new InfoMusculoSinConexion(this);
        infoEjericio = new InfoEjerciciosSinConexion(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_controller);

        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnVolver =(Button)findViewById(R.id.btnVolverTest);

        /******************************************************************************************/
        //Inicializo el titulo de la vista
        testMusculo = (TextView)findViewById(R.id.txtTestMusculo);
        testEjercicio = (TextView)findViewById(R.id.txtTestEjercicio);

        /******************************************************************************************/
        //asignacion Onclick a los botones
        /***************************
         BOTON VOLVER
         ***************************/
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(testController.this, MainActivity.class);
                startActivity(i);
            }
        });
        /******************************************************************************************/
        //Info de la Base de datos
        /******************************************************************************************/
        // MUSCULOS
        /******************************************************************************************/
        String txtMusculo    ="Resultados de la tabla Musculo: \n";
        //lista de ejercicios presentes
        ArrayList<Musculo> musculos = infoMusculo.obtenerMusculos();
        if(musculos!=null){
            txtMusculo = txtMusculo+" [Hay "+musculos.size()+" en la bd]\n";
            for (Musculo m : musculos) {
                txtMusculo = txtMusculo+"    [id: "+m.idMusculo+"] "+m.nombreMusculo+"\n";
            }
        }else{
            txtMusculo = txtMusculo+"- No hay registros -";
        }
        /******************************************************************************************/
        // EJERCICIOS
        /******************************************************************************************/
        String txtMEjercicio ="Resultados de la tabla Ejericios: \n";
        /**/
        ArrayList<Ejercicio> ejercicios = infoEjericio.obtenerEjercicios();

        if(ejercicios!=null){
            txtMEjercicio = txtMEjercicio+" [Hay "+ejercicios.size()+" en la bd]\n";
            for (Ejercicio e : ejercicios) {
                txtMEjercicio = txtMEjercicio+"    [id: "+e.idEjercicio+"("+e.idMusculo+")] "+e.nombreEjercicio+"\n";
            }
        }
        else{
            txtMEjercicio = txtMEjercicio +"- No hay registros -";
        }

        testMusculo.setText(txtMusculo);
        testEjercicio.setText(txtMEjercicio);
    }
}
