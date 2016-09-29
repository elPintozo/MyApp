package com.thepintozo.myappgym;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Resource.DataOffLine;
import Resource.Informacion;

public class MainActivity extends AppCompatActivity {

    public Informacion data;
    private Button btnComenzar;
    private TextView titulo;
    private int contador=0;

    private ImageView imagenPerfil;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new Informacion(getApplicationContext());
        data.cargarInfo(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        this.setSupportActionBar(toolbar);
        /******************************************************************************************/
        //Inicializo la imagen de perfil
        imagenPerfil = (ImageView) findViewById(R.id.profileImage);
        imagenPerfil.setImageResource(R.mipmap.male_profile);
        /******************************************************************************************/
        //Inicializo el titulo de la vista
        titulo = (TextView)findViewById(R.id.lblProfileName);
        titulo.setText("Ricardo");
        /******************************************************************************************/
        //Inicializo los botones correspondientes a la vista

        btnComenzar = (Button)findViewById(R.id.btnComenzar);

        /******************************************************************************************/
        //asignacion Onclick a los botones
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
        /***************************
         TEXTVIEW COMENZAR
         ***************************/
         titulo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 contador++;
                 if(contador==5){
                     contador=0;
                     Intent i = new Intent(MainActivity.this, testController.class);
                     startActivity(i);
                 }
             }
         });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_toolbar_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.salir:
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
