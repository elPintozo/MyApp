package com.thepintozo.myappgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Resource.DataOffLine;
import Resource.Informacion;

public class MainActivity extends AppCompatActivity {

    public Informacion data;
    private Button btnSalir;
    private Button btnComenzar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new Informacion(getApplicationContext());
        data.cargarInfo();

        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista

        btnSalir    = (Button)findViewById(R.id.btnSalir);
        btnComenzar = (Button)findViewById(R.id.btnComenzar);

        /******************************************************************************************/
        //asignacion Onclick a los botones
        /***************************
         BOTON SALIR
         ***************************/
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        /***************************
         BOTON COMENZAR
         ***************************/
        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ultimosEjerciciosController.class);
                startActivity(i);
            }
        });
    }
}
