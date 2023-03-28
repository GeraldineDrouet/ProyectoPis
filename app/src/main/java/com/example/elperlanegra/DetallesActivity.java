package com.example.elperlanegra;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.elperlanegra.modelos.VerTodoModel;

import java.util.Objects;

public class DetallesActivity extends AppCompatActivity {

    ImageView detailedImg, addItem, removeItem;
    TextView nombre, precio, descripcion, rating, cantidad;
    Button addTocart;

    int cantTotal = 1;
    double totalPrice = 0;

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
        cantidad = findViewById(R.id.number_item);

        precio = findViewById(R.id.precio_detalles);
        descripcion = findViewById(R.id.descrip_detalles);
        rating = findViewById(R.id.rating_detalles);
        nombre = findViewById(R.id.nombre_detalle);

        if (verTodoModel != null){
            Glide.with(getApplicationContext()).load(verTodoModel.getImg_url()).into(detailedImg);
            nombre.setText(verTodoModel.getNombre());
            rating.setText(verTodoModel.getRating());
            descripcion.setText(verTodoModel.getDescripcion());
            precio.setText("Precio: $"+ (double) verTodoModel.getPrecio() + "");

            totalPrice = verTodoModel.getPrecio() * cantTotal;
        }

        addTocart = findViewById(R.id.addtocart_bt);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cantTotal < 10){
                    cantTotal++;
                    cantidad.setText(String.valueOf(cantTotal));
                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cantTotal > 1){
                    cantTotal--;
                    cantidad.setText(String.valueOf(cantTotal));
                }
            }
        });
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