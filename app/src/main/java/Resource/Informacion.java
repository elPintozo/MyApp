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

        if (infoMusculo.obtenerMusculos().size()==0) {
            musculos = cargarMusculos();
        }

        /*musculos*/
        if(musculos.size()!=0) {
            for (int x = 0; x < musculos.size(); x++) {
                Musculo m = musculos.get(x);
                infoMusculo.cargarDatosDeMusculo(m.idMusculo, m.nombreMusculo);
            }
        }

        if (infoEjericio.obtenerEjercicios().size()==0) {
            InfoEjerciciosSinConexion f = new InfoEjerciciosSinConexion(context);
            /*f.borrarEjercicio(1);
            f.borrarEjercicio(4);
            f.borrarEjercicio(7);
            f.borrarEjercicio(10);
            f.borrarEjercicio(13);
            f.borrarEjercicio(19);*/
            ejercicios = cargarEjercicios();
        }

        //ejercicios
        if(ejercicios.size()!=0) {
            for (int x = 0; x < ejercicios.size(); x++) {
                Ejercicio e = ejercicios.get(x);
                infoEjericio.cargarDatosDeEjercicio(e.idEjercicio, e.idMusculo, e.nombreEjercicio, e.detalleEjercicio);
            }
        }
    }
    /**
     * Infomación predefinida para uso offline
     * @return
     */
    public ArrayList<Musculo> cargarMusculos(){

        ArrayList<Musculo> musculos = new ArrayList<>();

        Musculo a = new Musculo(1,"Bicep",1);
        musculos.add(a);
        Musculo b = new Musculo(2,"Tricep",1);
        musculos.add(b);
        Musculo c = new Musculo(3,"Abdomen",1);
        musculos.add(c);
        Musculo d = new Musculo(4,"Piernas",1);
        musculos.add(d);
        Musculo e = new Musculo(5,"Hombros",1);
        musculos.add(e);
        Musculo f = new Musculo(6,"Espalda",1);
        musculos.add(f);
        Musculo g = new Musculo(7,"Pectorales",1);
        musculos.add(g);

        return musculos;
    }
    /**
     * Infomación predefinida para uso offline
     * @return
     */
    public ArrayList<Ejercicio> cargarEjercicios(){

        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        /*Bicep*/
        Ejercicio a1 = new Ejercicio(1,1,"reverse wrist curl","detalle",1);
        ejercicios.add(a1);
        Ejercicio a2 = new Ejercicio(2,1,"standing biceps curl","detalle",1);
        ejercicios.add(a2);
        Ejercicio a3 = new Ejercicio(3,1,"zottman curl","detalle",1);
        ejercicios.add(a3);

        /*Tricep*/
        Ejercicio b1 = new Ejercicio(4,2,"extension vertical alternada de los brazos","detalle",1);
        ejercicios.add(b1);
        Ejercicio b2 = new Ejercicio(5,2,"extension alternada de los antebrazos","detalle",1);
        ejercicios.add(b2);
        Ejercicio b3 = new Ejercicio(6,2,"extension de tricep en polea","detalle",1);
        ejercicios.add(b3);

        /*Abdomen*/
        Ejercicio c1 = new Ejercicio(7,3,"clam","detalle",1);
        ejercicios.add(c1);
        Ejercicio c2 = new Ejercicio(8,3,"bumbbell slide bend","detalle",1);
        ejercicios.add(c2);
        Ejercicio c3 = new Ejercicio(9,3,"cable kneeling crunch","detalle",1);
        ejercicios.add(c3);

        /*Piernas*/
        Ejercicio d1 = new Ejercicio(10,4,"squats on the shoulders","detalle",1);
        ejercicios.add(d1);
        Ejercicio d2 = new Ejercicio(11,4,"walk on the elliptical","detalle",1);
        ejercicios.add(d2);
        Ejercicio d3 = new Ejercicio(12,4,"pacing with dumbbells","detalle",1);
        ejercicios.add(d3);

        /*Hombros*/
        Ejercicio e1 = new Ejercicio(13,5,"cable upright row","detalle",1);
        ejercicios.add(e1);
        Ejercicio e2 = new Ejercicio(14,5,"elevacion lateral de mancuernas","detalle",1);
        ejercicios.add(e2);
        Ejercicio e3 = new Ejercicio(15,5,"elevacion trasera de mancuerna","detalle",1);
        ejercicios.add(e3);

        /*Espalda*/
        Ejercicio f1 = new Ejercicio(16,6,"pull over con polea alta","detalle",1);
        ejercicios.add(f1);
        Ejercicio f2 = new Ejercicio(17,6,"remo horizontal con barra","detalle",1);
        ejercicios.add(f2);
        Ejercicio f3 = new Ejercicio(18,6,"polea trasnuca","detalle",1);
        ejercicios.add(f3);

        /*Pectorales*/
        Ejercicio g1 = new Ejercicio(19,7,"levantamiento de mancuerna en inclinacion","detalle",1);
        ejercicios.add(g1);
        Ejercicio g2 = new Ejercicio(20,7,"press de banca con inclinacion","detalle",1);
        ejercicios.add(g2);
        Ejercicio g3 = new Ejercicio(21,7,"apertura de mancuernas con inclinacion","detalle",1);
        ejercicios.add(g3);

        return ejercicios;
    }

    public ArrayList<String> ultimosEjercicios(Context context,String fecha){
        ArrayList<String> salida = new ArrayList<>();
        fecha = diaAnterior(fecha);
        extra.Mensaje(context,fecha);
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
