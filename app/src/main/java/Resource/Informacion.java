package Resource;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;

import com.thepintozo.myappgym.Extra;

import java.util.ArrayList;

import Model.CapsulaEjercicio;
import Model.Ejercicio;
import Model.Musculo;
import Model.Rutina;

/**
 * Created by ricar on 18/07/2016.
 */
public class Informacion {

    public InfoMusculoSinConexion infoMusculo;
    public InfoEjerciciosSinConexion infoEjericio;
    public InfoRutinaSinConexion infoRutina;
    private DataOffLine data;
    private Extra extra;
    public Informacion(Context context) {
        infoMusculo = new InfoMusculoSinConexion(context);
        infoEjericio = new InfoEjerciciosSinConexion(context);
        infoRutina = new InfoRutinaSinConexion(context);
        data = new DataOffLine(context);
        extra = new Extra();
    }
    public  void cargarInfo(Context context){

        ArrayList<Musculo> musculos     = new ArrayList<>();
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();

        //cargo la info referente a los musculos y ejercicios contenidos en local
        musculos = cargarMusculos();
        ejercicios = cargarEjercicios();

        //verifico si la cantidad de musculo en la bd es la misma que en el listado de ultimos musculos
        if(musculos.size()!=infoMusculo.obtenerMusculos().size()){
            //recorro la lista de musculos
            for (Musculo m: musculos) {
                //si un musculo no se encuentra registrado en la bd se añade
                if(infoMusculo.buscarMusculoPorNombre(m.nombreMusculo)==null){
                    infoMusculo.cargarDatosDeMusculo(infoMusculo.proximoMusculo(), m.nombreMusculo);
                }
            }
        }
        //verifico si la cantidad de ejercicios en la bd es la misma que en el listado de ultimos ejercicios
        if(ejercicios.size()!=infoEjericio.obtenerEjercicios().size()){
            //recorro la lista de ejercicios
            for (Ejercicio e:ejercicios ) {
                //si un ejercicio no se encuentra en la base de datos se añade
                if(infoEjericio.buscarEjercicio(e.nombreEjercicio)==null){
                    infoEjericio.cargarDatosDeEjercicio(infoEjericio.proximoEjercicio(), e.idMusculo, e.nombreEjercicio, "Proximamente");
                }
            }
        }
    }
    /**
     * Infomación predefinida para uso offline
     * @return
     */
    public ArrayList<Musculo> cargarMusculos(){

        ArrayList<Musculo> musculos = new ArrayList<>();

        Musculo a = new Musculo("Bicep");
        musculos.add(a);
        Musculo b = new Musculo("Tricep");
        musculos.add(b);
        Musculo c = new Musculo("Abdomen");
        musculos.add(c);
        Musculo d = new Musculo("Piernas");
        musculos.add(d);
        Musculo e = new Musculo("Hombros");
        musculos.add(e);
        Musculo f = new Musculo("Espalda");
        musculos.add(f);
        Musculo g = new Musculo("Pectorales");
        musculos.add(g);

        return musculos;
    }
    /**
     * Infomación predefinida para uso offline
     */
    public ArrayList<Ejercicio> cargarEjercicios(){

        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        /*Bicep*/
        Ejercicio a1 = new Ejercicio(1,"Reverse wrist curl");
        ejercicios.add(a1);
        Ejercicio a2 = new Ejercicio(1,"Standing biceps curl");
        ejercicios.add(a2);
        Ejercicio a3 = new Ejercicio(1,"Zottman curl");
        ejercicios.add(a3);
        Ejercicio a4 = new Ejercicio(1,"Barbell preacher curl");
        ejercicios.add(a4);
        Ejercicio a5 = new Ejercicio(1,"Cable alternating bend");
        ejercicios.add(a5);
        Ejercicio a6 = new Ejercicio(1,"Dumbbell concentration curl");
        ejercicios.add(a6);
        Ejercicio a7 = new Ejercicio(1,"Cable curl");
        ejercicios.add(a7);
        Ejercicio a8 = new Ejercicio(1,"Dumbbell curl");
        ejercicios.add(a8);
        Ejercicio a9 = new Ejercicio(1,"Levantamiento de barra de pie");
        ejercicios.add(a9);

        /*Tricep*/
        Ejercicio b1 = new Ejercicio(2,"extension vertical alternada de los brazos");
        ejercicios.add(b1);
        Ejercicio b2 = new Ejercicio(2,"extension alternada de los antebrazos");
        ejercicios.add(b2);
        Ejercicio b3 = new Ejercicio(2,"extension de triceps en polea");
        ejercicios.add(b3);
        Ejercicio b4 = new Ejercicio(2,"Ejercicio tricep uno");
        ejercicios.add(b4);
        Ejercicio b5 = new Ejercicio(2,"Negative dip");
        ejercicios.add(b5);
        Ejercicio b6 = new Ejercicio(2,"Barbell military press");
        ejercicios.add(b6);
        Ejercicio b7 = new Ejercicio(2,"Ejercicio tricep dos");
        ejercicios.add(b7);
        Ejercicio b8 = new Ejercicio(2,"Ejercicio tricep tres");
        ejercicios.add(b8);
        Ejercicio b9 = new Ejercicio(2,"Ejercicio tricep cinco");
        ejercicios.add(b9);

        /*Abdomen*/
        Ejercicio c1 = new Ejercicio(3,"clam");
        ejercicios.add(c1);
        Ejercicio c2 = new Ejercicio(3,"dumbbell side bend");
        ejercicios.add(c2);
        Ejercicio c3 = new Ejercicio(3,"cable kneeling crunch");
        ejercicios.add(c3);

        /*Piernas*/
        Ejercicio d1 = new Ejercicio(4,"squats on the shoulders");
        ejercicios.add(d1);
        Ejercicio d2 = new Ejercicio(4,"walk on the elliptical");
        ejercicios.add(d2);
        Ejercicio d3 = new Ejercicio(4,"pacing with dumbbells");
        ejercicios.add(d3);

        /*Hombros*/
        Ejercicio e1 = new Ejercicio(5,"cable upright row");
        ejercicios.add(e1);
        Ejercicio e2 = new Ejercicio(5,"elevacion lateral de mancuernas");
        ejercicios.add(e2);
        Ejercicio e3 = new Ejercicio(5,"elevacion trasera de mancuerna");
        ejercicios.add(e3);

        /*Espalda*/
        Ejercicio f1 = new Ejercicio(6,"pull over con polea alta");
        ejercicios.add(f1);
        Ejercicio f2 = new Ejercicio(6,"remo horizontal con barra");
        ejercicios.add(f2);
        Ejercicio f3 = new Ejercicio(6,"polea trasnuca");
        ejercicios.add(f3);
        Ejercicio f4 = new Ejercicio(6,"Back raise");
        ejercicios.add(f4);
        Ejercicio f5 = new Ejercicio(6,"Ejercicio espalda Cuatro");
        ejercicios.add(f5);
        Ejercicio f6 = new Ejercicio(6,"Ejercicio espalda tres");
        ejercicios.add(f6);
        Ejercicio f7 = new Ejercicio(6,"Ejercicio espalda uno");
        ejercicios.add(f7);
        Ejercicio f8 = new Ejercicio(6,"One arm row");
        ejercicios.add(f8);
        Ejercicio f9 = new Ejercicio(6,"Stiff leg barbell deadlift");
        ejercicios.add(f9);
        Ejercicio f10 = new Ejercicio(6,"Wide overhand grip pull up");
        ejercicios.add(f10);

        /*Pectorales*/
        Ejercicio g1 = new Ejercicio(7,"Levantamiento de mancuerna en inclinacion");
        ejercicios.add(g1);
        Ejercicio g2 = new Ejercicio(7,"Press de banca con inclinacion");
        ejercicios.add(g2);
        Ejercicio g3 = new Ejercicio(7,"Apertura de mancuernas con inclinacion");
        ejercicios.add(g3);

        return ejercicios;
    }

