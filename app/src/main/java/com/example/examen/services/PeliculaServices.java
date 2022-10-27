package com.example.examen.services;

import com.example.examen.adapter.PeliculaAdapter;
import com.example.examen.entidades.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PeliculaServices {
    //listar
    @GET("peliculas")
    Call<List<Pelicula>> listPeliculas();

    //guardar
    @POST("peliculas")
    Call<Void> create(@Body Pelicula pelicula);

    //Actulaizar
    @PUT("peliculas/{idPelicula}")
    Call<Void> update(@Body Pelicula pelicula, @Path("idPelicula")int id);

    //eliminar
    @DELETE("peliculas/{idPelicula}")
    Call<Void> delete(@Path("idPelicula")int id);

}
