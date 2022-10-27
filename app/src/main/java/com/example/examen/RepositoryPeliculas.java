package com.example.examen;

import com.example.examen.adapter.PeliculaAdapter;
import com.example.examen.entidades.Pelicula;
import com.example.examen.services.PeliculaServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryPeliculas {
    private  static  final String BASE_URL = "https://6356fead9243cf412f919b57.mockapi.io/";
    private  final PeliculaServices peliculaServices;

    public RepositoryPeliculas() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        peliculaServices = retrofit.create(PeliculaServices.class);
    }

    public Call<List<Pelicula>> liPelicula(){
        return peliculaServices.listPeliculas();
    }

    public Call<Void> crearPelicula(Pelicula pelicula){
        return peliculaServices.create(pelicula);
    }

    public Call<Void> actualizarPelicula(Pelicula pelicula, int id){
        return peliculaServices.update(pelicula, id);
    }

    public Call<Void> eliminarPelicula(int id){
        return peliculaServices.delete(id);
    }

}
