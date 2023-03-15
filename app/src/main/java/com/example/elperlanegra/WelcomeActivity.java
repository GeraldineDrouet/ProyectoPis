package com.example.elperlanegra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void registrarse(View view) {
        startActivity(new Intent(WelcomeActivity.this, RegistroActivity.class));
    }

    public void loginWelcome(View view) {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
    }
}