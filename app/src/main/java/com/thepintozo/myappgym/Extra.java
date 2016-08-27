package com.thepintozo.myappgym;

import android.content.Context;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by ricar on 27/07/2016.
 */
public class Extra {

    private static Date date = new Date();

    public Extra() {
    }

    /**
     * Funcion que recibe las iniciales de un dia de la semana en ingles, y
     * este retorna el día completo en espaniol
     * @param dia [String] : iniciales de un dia en ingles
     * @return [String] : dia de la semana en españl
     */
    public static String getDiaEnEspaniol(String dia){
        switch (dia){
            case "Mon":
                return "Lunes";
            case "Tue":
                return "Martes";
            case "Wed":
                return "Miércoles";
            case "THU":
                return "Jueves";
            case "Fri":
                return "Viernes";
            case "Sat":
                return "Sábado";
            case "Sun":
                return "Domingo";
        }
        return "";
    }

    /**
     * Funcion que recibe las iniciales de un mes en ingles y este retorna el nombre del mes completo
     * en espaniol
     * @param mes [String] : iniciales de un mes en espaniol
     * @return [String] : nombre completo del mes en espaniol
     */
    public static String getMesEnEspaniol(String mes){
        switch (mes){
            case "Jan":
                return "Enero";
            case "Feb":
                return "Febrero";
            case "Mar":
                return "Marzo";
            case "Apr":
                return "Abril";
            case "May":
                return "Mayo";
            case "Jun":
                return "Junio";
            case "Jul":
                return "Julio";
            case "Aug":
                return "Agosto";
            case "Sep":
                return "Septiembre";
            case "Oct":
                return "Octubre";
            case "Nov":
                return "Noviembre";
            case "Dec":
                return "Diciemnre";
        }
        return "";
    }

    /**
     * Funcion encargada de obtener el dia del mes del sistema
     * @param formato [int] : 0 para obtener el dia en forma numerica, 1 en forma de texto
     * @return [String] : nombre o numero dependiendo del formato requerido
     */
    public static String getDia(int formato){
        DateFormat txtdia = new SimpleDateFormat("EEE");
        DateFormat intdia = new SimpleDateFormat("dd");
        return (formato == 0) ? intdia.format(date) : getDiaEnEspaniol(txtdia.format(date));

    }

    /**
     * Funcion encargada de obtener el mes del año del sistema
     * @param formato [int] : 0 para obtener el mes en forma numerica, 1 en forma de texto
     * @return
     */
    public static String getMes(int formato){
        DateFormat txtmes = new SimpleDateFormat("MMM");
        DateFormat intmes = new SimpleDateFormat("MM");
        return (formato == 0) ? intmes.format(date) : getMesEnEspaniol(txtmes.format(date));
    }

    /**
     * Funcion encargada de obtener el añodel sistema
     * @return
     */
    public static String getAnio(){
        DateFormat intanio = new SimpleDateFormat("yyyy");
        return intanio.format(date);
    }

    /**
     * Funcion que retorna la fecha en string con diferente formato
     * @param formato [int] : 1 ó 2 u 3
     * @return
     */
    public static String getFechaCompleta(int formato){
        switch (formato){
            case 1:
                return getDia(0)+"/"+getMes(0)+"/"+getAnio();//dd/mm/aaaa
            case 2:
                return " de "+getMes(1)+" de "+getAnio();// de mmmm de aaaa
            case 3:
                return " de "+getMes(1);
        }
        return "";
    }

    /**
     * Funcion que me ayuda a desplegar un mensaje por pantalla
     * @param context : [Context] activity donde se quiere mostrar
     * @param mensaje :[string] mensaje que se quiere desplegar
     */
    public static void Mensaje(Context context,String mensaje){
        Toast toast1 = Toast.makeText(context,mensaje, Toast.LENGTH_SHORT);
        toast1.show();
    }

    /**
     * Funcion encargada de recibir el string de un objeto cronometro y este devuelve el tiempo
     * transcurrido en segundos en formato numerico
     * @param tiempo : [string]
     * @return seg : [int]
     */
    public static int enSegundos(String tiempo){
        StringTokenizer st = new StringTokenizer(tiempo,":",true);
        //System.out.println("Hay un total de: "+st.countTokens()+" tokens.");
        int contador=0;
        int seg=0;
        String pivote="";
        while (st.hasMoreTokens()) {
            pivote =st.nextToken();
            if(!pivote.equals(":")) {
                int m = Integer.parseInt(pivote);
                if (contador == 0) {
                    if (m > 0) {
                        seg = seg + (m * 60);
                    }
                    contador++;
                } else {
                    seg = seg + m;
                }
            }
        }
        return seg;
    }
}
