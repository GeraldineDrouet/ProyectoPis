package com.example.elperlanegra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elperlanegra.modelos.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    Button registro;
    EditText nombreApellido, direccion, telefono, correo, contrasena;
    TextView loginEnRegistro;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

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

        //ClickListener para el botón que registra con el método que crea el usuario
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUser();
            }
        });
    }

    //Método que crea el usuario
    private void createUser() {
        String userName = nombreApellido.getText().toString();
        String userAddress = direccion.getText().toString();
        String userPhone = telefono.getText().toString();
        String userEmail = correo.getText().toString();
        String userPass = contrasena.getText().toString();

        //IF por si algún campo está vacío
        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this, "¡¡Campo [Nombre y Apellido] está vacío!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userAddress)){
            Toast.makeText(this, "¡¡Campo [Dirección] está vacío!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPhone)){
            Toast.makeText(this, "¡¡Campo [Teléfono] está vacío!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "¡¡Campo [Correo electrónico] está vacío!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPass)){
            Toast.makeText(this, "¡¡Campo [Contraseña] está vacío!!", Toast.LENGTH_SHORT).show();
            return;
        }

        //IF por si la contraseña tiene menos de 6 dígitos/letras
        if (userPass.length() < 6){
            Toast.makeText(this, "¡¡La contraseña debe contener 6 dígitos/letras o más!!", Toast.LENGTH_SHORT).show();
            return;
        }

        //Creando/Guardando el usuario en la base de datos
        auth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    UserModel userModel = new UserModel(userName,userAddress,userPhone,userEmail,userPass);
                    String id = task.getResult().getUser().getUid();
                    database.getReference().child("Users").child(id).setValue(userModel);

                    Toast.makeText(RegistroActivity.this, "¡REGISTRO EXITOSO!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(RegistroActivity.this, "ERROR: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}