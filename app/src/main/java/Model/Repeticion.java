package Model;

/**
 * Created by ricar on 18/07/2016.
 */
public class Repeticion {
    public int idRepeticion;
    public int idEjercicio;
    public int peso;
    public int repeticiones;
    public int tiempoDescanso;
    public int tiempoEjercicio;
    public int uMedida;

    public Repeticion(int idRepeticion, int idEjercicio, int peso, int repeticiones, int tiempoDescanso, int tiempoEjercicio, int uMedida) {
        this.idRepeticion = idRepeticion;
        this.idEjercicio = idEjercicio;
        this.peso = peso;
        this.repeticiones = repeticiones;
        this.tiempoDescanso = tiempoDescanso;
        this.tiempoEjercicio = tiempoEjercicio;
        this.uMedida = uMedida;
    }
}
