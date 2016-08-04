package Resource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Model.Rutina;
import Model.RutinaRepeticion;

/**
 * Created by ricar on 27/07/2016.
 */
public class InfoRutinaSinConexion {
    public DataOffLine data;

    public InfoRutinaSinConexion(Context context) {
        data = new DataOffLine(context);
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

    public Rutina buscarRutina(int idRutina){
        Rutina r;
        SQLiteDatabase bd = data.getReadableDatabase();
        String[] datosPedidos = {DataOffLine.DatosTablaRutina.COLUMNA_id,
                                 DataOffLine.DatosTablaRutina.COLUMNA_fecha,
                                 DataOffLine.DatosTablaRutina.COLUMNA_estado};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaRutina.NOMBRE_TABLA,
                    datosPedidos,
                    null,null, null, null, null);
            while (c.moveToNext()){
                r = new Rutina(Integer.parseInt(c.getString(0)),c.getString(1),Integer.parseInt(c.getString(2)));
            }
        }catch (Exception e){
            r = null;
        }
        return new Rutina(0,"",1);
    }

    public RutinaRepeticion buscarRutinaRepeticion(int idRutina, int idRepeticion){
        RutinaRepeticion rr;
        SQLiteDatabase bd = data.getReadableDatabase();
        String[] datosPedidos = {DataOffLine.DatosTablaRutinaRepeticion.COLUMNA_idRutina,
                                 DataOffLine.DatosTablaRutinaRepeticion.COLUMNA_idRepeticion};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaRutinaRepeticion.NOMBRE_TABLA,
                    datosPedidos,
                    null,null, null, null, null);
            while (c.moveToNext()){

            }
        }catch (Exception e){

        }
        return new RutinaRepeticion(0,0);
    }
}
