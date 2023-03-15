package com.example.elperlanegra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText correoLog, contrasenaLog;
    TextView registroEnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correoLog = findViewById(R.id.et_correoLog);
        contrasenaLog = findViewById(R.id.et_contrasenaLog);
        login = findViewById(R.id.buttonLog);
        registroEnLog =  findViewById(R.id.tv_regLog);

        //ClickListener para el textview que lleva a RegistroActivity
        registroEnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
            }
        });

        //ClickListener para el botón que inicia sesión
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}