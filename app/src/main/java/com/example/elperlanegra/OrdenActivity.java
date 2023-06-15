package com.example.elperlanegra;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.elperlanegra.modelos.CarritoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdenActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    Toolbar toolbarOrden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);

        toolbarOrden = findViewById(R.id.tb_orden);
        setSupportActionBar(toolbarOrden);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        List<CarritoModel> list = (ArrayList<CarritoModel>) getIntent().getSerializableExtra("itemList");

        if (list != null &&list.size() > 0){

            for (CarritoModel model : list) {
                final HashMap<String, Object> cartMap = new HashMap<>();

                cartMap.put("nombre", model.getNombre());
                cartMap.put("precio", model.getPrecio());
                cartMap.put("fecha", model.getFecha());
                cartMap.put("hora", model.getHora());
                cartMap.put("cantidad", model.getCantidad());
                cartMap.put("precioTotal", model.getPrecioTotal());
                cartMap.put("img", model.getImg());

                firestore.collection("Pedidos").document(auth.getCurrentUser().getUid())
                        .collection("Orden").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(OrdenActivity.this, "PEDIDO REALIZADO CON ÉXITO", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
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