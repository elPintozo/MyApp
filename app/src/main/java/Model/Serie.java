package Model;

/**
 * Created by ricar on 05/08/2016.
 */
public class Serie {
    public int idRepeticion;
    public int idSerie;
    public int peso;
    public int veces;
    public int tDescanso;
    public int tEjercicio;

    public Serie(int idRepeticion, int peso, int veces, int tDescanso, int tEjercicio) {
        this.idRepeticion = idRepeticion;
        this.peso = peso;
        this.veces = veces;
        this.tDescanso = tDescanso;
        this.tEjercicio = tEjercicio;
    }
}
