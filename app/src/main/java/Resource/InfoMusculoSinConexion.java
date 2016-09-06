package Resource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Model.Musculo;

/**
 * Created by ricar on 18/07/2016.
 */
public class InfoMusculoSinConexion{

    public DataOffLine data;
    public InfoMusculoSinConexion(Context context) {
        data = new DataOffLine(context);
    }

    /**
     * Funcion encargada de cargar un registro de tipo musculo desde el smartphone
     * @param id [int] : id del musculo a cargar
     * @param nombre [String] : nombre del musculo
     * @return [int] : 1 si todo sale bien, -1 el registro ya existe, 0 error
     */
    public int cargarDatosDeMusculo(int id,
                                    String nombre ) {

        SQLiteDatabase bd = data.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DataOffLine.DatosTablaMusculo.COLUMNA_id,id);
        valores.put(DataOffLine.DatosTablaMusculo.COLUMNA_nombre,nombre);
        valores.put(DataOffLine.DatosTablaMusculo.COLUMNA_estado,1);

        try {
            Long idGuardar = bd.insert(DataOffLine.DatosTablaMusculo.NOMBRE_TABLA,
                    DataOffLine.DatosTablaMusculo.COLUMNA_id,
                    valores);
            return idGuardar.intValue();
        }
        catch (Exception e){
            return 0;
        }
        //si retorna un -1 es porque el registro ya existe
    }
    /**
     * Funcion que retorna un Objeto Musculo si es que este se encuentra en la base de datos
     * @param idMusculo [int] : corresponde al id del musculo buscado
     * @return [Musculo] : Un objeto de tipo musculo
     */
    public Musculo buscarMusculo(int idMusculo){
        Musculo m;
        //Conexion de consulta con la BD
        SQLiteDatabase bd = data.getReadableDatabase();
        //el id del elemento que busco
        String[] where ={Integer.toString(idMusculo)};
        //Que quiero obtener en el orden que quiero
        String[] datosPedidos = {DataOffLine.DatosTablaMusculo.COLUMNA_id,
                                 DataOffLine.DatosTablaMusculo.COLUMNA_nombre,
                                 DataOffLine.DatosTablaMusculo.COLUMNA_estado};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaMusculo.NOMBRE_TABLA,
                                 datosPedidos,
                                 DataOffLine.DatosTablaMusculo.COLUMNA_id+"=?",
                                 where, null, null, null);
            //ordeno lo obtenido en una fila
            c.moveToFirst();
            //genero el objeto que enviare como resultado
            m = new Musculo(Integer.parseInt(c.getString(0)),c.getString(1),Integer.parseInt(c.getString(2)));
            return m;
        }
        catch (Exception e){
            m = null;
            return m ;
        }
    }
    /**
     * Funcion que retorna un Objeto Musculo si es que este se encuentra en la base de datos
     * @param nombreMusculo [String] : corresponde al nombre del musculo buscado
     * @return [Musculo] : Un objeto de tipo musculo
     */
    public Musculo buscarMusculoPorNombre(String nombreMusculo){
        Musculo m = null;
        //Conexion de consulta con la BD
        SQLiteDatabase bd = data.getReadableDatabase();
        //el id del elemento que busco
        String[] where ={nombreMusculo};
        //Que quiero obtener en el orden que quiero
        String[] datosPedidos = {DataOffLine.DatosTablaMusculo.COLUMNA_id,
                DataOffLine.DatosTablaMusculo.COLUMNA_nombre,
                DataOffLine.DatosTablaMusculo.COLUMNA_estado};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaMusculo.NOMBRE_TABLA,
                    datosPedidos,
                    DataOffLine.DatosTablaMusculo.COLUMNA_nombre+"=?",
                    where, null, null, null);

            while (c.moveToNext()) {
                //genero el objeto que enviare como resultado
                m = new Musculo(Integer.parseInt(c.getString(0)), c.getString(1), Integer.parseInt(c.getString(2)));
            }
            return m;
        }
        catch (Exception e){
            return m ;
        }
    }
    /**
     * Funcion para revisar el paso a paso de la funcion
     * @param idMusculo [int] : corresponde al id del musculo buscado
     * @return [String] : Es un string que retorna el n° de pasos realizados, si faltan es porque hay
     * un error durante el transcurso de la ejecucion de la funcion
     */
    public String buscarMusculoTest(int idMusculo){
        String respuesta="";
        Musculo m;
        //Conexion de consulta con la BD
        SQLiteDatabase bd = data.getReadableDatabase();
        respuesta = respuesta+"1, ";
        //el id del elemento que busco
        String[] where ={Integer.toString(idMusculo)};
        respuesta = respuesta+"2, ";
        //Que quiero obtener en el orden que quiero
        String[] datosPedidos = {DataOffLine.DatosTablaMusculo.COLUMNA_id,
                                 DataOffLine.DatosTablaMusculo.COLUMNA_nombre,
                                 DataOffLine.DatosTablaMusculo.COLUMNA_estado};
        respuesta = respuesta+"3, ";
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaMusculo.NOMBRE_TABLA,
                                 datosPedidos,
                                 DataOffLine.DatosTablaMusculo.COLUMNA_id+"=?",
                                 where, null, null, null);
            respuesta = respuesta+"4, ";
            //ordeno lo obtenido en una fila
            c.moveToFirst();
            respuesta = respuesta+"5, ";
            //genero el objeto que enviare como resultado
            //m = new Musculo(Integer.parseInt(c.getString(1)),c.getString(0),Integer.parseInt(c.getString(2)));
            //return m;
            respuesta = respuesta+"6 ((0)- "+c.getString(0)+" (1)-"+c.getString(1)+"  (2)-"+c.getString(2)+")";
            return respuesta;
        }
        catch (Exception e){
            m = null;
            respuesta = respuesta+"error. ";
            return respuesta;
            //return m ;
        }
    }
    /**
     *Funcion encarga de actualizar la informacion de un objeto Musculo
     * @param idMusculo [int] : id del musculo a actualizar
     * @param tipoActualizacion [int] : 0 para cambiar el nombre / 1 para su estado
     * @param  nuevoValor [String] : nuevo valor a agregar
     * @return [int] :
     */
    public int actualizarMusculo(int idMusculo, int tipoActualizacion, String nuevoValor){

        SQLiteDatabase bd =  data.getWritableDatabase();
        ContentValues valores = new ContentValues();

        if(tipoActualizacion==0) {//cambiar el nombre
            valores.put(DataOffLine.DatosTablaMusculo.COLUMNA_nombre, nuevoValor);
        }
        else{//cambiar su estado
            valores.put(DataOffLine.DatosTablaMusculo.COLUMNA_estado, Integer.parseInt(nuevoValor));
        }
        //el id del elemento que quiero borrar
        String[] where ={Integer.toString(idMusculo)};

        //indico en que columna buscar
        String seleccion = DataOffLine.DatosTablaMusculo.COLUMNA_id+"=?";

        int respuesta = bd.update(DataOffLine.DatosTablaMusculo.NOMBRE_TABLA,
                valores,
                seleccion,
                where);
        return respuesta;
    }

    /**
     * Funcion que retorna un arraylist con todos lo musculos almacenados en la app
     * @return ArrayList<Musculo>
     */
    public ArrayList<Musculo> obtenerMusculos(){
        ArrayList<Musculo> musculos = new ArrayList<>();

        //Conexion de consulta con la BD
        SQLiteDatabase bd = data.getReadableDatabase();
        //Que quiero obtener en el orden que quiero
        String[] datosPedidos = {DataOffLine.DatosTablaMusculo.COLUMNA_id,
                                 DataOffLine.DatosTablaMusculo.COLUMNA_nombre,
                                 DataOffLine.DatosTablaMusculo.COLUMNA_estado};
        try {
            // realizo la consulta
            Cursor c = bd.query( DataOffLine.DatosTablaMusculo.NOMBRE_TABLA,
                    datosPedidos,
                    null,null, null, null, null);
            while (c.moveToNext()){
                Musculo m = new Musculo(Integer.parseInt(c.getString(0)),c.getString(1),Integer.parseInt(c.getString(2)));
                musculos.add(m);

            }
            return  musculos;
        }
        catch (Exception e){
            return musculos ;
        }
    }
    /**
     * Funcion encarga de eliminar un registro de la tabla Musculo
     * @return [int] : 1 para eliminada exitosa, 0 para error de elimacion
     */
    public int borrarMusculo(int idMusculo){
        //Conexion de consulta con la BD
        SQLiteDatabase bd = data.getWritableDatabase();

        //el id del elemento que quiero borrar
        String[] where ={Integer.toString(idMusculo)};

        //indico en que columna buscar
        String seleccion = DataOffLine.DatosTablaMusculo.COLUMNA_id+"=?";

        try {
            bd.delete(DataOffLine.DatosTablaMusculo.NOMBRE_TABLA, seleccion, where);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    /**
     * Funcion encargada de retornar el proximo id para una nuevo ejercicio
     * @return [int] : id para una nuevo ejercicio
     */
    public int proximoMusculo(){
        ArrayList<Musculo> musculos = this.obtenerMusculos();
        if(musculos.size()==0){
            return 1;
        }
        else {
            int id=0;
            for (Musculo r: musculos) {
                if (r.idMusculo>id){
                    id=r.idMusculo;
                }
            }
            return (id+1);
        }
    }
}
