package com.example.proyectofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectofinal.models.Evento;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener{

    private ArrayList<Evento> carteleras;
    private Context Context;

    private View.OnClickListener listener; /////////////


    public RecyclerViewAdapter(ArrayList<Evento> carteleras, Context context) {
        this.carteleras = carteleras;
        this.Context = context;
    }

    @NonNull
    @Override   //En este apartado se especifica cual es el XML que se desa inflar en el recyclerview
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_eventos,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override  // colocaremos en el componente del xml los datos que queremos es decir la imagen y el titulo .
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titulo.setText(carteleras.get(position).getTitulo());
        Glide.with(Context)
             .load(carteleras.get(position).getFoto())
             .into(holder.Foto);

    }

    @Override
    public int getItemCount() {
        return carteleras.size();
    }

    //creacion de metodo para seleccion de cards en recyclearview
    public void  setOnClickListener(View.OnClickListener listener){
        this.listener= listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }

    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        private ImageView Foto;
        private TextView  titulo;
        private CardView  CardEvento;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CardEvento = itemView.findViewById(R.id.CardEvento);
            Foto = itemView.findViewById(R.id.imagen);
            titulo = itemView.findViewById(R.id.titulo);

        }
    }
}
