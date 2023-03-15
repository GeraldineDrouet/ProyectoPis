package com.example.elperlanegra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity {

    Button registro;
    EditText nombreApellido, direccion, telefono, correo, contrasena;
    TextView loginEnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreApellido = findViewById(R.id.et_nombreAp);
        direccion = findViewById(R.id.et_direccion);
        telefono = findViewById(R.id.et_telefono);
        correo = findViewById(R.id.et_correo);
        contrasena = findViewById(R.id.et_contrasena);
        registro = findViewById(R.id.buttonReg);
        loginEnRegistro = findViewById(R.id.tv_logReg);

        //ClickListener para el textview que lleva a LoginActivity
        loginEnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
            }
        });

        //ClickListener para el botón que registra
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}