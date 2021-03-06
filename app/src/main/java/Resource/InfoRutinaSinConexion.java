package Resource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thepintozo.myappgym.Extra;

import java.util.ArrayList;

import Model.Rutina;
import Model.RutinaRepeticion;

/**
 * Created by ricar on 27/07/2016.
 */
public class InfoRutinaSinConexion {
    public DataOffLine data;
    private Extra extra;
    public InfoRutinaSinConexion(Context context) {
        data = new DataOffLine(context);
        extra =  new Extra();
    }
    /**
     * Funcion encarga de registrar una repeticion
     */
    public void cargarDatosDeRutina(int id,
                                    String fecha ) {

        SQLiteDatabase bd = data.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DataOffLine.DatosTablaRutina.COLUMNA_id,id);
        valores.put(DataOffLine.DatosTablaRutina.COLUMNA_fecha,fecha);
        valores.put(DataOffLine.DatosTablaRutina.COLUMNA_estado,1);

        Long idGuardar = bd.insert(DataOffLine.DatosTablaRutina.NOMBRE_TABLA, DataOffLine.DatosTablaRutina.COLUMNA_id,valores);
        //Toast.makeText(getApplicationContext(),"Se guardo el datos : "+idGuardar, Toast.LENGTH_LONG);
        //si retorna un -1 es porque el registro ya existe
    }

    /**
     * Funcion encarga de registrar una RutinaRepeticion
     */
    public void cargarDatosDeRutinaRepeticion(int idRutina,
                                              int idRepeticion ) {

        SQLiteDatabase bd = data.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DataOffLine.DatosTablaRutinaRepeticion.COLUMNA_idRutina,idRutina);
        valores.put(DataOffLine.DatosTablaRutinaRepeticion.COLUMNA_idRepeticion,idRepeticion);

        Long idGuardar = bd.insert(DataOffLine.DatosTablaRutinaRepeticion.NOMBRE_TABLA, DataOffLine.DatosTablaRutinaRepeticion.COLUMNA_idRutina,valores);
        //Toast.makeText(getApplicationContext(),"Se guardo el datos : "+idGuardar, Toast.LENGTH_LONG);
        //si retorna un -1 es porque el registro ya existe
    }

    public Rutina buscarRutinaId(int id){
        Rutina r;
        SQLiteDatabase bd = data.getReadableDatabase();
        //la fecha de la rutina que busco
        String[] where ={Integer.toString(id)};

        String[] datosPedidos = {DataOffLine.DatosTablaRutina.COLUMNA_id,
                                 DataOffLine.DatosTablaRutina.COLUMNA_fecha,
                                 DataOffLine.DatosTablaRutina.COLUMNA_estado};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaRutina.NOMBRE_TABLA,
                    datosPedidos,
                    DataOffLine.DatosTablaRutina.COLUMNA_id+"=?",
                    where, null, null, null);
            while (c.moveToNext()){
                r = new Rutina(Integer.parseInt(c.getString(0)),c.getString(1),Integer.parseInt(c.getString(2)));
                return r;
            }
        }catch (Exception e){
            r = null;
            return r;
        }
        return null;
    }
    public Rutina buscarRutina(String fecha){
        Rutina r;
        SQLiteDatabase bd = data.getReadableDatabase();
        //la fecha de la rutina que busco
        String[] where ={fecha};

        String[] datosPedidos = {DataOffLine.DatosTablaRutina.COLUMNA_id,
                DataOffLine.DatosTablaRutina.COLUMNA_fecha,
                DataOffLine.DatosTablaRutina.COLUMNA_estado};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaRutina.NOMBRE_TABLA,
                    datosPedidos,
                    DataOffLine.DatosTablaRutina.COLUMNA_fecha+"=?",
                    where, null, null, null);
            while (c.moveToNext()){
                r = new Rutina(Integer.parseInt(c.getString(0)),c.getString(1),Integer.parseInt(c.getString(2)));
                return r;
            }
        }catch (Exception e){
            r = null;
            return r;
        }
        return null;
    }

    public RutinaRepeticion buscarRutinaRepeticion(int idRutina){

        RutinaRepeticion rr;
        SQLiteDatabase bd = data.getReadableDatabase();

        //el id del elemento que busco
        String[] where ={Integer.toString(idRutina)};

        String[] datosPedidos = {DataOffLine.DatosTablaRutinaRepeticion.COLUMNA_idRutina,
                                 DataOffLine.DatosTablaRutinaRepeticion.COLUMNA_idRepeticion};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaRutinaRepeticion.NOMBRE_TABLA,
                    datosPedidos,
                    DataOffLine.DatosTablaRutinaRepeticion.COLUMNA_idRutina+"=?",
                    where, null, null, null);
            while (c.moveToNext()){
                rr = new RutinaRepeticion(Integer.parseInt(c.getString(0)),Integer.parseInt(c.getString(1)));
                return rr;
            }
        }catch (Exception e){
            rr=null;
            return rr;
        }
        return null;
    }
    public ArrayList<RutinaRepeticion> allRutinaRepeticion(){
        ArrayList<RutinaRepeticion> rutinaRepeticioness = new ArrayList<>();
        SQLiteDatabase bd = data.getReadableDatabase();
        String[] datosPedidos = {DataOffLine.DatosTablaRutinaRepeticion.COLUMNA_idRutina,
                DataOffLine.DatosTablaRutinaRepeticion.COLUMNA_idRepeticion};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaRutinaRepeticion.NOMBRE_TABLA,
                    datosPedidos,
                    null,null, null, null, null);
            while (c.moveToNext()){
                RutinaRepeticion rr = new RutinaRepeticion(Integer.parseInt(c.getString(0)),Integer.parseInt(c.getString(1)));
                rutinaRepeticioness.add(rr);
            }
            return rutinaRepeticioness;
        }catch (Exception e){
            return  rutinaRepeticioness;
        }
    }
    public ArrayList<Rutina> allRitunas(){
        ArrayList<Rutina> rutinas = new ArrayList<>();

        SQLiteDatabase bd = data.getReadableDatabase();
        String[] datosPedidos = {DataOffLine.DatosTablaRutina.COLUMNA_id,
                DataOffLine.DatosTablaRutina.COLUMNA_fecha,
                DataOffLine.DatosTablaRutina.COLUMNA_estado};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaRutina.NOMBRE_TABLA,
                    datosPedidos,
                    null, null, null, null, null);
            while (c.moveToNext()){
                Rutina r = new Rutina(Integer.parseInt(c.getString(0)),c.getString(1),Integer.parseInt(c.getString(2)));
                rutinas.add(r);
            }
        }catch (Exception e){
            return rutinas;
        }
        return rutinas;
    }
    public Rutina buscarUltimaRutina(){
        ArrayList<Rutina> rutinas = new ArrayList<>();

        SQLiteDatabase bd = data.getReadableDatabase();
        String[] datosPedidos = {DataOffLine.DatosTablaRutina.COLUMNA_id,
                DataOffLine.DatosTablaRutina.COLUMNA_fecha,
                DataOffLine.DatosTablaRutina.COLUMNA_estado};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaRutina.NOMBRE_TABLA,
                    datosPedidos,
                    null, null, null, null, null);
            while (c.moveToNext()){
                Rutina r = new Rutina(Integer.parseInt(c.getString(0)),c.getString(1),Integer.parseInt(c.getString(2)));
                rutinas.add(r);
            }
            return rutinas.get(rutinas.size()-1);
        }catch (Exception e){
            return null;
        }
    }

    public int idProximaRutina(){
        ArrayList<Rutina> rutinas = this.allRitunas();
        String fechaa = extra.getFechaCompleta(1);
        Rutina ultimaRutina = null;

        if(rutinas.size()==0){
            this.cargarDatosDeRutina(0,fechaa);
            return 0;
        }
        else {
            int id=0;
            for (Rutina r: rutinas) {
                if (r.idRutina>=id){
                    id=r.idRutina;
                    ultimaRutina = r;
                }
            }

            if(ultimaRutina.fecha.equals(fechaa)){
                return id;
            }else {
                this.cargarDatosDeRutina(id+1,fechaa);
                return (id + 1);
            }
        }
    }
}
