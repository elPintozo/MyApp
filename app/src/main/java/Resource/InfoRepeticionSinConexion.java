package Resource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Model.Repeticion;

/**
 * Created by ricar on 27/07/2016.
 */
public class InfoRepeticionSinConexion {
    public DataOffLine data;

    public InfoRepeticionSinConexion(Context context) {
        data = new DataOffLine(context);
    }
    /**
     * Funcion encarga de registrar una repeticion
     */
    public void cargarDatosDeRepeticion(int id,
                                        int idEjercicio,
                                        int peso,
                                        int repeticiones,
                                        int tDescanso,
                                        int tEjercicio,
                                        int uMedida) {

        SQLiteDatabase bd = data.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DataOffLine.DatosTablaRepeticion.COLUMNA_id,id);
        valores.put(DataOffLine.DatosTablaRepeticion.COLUMNA_idEjercicio,idEjercicio);
        valores.put(DataOffLine.DatosTablaRepeticion.COLUMNA_peso,peso);
        valores.put(DataOffLine.DatosTablaRepeticion.COLUMNA_repeticiones,repeticiones);
        valores.put(DataOffLine.DatosTablaRepeticion.COLUMNA_tiempoDescanso,tDescanso);
        valores.put(DataOffLine.DatosTablaRepeticion.COLUMNA_tiempoEjercicio,tEjercicio);
        valores.put(DataOffLine.DatosTablaRepeticion.COLUMNA_uMedida,uMedida);
        valores.put(DataOffLine.DatosTablaRepeticion.COLUMNA_estado,1);

        Long idGuardar = bd.insert(DataOffLine.DatosTablaRepeticion.NOMBRE_TABLA, DataOffLine.DatosTablaRepeticion.COLUMNA_id,valores);
        //Toast.makeText(getApplicationContext(),"Se guardo el datos : "+idGuardar, Toast.LENGTH_LONG);
        //si retorna un -1 es porque el registro ya existe
    }

    public Repeticion buscarRepeticion(int idRepeticion) {
        Repeticion r;
        //Conexion de consulta con la BD
        SQLiteDatabase bd = data.getReadableDatabase();

        //el id del elemento que busco
        String[] where ={Integer.toString(idRepeticion)};

        //Que quiero obtener en el orden que quiero
        String[] datosPedidos = {DataOffLine.DatosTablaRepeticion.COLUMNA_idEjercicio,
                                 DataOffLine.DatosTablaRepeticion.COLUMNA_peso,
                                 DataOffLine.DatosTablaRepeticion.COLUMNA_repeticiones,
                                 DataOffLine.DatosTablaRepeticion.COLUMNA_tiempoDescanso,
                                 DataOffLine.DatosTablaRepeticion.COLUMNA_tiempoEjercicio,
                                 DataOffLine.DatosTablaRepeticion.COLUMNA_uMedida,
                                 DataOffLine.DatosTablaRepeticion.COLUMNA_estado};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaRepeticion.NOMBRE_TABLA,
                                 datosPedidos,
                                 null,null, null, null, null);
            while (c.moveToNext()){

            }
        }catch (Exception e){

        }
        return new Repeticion(0,0,0,0,0,0,0);
    }
}
