package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.Credentials;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button btnLogin;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("com.exaple.practica19102022k.MyApp", MODE_PRIVATE);

        if(sharedPreferences.getString("AUTHORIZATION", null) != null){
            redirectUserActivity();
        }
        else {
            username = findViewById(R.id.etUsername);
            password = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);

            SetUpLoginButton();
        }


    }

    private void SetUpLoginButton(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String credentials = Credentials.basic(username.getText().toString(), password.getText().toString());

                SharedPreferences.Editor sharedEditor = sharedPreferences.edit();
                sharedEditor.putString("AUTHORIZACION", credentials);
                sharedEditor.apply();

                redirectUserActivity();
            }
        });
    }
    private void redirectUserActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }


}