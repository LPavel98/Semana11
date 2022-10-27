package com.example.examen.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.EditarPeliculaActivity;
import com.example.examen.R;
import com.example.examen.RepositoryPeliculas;
import com.example.examen.entidades.Pelicula;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculasViewHolder> {

    List<Pelicula> peliculasData;
    RepositoryPeliculas repositoryPeliculas;

    public PeliculaAdapter(List<Pelicula> data, RepositoryPeliculas repository){
        this.peliculasData = data;
        this.repositoryPeliculas = repository;
    }

    @NonNull
    @Override
    public PeliculaAdapter.PeliculasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_pelicula, parent, false);
        return new PeliculasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaAdapter.PeliculasViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Pelicula pelicula = peliculasData.get(position);

        holder.tvTitulo.setText(pelicula.getTitulo());
        holder.tvGenero.setText(pelicula.getGenero());
        holder.tvNacionalidad.setText(pelicula.getNacionalidad());
        holder.tvDirector.setText(pelicula.getDirector());
        holder.tvAnio.setText(pelicula.getAnio());


        holder.btnEdit.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), EditarPeliculaActivity.class);
            intent.putExtra("PELI_EXTRA", new Gson().toJson(pelicula));
            view.getContext().startActivity(intent);
        });


        holder.btnDelete.setOnClickListener(view -> {
            repositoryPeliculas.eliminarPelicula(pelicula.getId()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()){
                        peliculasData.remove(position);
                        notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        });

    }

    @Override
    public int getItemCount() {
        return peliculasData.size();
    }

    public class PeliculasViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitulo;
        TextView tvGenero;
        TextView tvNacionalidad;
        TextView tvDirector;
        TextView tvAnio;
        Button btnEdit;
        Button btnDelete;

        public PeliculasViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvGenero = itemView.findViewById(R.id.tvGenero);
            tvNacionalidad = itemView.findViewById(R.id.tvNacionalidad);
            tvDirector = itemView.findViewById(R.id.tvDirector);
            tvAnio = itemView.findViewById(R.id.tvAnio);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
