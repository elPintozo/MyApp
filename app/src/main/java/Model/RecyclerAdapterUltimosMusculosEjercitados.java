package Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thepintozo.myappgym.R;

import java.util.ArrayList;

/**
 * Created by ricar on 02/09/2016.
 */
public class RecyclerAdapterUltimosMusculosEjercitados extends  RecyclerView.Adapter<RecyclerAdapterUltimosMusculosEjercitados.ViewHolder>{

    private ArrayList<String> ultimosEjercicio = new ArrayList<>();
    private Context context;
    private String fecha;

    public RecyclerAdapterUltimosMusculosEjercitados(ArrayList<String> ultimosEjercicio, String fecha, Context context) {
        this.ultimosEjercicio = ultimosEjercicio;
        this.context = context;
        this.fecha = fecha;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView nombre;
        //public TextView descripcion;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView)itemView.findViewById(R.id.imagenUltimoMusculoEjercitado);
            nombre =(TextView)itemView.findViewById(R.id.lblNombreUltimoMusculo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*int position = getAdapterPosition();
                    Intent i2 = new Intent(context, ComenzarEjercicioController.class);
                    i2.putExtra("idMusculo",idMusculo);
                    i2.putExtra("idEjercicio",ejercicios.get(position).idEjercicio);//<-----
                    i2.putExtra("idRutina",idRutina);
                    context.startActivity(i2);*/
                }
            });
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ultimos_musculos_ejercitados, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            holder.nombre.setText(ultimosEjercicio.get(position)+"\n ["+fecha+"]");
            String i = ultimosEjercicio.get(position).toLowerCase().replace(' ','_');
            int m = context.getResources().getIdentifier(i,"drawable",context.getPackageName());
            Glide.with(context)
                    .load(m)
                    .placeholder(R.drawable.cargando)
                    .error(R.drawable.error)
                    .override(350, 350)
                    .into(holder.imagen);
        }catch (Exception e){
            holder.nombre.setText("Error");
            holder.imagen.setImageResource(R.drawable.error);
        }
    }

    @Override
    public int getItemCount() {
        return  ultimosEjercicio.size();
    }
}
