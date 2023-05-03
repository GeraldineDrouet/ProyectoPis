package com.example.elperlanegra;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class AcercaActivity extends AppCompatActivity {

    ImageView banner;
    TextView titulo1, descripcion, titulo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);

        /////////////FLECHA HACIA ATRAS
        Toolbar toolbar = findViewById(R.id.tb_acerca);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /*banner = findViewById(R.id.imageViewbanner);
        titulo1 = findViewById(R.id.titulo1);
        descripcion = findViewById(R.id.descripcion);
        titulo2 = findViewById(R.id.titulo2);*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}