package com.example.elperlanegra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText correoLog, contrasenaLog;
    TextView registroEnLog;
    FirebaseAuth auth;
    ProgressBar pb_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        pb_log = findViewById(R.id.pb_log);
        pb_log.setVisibility(View.GONE);

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
                loginUser();
            }
        });
    }

    private void loginUser() {

        String userEmailLog = correoLog.getText().toString();
        String userPassLog = contrasenaLog.getText().toString();

        //IF por si algún campo está vacío
        if (TextUtils.isEmpty(userEmailLog)){
            Toast.makeText(this, "¡¡Campo [Correo electrónico] está vacío!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPassLog)){
            Toast.makeText(this, "¡¡Campo [Contraseña] está vacío!!", Toast.LENGTH_SHORT).show();
            return;
        }

        //IF por si la contraseña tiene menos de 6 dígitos/letras
        if (userPassLog.length() < 6){
            Toast.makeText(this, "¡¡La contraseña debe contener 6 dígitos/letras o más!!", Toast.LENGTH_SHORT).show();
            return;
        }

        //LOGIN USER
        auth.signInWithEmailAndPassword(userEmailLog, userPassLog).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    pb_log.setVisibility(View.VISIBLE);
                    Toast.makeText(LoginActivity.this, "¡INICIO DE SESIÓN EXITOSO!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    pb_log.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "ERROR: " + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}