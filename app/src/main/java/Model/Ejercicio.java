package Model;

import android.widget.ImageView;

/**
 * Created by ricar on 18/07/2016.
 */
public class Ejercicio {
    public int idMusculo;
    public int idEjercicio;
    public String nombreEjercicio;
    public String detalleEjercicio;
    public ImageView imagenEjercicio;
    public int estado;

    public Ejercicio(int idEjercicio, int idMusculo, String nombreEjercicio, String detalleEjercicio, int estado) {
        this.idMusculo = idMusculo;
        this.idEjercicio = idEjercicio;
        this.nombreEjercicio = nombreEjercicio;
        this.detalleEjercicio = detalleEjercicio;
        this.estado = estado;
    }
}
