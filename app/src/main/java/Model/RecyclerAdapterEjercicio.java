package Model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thepintozo.myappgym.ComenzarEjercicioController;
import com.thepintozo.myappgym.R;

import java.util.ArrayList;

/**
 * Created by ricar on 23/08/2016.
 */
public class RecyclerAdapterEjercicio extends  RecyclerView.Adapter<RecyclerAdapterEjercicio.ViewHolder>{

    private ArrayList<Ejercicio> ejercicios = new ArrayList<>();
    private Context context;
    private int idRutina;
    private int idMusculo;

    public RecyclerAdapterEjercicio(ArrayList<Ejercicio> ejercicios, Context context, int idRutina, int idMusculo) {
        this.ejercicios = ejercicios;
        this.context = context;
        this.idRutina = idRutina;
        this.idMusculo = idMusculo;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView nombre;
        //public TextView descripcion;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView)itemView.findViewById(R.id.imagenEjercicio);
            nombre =(TextView)itemView.findViewById(R.id.lblNombreEjercicio);
            //descripcion =(TextView)itemView.findViewById(R.id.lblDescripcionEjercicio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent i2 = new Intent(context, ComenzarEjercicioController.class);
                    i2.putExtra("idMusculo",idMusculo);
                    i2.putExtra("idEjercicio",ejercicios.get(position).idEjercicio);//<-----
                    i2.putExtra("idRutina",idRutina);
                    context.startActivity(i2);
                }
            });
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ejercicio, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            holder.nombre.setText(ejercicios.get(position).nombreEjercicio);
            String i = ejercicios.get(position).nombreEjercicio.toLowerCase().replace(' ','_');
            int m = context.getResources().getIdentifier(i,"drawable",context.getPackageName());
            Glide.with(context)
                    .load(m)
                    .placeholder(R.drawable.cargando)
                    .error(R.drawable.error)
                    .override(200, 200)
                    .into(holder.imagen);
        }catch (Exception e){
            holder.nombre.setText("Error");
            holder.imagen.setImageResource(R.drawable.error);
        }
    }

    @Override
    public int getItemCount() {
        return  ejercicios.size();
    }
}
