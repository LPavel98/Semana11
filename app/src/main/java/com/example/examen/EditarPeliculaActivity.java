package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.examen.entidades.Pelicula;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarPeliculaActivity extends AppCompatActivity {

    RepositoryPeliculas repositoryPeliculas;
    Pelicula pelicula;

    private String TYPE_ACTION = "edit";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pelicula);

        EditText etTitulo = findViewById(R.id.etTitulo);
        EditText etGenero = findViewById(R.id.etGenero);
        EditText etNacionalidad = findViewById(R.id.etNacionalidad);
        EditText etDirector = findViewById(R.id.etDirector);
        EditText etAnio = findViewById(R.id.etAnio);
        TextView tvTittle = findViewById(R.id.tvEditarPelicula);
        Button btnGuardar = findViewById(R.id.btnGuardar);


        repositoryPeliculas = new RepositoryPeliculas();

        if (getIntent().getExtras() != null){
            pelicula = new Gson().fromJson(getIntent().getStringExtra("PELI_EXTRA"),Pelicula.class);
            etTitulo.setText(pelicula.getTitulo());
            etGenero.setText(pelicula.getGenero());
            etNacionalidad.setText(pelicula.getNacionalidad());
            etDirector.setText(pelicula.getDirector());
            etAnio.setText(pelicula.getAnio());

            TYPE_ACTION = "edit";

        }else{
            tvTittle.setText("Crear Pelicula");
            TYPE_ACTION = "create";
        }

        btnGuardar.setOnClickListener(view -> {
            if (TYPE_ACTION.equals("create")){
                Pelicula pelicula = new Pelicula();

                pelicula.setTitulo(etTitulo.getText().toString());
                pelicula.setGenero(etGenero.getText().toString());
                pelicula.setNacionalidad(etNacionalidad.getText().toString());
                pelicula.setDirector(etDirector.getText().toString());
                pelicula.setAnio(etAnio.getText().toString());

                repositoryPeliculas.crearPelicula(pelicula).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Refresh();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
            else{
                pelicula.setTitulo(etTitulo.getText().toString());
                pelicula.setGenero(etGenero.getText().toString());
                pelicula.setNacionalidad(etNacionalidad.getText().toString());
                pelicula.setDirector(etDirector.getText().toString());
                pelicula.setAnio(etAnio.getText().toString());

                repositoryPeliculas.actualizarPelicula(pelicula, pelicula.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Refresh();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

    }

    public void Refresh(){
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}