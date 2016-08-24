package Model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thepintozo.myappgym.R;
import com.thepintozo.myappgym.SeleccionarEjercicioController;
import com.thepintozo.myappgym.SeleccionarMusculoController;

import java.util.ArrayList;

/**
 * Created by ricar on 23/08/2016.
 */
public class RecyclerAdapterMusculo extends RecyclerView.Adapter<RecyclerAdapterMusculo.ViewHolder> {

    int[] imagenes={R.mipmap.bicep,
                    R.mipmap.tricep,
                    R.mipmap.abdomen,
                    R.mipmap.piernas,
                    R.mipmap.hombro,
                    R.mipmap.espalda,
                    R.mipmap.pectorales};
    private ArrayList<Musculo> musculos = new ArrayList<>();
    private int idRutina;
    private Context context;
    /**
     *
     * @param musculos
     */
    public RecyclerAdapterMusculo(ArrayList<Musculo> musculos, int idRutina, Context context){
        this.musculos = musculos;
        this.idRutina = idRutina;
        this.context = context;
    }

    /**
     *
     */
    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView nombre;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView)itemView.findViewById(R.id.imagenMusculo);
            nombre =(TextView)itemView.findViewById(R.id.lblNombreMusculo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent i2 = new Intent(context, SeleccionarEjercicioController.class);
                    i2.putExtra("idRutina", idRutina);
                    i2.putExtra("idMusculo", musculos.get(position).idMusculo);
                    context.startActivity(i2);
                }
            });
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_musculo, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(musculos.get(position).nombreMusculo);
        holder.imagen.setImageResource(imagenes[position]);
    }

    @Override
    public int getItemCount() {
        return  musculos.size();
    }
}