    public ArrayList<String> ultimosEjercicios(Context context,String fecha){
        ArrayList<String> salida = new ArrayList<>();
        fecha = diaAnterior(fecha);
        //extra.Mensaje(context,fecha);
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor fila = db.rawQuery("select distinct (m."+DataOffLine.DatosTablaMusculo.COLUMNA_nombre+") "+
                                  "from "+DataOffLine.DatosTablaRutina.NOMBRE_TABLA+" r "+
                                  "inner join "+DataOffLine.DatosTablaRutinaRepeticion.NOMBRE_TABLA+" rp on rp.idRutina = r.idRutina " +
                                  "inner join "+DataOffLine.DatosTablaRepeticion.NOMBRE_TABLA+" re on re.idRepeticion = rp.idRepeticion " +
                                  "inner join "+DataOffLine.DatosTablaEjercicio.NOMBRE_TABLA+" e on e.idEjercicio = re.idEjercicio " +
                                  "inner join "+DataOffLine.DatosTablaMusculo.NOMBRE_TABLA+" m on e.idMusculo = m.idMusculo " +
                                  "where r."+DataOffLine.DatosTablaRutina.COLUMNA_fecha+" = '"+fecha+"'", null);
        //------------------------------------------------------------------------------------------
        if(fila.moveToFirst()){
            do {
                salida.add(fila.getString(0));
            }while (fila.moveToNext());
        }
        //------------------------------------------------------------------------------------------
        db.close();
        return salida;
    }

    private String diaAnterior(String fecha) {
        Rutina r = infoRutina.buscarRutina(fecha);
        if(r!=null){
            Rutina rr = infoRutina.buscarRutinaId(r.idRutina-1);
            if(rr!=null){
                return rr.fecha;
            }
        }
        else{
            Rutina rrr = infoRutina.buscarUltimaRutina();
            if(rrr!=null){
                return  rrr.fecha;
            }
            else {
                return fecha;
            }
        }
        return fecha;
    }

    public ArrayList<CapsulaEjercicio> EjerciciosRutinaActual(Context context,String fecha){
        ArrayList<CapsulaEjercicio> resumen = new ArrayList<>();
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor fila = db.rawQuery("select m.nombreMusculo as 'Musculo', count(*) as 'Ejercicios realizados' " +
                "from rutina r " +
                "inner join RutinaRepeticion rp on rp.idRutina = r.idRutina " +
                "inner join Repeticion re on re.idRepeticion = rp.idRepeticion " +
                "inner join Ejercicio e on e.idEjercicio = re.idEjercicio " +
                "inner join Musculo m on e.idMusculo = m.idMusculo " +
                "where r.fecha ='"+fecha+"' " +
                "group by m.nombreMusculo", null);
        //------------------------------------------------------------------------------------------
        if(fila.moveToFirst()){
            do {
                CapsulaEjercicio c = new CapsulaEjercicio(fila.getString(0),Integer.parseInt(fila.getString(1)));
                resumen.add(c);
            }while (fila.moveToNext());
        }
        //------------------------------------------------------------------------------------------
        db.close();
        return resumen;
    }
}
