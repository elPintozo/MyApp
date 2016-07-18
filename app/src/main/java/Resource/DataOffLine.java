package Resource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by ricar on 18/07/2016.
 */
public class DataOffLine extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME ="dbMiRutina.db";

    public DataOffLine(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        //creo las tablas en la base de datos
        db.execSQL(DatosTablaMusculo.SQL_CREATE_TABlE);
        db.execSQL(DatosTablaEjercicio.SQL_CREATE_TABlE);
        db.execSQL(DatosTablaRepeticion.SQL_CREATE_TABlE);
        db.execSQL(DatosTablaRutina.SQL_CREATE_TABlE);
        db.execSQL(DatosTablaRutinaRepeticion.SQL_CREATE_TABlE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //creo las tablas en la base de datos
        db.execSQL(DatosTablaMusculo.SQL_DELETE_TABLE);
        db.execSQL(DatosTablaEjercicio.SQL_DELETE_TABLE);
        db.execSQL(DatosTablaRepeticion.SQL_DELETE_TABLE);
        db.execSQL(DatosTablaRutina.SQL_DELETE_TABLE);
        db.execSQL(DatosTablaRutinaRepeticion.SQL_DELETE_TABLE);
        onCreate(db);
    }

    public static abstract class DatosTablaMusculo implements BaseColumns {
        public static final String NOMBRE_TABLA     = "Musculo";
        public static final String COLUMNA_id       = "idMusculo";
        public static final String COLUMNA_nombre   = "nombreMusculo";
        public static final String COLUMNA_estado   = "estado";

        public static final String SQL_CREATE_TABlE = "CREATE TABLE "+DatosTablaMusculo.NOMBRE_TABLA +" ("+
                DatosTablaMusculo.COLUMNA_id     + " INTEGER PRIMARY KEY, "+
                DatosTablaMusculo.COLUMNA_nombre + " TEXT, "+
                DatosTablaMusculo.COLUMNA_estado + " INTEGER " +") ";
        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS  "+DatosTablaMusculo.NOMBRE_TABLA;
    }

    public static abstract class DatosTablaEjercicio implements BaseColumns{
        public static  final String NOMBRE_TABLA      = "Ejercicio";
        public static  final String COLUMNA_id        = "idEjercicio";
        public static  final String COLUMNA_nombre    = "nombreEjercicio";
        public static  final String COLUMNA_detalle   = "detalleEjercicio";
        public static  final String COLUMNA_idMusculo = "idMusculo";
        public static  final String COLUMNA_estado    = "estado";
        public static  final String SQL_CREATE_TABlE  = "CREATE TABLE "+DatosTablaEjercicio.NOMBRE_TABLA +" ("+
                DatosTablaEjercicio.COLUMNA_id        + " INTEGER PRIMARY KEY, "+
                DatosTablaEjercicio.COLUMNA_nombre    + " TEXT, "+
                DatosTablaEjercicio.COLUMNA_detalle   + " TEXT, "+
                DatosTablaEjercicio.COLUMNA_idMusculo + " INTEGER, "+
                DatosTablaEjercicio.COLUMNA_estado    + " TEXT, " +
                "FOREIGN KEY ("+DatosTablaEjercicio.COLUMNA_idMusculo+") REFERENCES Musculo(idMusculo)" +" ) ";
        public static  final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS  "+DatosTablaEjercicio.NOMBRE_TABLA;
    }

    public static abstract class DatosTablaRepeticion implements BaseColumns{
        public static final String NOMBRE_TABLA            = "Repeticion";
        public static final String COLUMNA_id              = "idRepeticion";
        public static final String COLUMNA_idEjercicio     = "idEjercicio";
        public static final String COLUMNA_peso            = "peso";
        public static final String COLUMNA_repeticiones    = "repeticiones";
        public static final String COLUMNA_tiempoDescanso  = "tiempoDescanso";
        public static final String COLUMNA_tiempoEjercicio = "tiempoEjercicio";
        public static final String COLUMNA_uMedida         = "uMedida";
        public static final String COLUMNA_estado          = "estado";
        public static final String SQL_CREATE_TABlE        = "CREATE TABLE "+DatosTablaRepeticion.NOMBRE_TABLA +" ("+
                DatosTablaRepeticion.COLUMNA_id              + " INTEGER PRIMARY KEY, "+
                DatosTablaRepeticion.COLUMNA_idEjercicio     + " INTEGER, "+
                DatosTablaRepeticion.COLUMNA_peso            + " INTEGER, "+
                DatosTablaRepeticion.COLUMNA_repeticiones    + " INTEGER, " +
                DatosTablaRepeticion.COLUMNA_tiempoDescanso  + " INTEGER, " +
                DatosTablaRepeticion.COLUMNA_tiempoEjercicio + " INTEGER, " +
                DatosTablaRepeticion.COLUMNA_uMedida         + " INTEGER, " +
                DatosTablaRepeticion.COLUMNA_estado          + " INTEGER, " +
                "FOREIGN KEY ("+DatosTablaRepeticion.COLUMNA_idEjercicio+") REFERENCES Ejercicio(idEjercicio)" +") ";
        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS  "+DatosTablaRepeticion.NOMBRE_TABLA;
    }

    public static abstract class DatosTablaRutina implements BaseColumns{
        public static final String NOMBRE_TABLA   = "Rutina";
        public static final String COLUMNA_id     = "idRutina";
        public static final String COLUMNA_fecha  = "fecha";
        public static final String COLUMNA_estado = "estado";
        public static final String SQL_CREATE_TABlE  = "CREATE TABLE "+DatosTablaRutina.NOMBRE_TABLA +" ("+
                DatosTablaRutina.COLUMNA_id       + " INTEGER PRIMARY KEY, "+
                DatosTablaRutina.COLUMNA_fecha    + " TEXT, "+
                DatosTablaRutina.COLUMNA_estado   + " INTEGER" +") ";
        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS  "+DatosTablaRutina.NOMBRE_TABLA;
    }

    public static abstract class DatosTablaRutinaRepeticion implements BaseColumns{
        public static final String NOMBRE_TABLA         = "RutinaRepeticion";
        public static final String COLUMNA_idRutina     = "idRutina";
        public static final String COLUMNA_idRepeticion = "idRepeticion";
        public static final String SQL_CREATE_TABlE     = "CREATE TABLE "+DatosTablaRutinaRepeticion.NOMBRE_TABLA +" ("+
                DatosTablaRutinaRepeticion.COLUMNA_idRutina       + " INTEGER PRIMARY KEY, "+
                DatosTablaRutinaRepeticion.COLUMNA_idRepeticion   + " INTEGER, "+
                "FOREIGN KEY ("+DatosTablaRutinaRepeticion.COLUMNA_idRutina +")     REFERENCES Rutina(idRutina), "+
                "FOREIGN KEY ("+DatosTablaRutinaRepeticion.COLUMNA_idRepeticion +") REFERENCES Repeticion(idRepeticion)" +") ";
        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS  "+DatosTablaRutinaRepeticion.NOMBRE_TABLA;
    }

}
