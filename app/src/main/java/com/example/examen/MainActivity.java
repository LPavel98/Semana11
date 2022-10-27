package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.examen.adapter.PeliculaAdapter;
import com.example.examen.entidades.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvListarpeliculas;
    Button btnAgregar;
    List<Pelicula> peliculas;
    PeliculaAdapter peliculaAdapter;
    RepositoryPeliculas repositoryPeliculas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListarpeliculas = findViewById(R.id.rvListaPeliculas);
        repositoryPeliculas =new RepositoryPeliculas();
        ListarPeliculas();
        btnAgregar = findViewById(R.id.btnAgregar);



        btnAgregar.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), EditarPeliculaActivity.class));
        });

    }

    public void ListarPeliculas() {
        repositoryPeliculas.liPelicula().enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                if (response.isSuccessful()) {
                    peliculas = response.body();
                    peliculaAdapter = new PeliculaAdapter(peliculas, repositoryPeliculas);
                    rvListarpeliculas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvListarpeliculas.setAdapter(peliculaAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {

            }
        });
    }

}