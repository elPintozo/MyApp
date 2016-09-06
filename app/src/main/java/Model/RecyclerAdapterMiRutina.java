package Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thepintozo.myappgym.R;

import java.util.ArrayList;

/**
 * Created by ricar on 02/09/2016.
 */
public class RecyclerAdapterMiRutina extends RecyclerView.Adapter<RecyclerAdapterMiRutina.ViewHolder> {

    private ArrayList<CapsulaEjercicio> ultimos = new ArrayList<>();
    private Context context;

    public RecyclerAdapterMiRutina(ArrayList<CapsulaEjercicio> ultimos, Context context) {
        this.ultimos = ultimos;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView nombre;
        public TextView series;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView)itemView.findViewById(R.id.imagenMusculoEjercitadoAhora);
            nombre =(TextView)itemView.findViewById(R.id.lblNombreMusculoEjercitadoAhora);
            series =(TextView)itemView.findViewById(R.id.lblSeriesMusculoEjercitadoAhora);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*int position = getAdapterPosition();
                    Intent i2 = new Intent(context, SeleccionarEjercicioController.class);
                    i2.putExtra("idRutina", idRutina);
                    i2.putExtra("idMusculo", musculos.get(position).idMusculo);
                    context.startActivity(i2);*/
                }
            });
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mirutina, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{
            holder.nombre.setText(ultimos.get(position).nombreMusculo);
            holder.series.setText("Has realizado "+String.valueOf(ultimos.get(position).numeroRepeticiones)+
                                  " series, \n con diferentes ejercicios.");
            String i = ultimos.get(position).nombreMusculo.toLowerCase().replace(' ','_');
            int m = context.getResources().getIdentifier(i,"drawable",context.getPackageName());
            holder.imagen.setImageResource(m);
        }catch (Exception e){
            String i = ultimos.get(position).nombreMusculo.toLowerCase().replace(' ','_');
            holder.nombre.setText("Error: "+i);
            holder.series.setText("Error: "+ultimos.get(position).numeroRepeticiones);
            holder.imagen.setImageResource(R.drawable.error);
        }
    }

    @Override
    public int getItemCount() {
        return  ultimos.size();
    }
}
