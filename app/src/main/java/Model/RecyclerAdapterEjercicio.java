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
    private int[] imagenes;

    public RecyclerAdapterEjercicio(ArrayList<Ejercicio> ejercicios, Context context, int idRutina, int idMusculo) {
        this.ejercicios = ejercicios;
        this.context = context;
        this.idRutina = idRutina;
        this.idMusculo = idMusculo;
        cargarImagenes();
    }

    private void cargarImagenes() {
        if(idMusculo==1){
            imagenes = new int[]{R.mipmap.reverse_wrist_curl,
                                 R.mipmap.standing_biceps_curl,
                                 R.mipmap.zottman_curl};
        }
        if(idMusculo==2){
            imagenes = new int[]{R.mipmap.extension_vertical_alternada_de_los_brazos,
                                 R.mipmap.extension_alternada_de_los_antebrazos,
                                 R.mipmap.extension_de_triceps_en_polea};
        }
        if(idMusculo==3){
            imagenes = new int[]{R.mipmap.clam,
                                 R.mipmap.clam,
                                 R.mipmap.cable_kneeling_crunch};
        }
        if(idMusculo==4){
            imagenes = new int[]{R.mipmap.squats_on_the_shoulders,
                    R.mipmap.squats_on_the_shoulders,
                    R.mipmap.pacing_with_dumbbells};
        }
        if(idMusculo==5){
            imagenes = new int[]{R.mipmap.cable_upright_row,
                    R.mipmap.elevacion_lateral_de_mancuernas,
                    R.mipmap.elevacion_trasera_de_mancuerna};
        }
        if(idMusculo==6){
            imagenes = new int[]{R.mipmap.pull_over_con_polea_alta,
                    R.mipmap.polea_trasnuca,
                    R.mipmap.polea_trasnuca};
        }
        if(idMusculo==7){
            imagenes = new int[]{R.mipmap.levantamiento_de_mancuernas_en_inclinacion,
                    R.mipmap.press_de_banca_inclinado,
                    R.mipmap.apertura_de_mancuernas_inclinado};
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView nombre;
        public TextView descripcion;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView)itemView.findViewById(R.id.imagenEjercicio);
            nombre =(TextView)itemView.findViewById(R.id.lblNombreEjercicio);
            descripcion =(TextView)itemView.findViewById(R.id.lblDescripcionEjercicio);

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
        holder.nombre.setText(ejercicios.get(position).nombreEjercicio);
        holder.descripcion.setText("");
        holder.imagen.setImageResource(imagenes[position]);
    }

    @Override
    public int getItemCount() {
        return  ejercicios.size();
    }
}
