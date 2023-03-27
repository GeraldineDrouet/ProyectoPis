package com.example.elperlanegra;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.elperlanegra.modelos.VerTodoModel;

import java.util.Objects;

public class DetallesActivity extends AppCompatActivity {

    ImageView detailedImg, addItem, removeItem;
    TextView precio, descripcion, rating;
    Button addTocart;

    Toolbar toolbarDetalles;
    VerTodoModel verTodoModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        toolbarDetalles = findViewById(R.id.tb_detalles);
        setSupportActionBar(toolbarDetalles);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        final Object objeto = getIntent().getSerializableExtra("detalle");
        if (objeto instanceof VerTodoModel){
            verTodoModel = (VerTodoModel) objeto;
        }

        detailedImg = findViewById(R.id.detallesImg);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.quitar_item);

        precio = findViewById(R.id.precio_detalles);
        descripcion = findViewById(R.id.descrip_detalles);
        rating = findViewById(R.id.rating_detalles);

        if (verTodoModel != null){
            Glide.with(getApplicationContext()).load(verTodoModel.getImg_url()).into(detailedImg);
            rating.setText(verTodoModel.getRating());
            descripcion.setText(verTodoModel.getDescripcion());
            precio.setText("Precio: $"+ (double) verTodoModel.getPrecio() + "");
        }

        addTocart = findViewById(R.id.addtocart_bt);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}