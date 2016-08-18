package com.thepintozo.myappgym;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Model.Ejercicio;
import Model.Musculo;
import Model.Repeticion;
import Model.Rutina;
import Model.RutinaRepeticion;
import Resource.DataOffLine;
import Resource.InfoEjerciciosSinConexion;
import Resource.InfoMusculoSinConexion;
import Resource.InfoRepeticionSinConexion;
import Resource.InfoRutinaSinConexion;
import Resource.Informacion;

public class testController extends AppCompatActivity {

    private Button btnVolver;
    private Button btnLimpiarBD;
    private Button btnResetearBD;
    private TextView testMusculo;
    private TextView testEjercicio;
    private TextView testRutina;
    private TextView testRepeticion;
    private TextView testRutinaRepeticion;
    private DataOffLine data;
    private Informacion info ;
    public InfoMusculoSinConexion infoMusculo;
    public InfoEjerciciosSinConexion infoEjericio;
    public InfoRutinaSinConexion infoRutina;
    public InfoRepeticionSinConexion infoRepeticion;

    public testController() {
        infoMusculo = new InfoMusculoSinConexion(this);
        infoEjericio = new InfoEjerciciosSinConexion(this);
        infoRutina = new InfoRutinaSinConexion(this);
        infoRepeticion = new InfoRepeticionSinConexion(this);
        data = new DataOffLine(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_controller);

        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista
        btnVolver =(Button)findViewById(R.id.btnVolverTest);
        btnLimpiarBD=(Button)findViewById(R.id.btnClearDBTest);
        btnResetearBD=(Button)findViewById(R.id.btnDeleteDBTest);

        /******************************************************************************************/
        //Inicializo el titulo de la vista
        testMusculo = (TextView)findViewById(R.id.txtTestMusculo);
        testEjercicio = (TextView)findViewById(R.id.txtTestEjercicio);
        testRutina = (TextView)findViewById(R.id.txtTestRutina);
        testRepeticion  = (TextView)findViewById(R.id.txtTestRepeticion);
        testRutinaRepeticion  = (TextView)findViewById(R.id.txtTestRutinaRepeticiones);
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
        /***************************
         BOTON LIMPIAR
         ***************************/
        btnLimpiarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = data.getWritableDatabase();
            }
        });
        /***************************
         BOTON RESETEAR
         ***************************/
        btnResetearBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = data.getWritableDatabase();
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
        /******************************************************************************************/
        // RUTINAS
        /******************************************************************************************/
        String txtRutina ="Resultados de la tabla Rutina: \n";
        ArrayList<Rutina> rutinas = infoRutina.allRitunas();

        if (rutinas.size()!=0){
            txtRutina = txtRutina+" [Hay "+rutinas.size()+" en la bd]\n";
            for (Rutina r: rutinas) {
                txtRutina = txtRutina+"    [id: "+r.idRutina+"] "+r.fecha+"\n";
            }
        }
        else {
            txtRutina = txtRutina +"- No hay registros -";
        }
        /******************************************************************************************/
        // REPETICIONES
        /******************************************************************************************/
        String txtRepeticion ="Resultados de la tabla Repeticion: \n";
        ArrayList<Repeticion> repeticiones = infoRepeticion.allRepeticion();

        if (repeticiones.size()!=0){
            txtRepeticion = txtRepeticion+" [Hay "+repeticiones.size()+" en la bd]\n";

            for (Repeticion r: repeticiones) {
                txtRepeticion = txtRepeticion+"    [id: "+r.idRepeticion+"]("+r.idEjercicio+")"+r.peso+"|"+r.repeticiones+"|"+r.tiempoDescanso+"|"+r.tiempoEjercicio+"\n";
            }
        }
        else {
            txtRepeticion = txtRepeticion +"- No hay registros -";
        }
        /******************************************************************************************/
        // RUTINAREPETICIONES
        /******************************************************************************************/
        String txtRutinaRepeticion ="Resultados de la tabla RutinaRepeticion: \n";
        ArrayList<RutinaRepeticion> RutinaRepeticiones = infoRutina.allRutinaRepeticion();

        if (RutinaRepeticiones.size()!=0){
            txtRutinaRepeticion = txtRutinaRepeticion+" [Hay "+RutinaRepeticiones.size()+" en la bd]\n";

            for (RutinaRepeticion rr: RutinaRepeticiones) {
                txtRutinaRepeticion = txtRutinaRepeticion+"    [id rutina: "+rr.idRutina+"][id repeticion:"+rr.idRepeticion+"]\n";
            }
        }
        else {
            txtRutinaRepeticion = txtRutinaRepeticion +"- No hay registros -";
        }


        testMusculo.setText(txtMusculo);
        testEjercicio.setText(txtMEjercicio);
        testRutina.setText(txtRutina);
        testRepeticion.setText(txtRepeticion);
        testRutinaRepeticion.setText(txtRutinaRepeticion);
    }
}
