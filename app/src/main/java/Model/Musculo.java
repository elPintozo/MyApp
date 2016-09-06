package Model;

import android.widget.ImageView;

/**
 * Created by ricar on 18/07/2016.
 */
public class Musculo {
    public int idMusculo;
    public String nombreMusculo;
    public int imagenMusculo;
    public int Estado;

    public Musculo(int idMusculo, String nombreMusculo, int estado) {
        this.idMusculo = idMusculo;
        this.nombreMusculo = nombreMusculo;
        Estado = estado;
    }

    public Musculo(String nombreMusculo) {
        this.nombreMusculo = nombreMusculo;
    }
}
