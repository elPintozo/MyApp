package Resource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Model.Ejercicio;

/**
 * Created by ricar on 18/07/2016.
 */
public class InfoEjerciciosSinConexion {
    public DataOffLine data;

    public InfoEjerciciosSinConexion(Context context) {
        data = new DataOffLine(context);
    }

    /**
     * Funcion encargada de registrar un ejericio
     * @param id : id del ejercicio
     * @param idMusculo . id del musculo asociado al ejercicio
     * @param nombre : nombre del ejericio
     * @param detalle : detalles sobre la ejecucion del ejercicio
     */
    public void cargarDatosDeEjercicio(int id,
                                       int idMusculo,
                                       String nombre,
                                       String detalle) {

        SQLiteDatabase bd = data.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DataOffLine.DatosTablaEjercicio.COLUMNA_id,id);
        valores.put(DataOffLine.DatosTablaEjercicio.COLUMNA_nombre,nombre);
        valores.put(DataOffLine.DatosTablaEjercicio.COLUMNA_detalle,detalle);
        valores.put(DataOffLine.DatosTablaEjercicio.COLUMNA_idMusculo,idMusculo);
        valores.put(DataOffLine.DatosTablaEjercicio.COLUMNA_estado,1);

        Long idGuardar = bd.insert(DataOffLine.DatosTablaEjercicio.NOMBRE_TABLA,
                DataOffLine.DatosTablaEjercicio.COLUMNA_id,
                valores);
        //si retorna un -1 es porque el registro ya existe
    }
    /**
     * Funcion encarga de eliminar un registro de la tabla Ejercicio
     * @return [int] : 1 para eliminada exitosa, 0 para error de elimacion
     */
    public int borrarEjercicio(int idEjerccio){
        //Conexion de consulta con la BD
        SQLiteDatabase bd = data.getWritableDatabase();

        //el id del elemento que quiero borrar
        String[] where ={Integer.toString(idEjerccio)};

        //indico en que columna buscar
        String seleccion = DataOffLine.DatosTablaEjercicio.COLUMNA_id+"=?";

        try {
            bd.delete(DataOffLine.DatosTablaEjercicio.NOMBRE_TABLA, seleccion, where);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    /**
     * Funcion que retorna una lista de todos los ejericios
     * @return ArrayList<Ejercicio>
     */
    public ArrayList<Ejercicio> obtenerEjercicios(){
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();

        //Conexion de consulta con la BD
        SQLiteDatabase bd = data.getReadableDatabase();

        //Que quiero obtener en el orden que quiero
        String[] datosPedidos = {DataOffLine.DatosTablaEjercicio.COLUMNA_id,
                DataOffLine.DatosTablaEjercicio.COLUMNA_idMusculo,
                DataOffLine.DatosTablaEjercicio.COLUMNA_nombre,
                DataOffLine.DatosTablaEjercicio.COLUMNA_detalle,
                DataOffLine.DatosTablaEjercicio.COLUMNA_estado};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaEjercicio.NOMBRE_TABLA,
                    datosPedidos,
                    null,null, null, null, null);

            while (c.moveToNext()){
                Ejercicio e  = new Ejercicio(Integer.parseInt(c.getString(0)),
                                             Integer.parseInt(c.getString(1)),
                                             c.getString(2),
                                             c.getString(3),
                                             Integer.parseInt(c.getString(4)));
                ejercicios.add(e);
            }
            return  ejercicios;
        }
        catch (Exception e){
            return ejercicios ;
        }
    }

    /**
     * Funcion encarga de enviar un arraylist con los ejericios asociados a un id de musculo
     * @param idMusculo [int] : id del musculo
     * @return ArrayList<Ejercicio>
     */
    public ArrayList<Ejercicio> obtenerEjerciciosDeUnMusculo(int idMusculo){
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();

        //Conexion de consulta con la BD
        SQLiteDatabase bd = data.getReadableDatabase();

        //Que quiero obtener en el orden que quiero
        String[] datosPedidos = {DataOffLine.DatosTablaEjercicio.COLUMNA_id,
                                 DataOffLine.DatosTablaEjercicio.COLUMNA_idMusculo,
                                 DataOffLine.DatosTablaEjercicio.COLUMNA_nombre,
                                 DataOffLine.DatosTablaEjercicio.COLUMNA_detalle,
                                 DataOffLine.DatosTablaEjercicio.COLUMNA_estado};

        //el id del elemento que quiero borrar
        String[] where ={Integer.toString(idMusculo)};

        //indico en que columna buscar
        String seleccion = DataOffLine.DatosTablaEjercicio.COLUMNA_idMusculo+"=?";

        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaEjercicio.NOMBRE_TABLA,
                    datosPedidos,
                    seleccion,where, null, null, null);

            while (c.moveToNext()){
                Ejercicio e  = new Ejercicio(Integer.parseInt(c.getString(0)),
                        Integer.parseInt(c.getString(1)),
                        c.getString(2),
                        c.getString(3),
                        Integer.parseInt(c.getString(4)));
                ejercicios.add(e);
            }
            return  ejercicios;
        }
        catch (Exception e){
            ejercicios = null;
            return ejercicios ;
        }
    }
}
