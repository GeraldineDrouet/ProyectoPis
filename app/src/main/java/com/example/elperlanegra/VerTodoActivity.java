package com.example.elperlanegra;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elperlanegra.adaptadores.VerTodoAdapter;
import com.example.elperlanegra.modelos.VerTodoModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VerTodoActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    RecyclerView verTodorec;
    VerTodoAdapter verTodoAdapter;
    List<VerTodoModel> verTodoModelList;

    ProgressBar progressBar;


    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_todo);

        Toolbar toolbar = findViewById(R.id.tb_vertodo);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.pb_vertodo);
        progressBar.setVisibility(View.VISIBLE);

        firestore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("tipo");
        //String type = String.valueOf(getIntent().getStringArrayListExtra("tipo"));
        verTodorec = findViewById(R.id.vertodo_rec);
        verTodorec.setVisibility(View.GONE);
        verTodorec.setLayoutManager(new LinearLayoutManager(this));

        verTodoModelList = new ArrayList<>();
        verTodoAdapter = new VerTodoAdapter(this, verTodoModelList);
        verTodorec.setAdapter(verTodoAdapter);

        ///POPULAR PRODUCTS////
        /////////Parrilladas//////////////
        if (type.equalsIgnoreCase("parrillada")){
            firestore.collection("AllProducts").whereEqualTo("tipo", "parrillada").get().addOnCompleteListener(task -> {
                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                    verTodoModelList.add(verTodoModel);
                    verTodoAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    verTodorec.setVisibility(View.VISIBLE);
                }
            });
        }

        /////////Hamburguesas//////////////
        if (type.equalsIgnoreCase("hamburguesa")){
            firestore.collection("AllProducts").whereEqualTo("tipo", "hamburguesa").get().addOnCompleteListener(task -> {
                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                    VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                    verTodoModelList.add(verTodoModel);
                    verTodoAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    verTodorec.setVisibility(View.VISIBLE);
                }
            });
        }

        ///////CATEGORÍAS///////
        /////////Bebidas//////////////
        if (type.equalsIgnoreCase("bebida")){
            firestore.collection("AllProducts").whereEqualTo("tipo", "bebida").get().addOnCompleteListener(task -> {
                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                    VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                    verTodoModelList.add(verTodoModel);
                    verTodoAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    verTodorec.setVisibility(View.VISIBLE);
                }
            });
        }

        /////////Mariscos//////////////
        if (type.equalsIgnoreCase("marisco")){
            firestore.collection("AllProducts").whereEqualTo("tipo", "marisco").get().addOnCompleteListener(task -> {
                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                    VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                    verTodoModelList.add(verTodoModel);
                    verTodoAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    verTodorec.setVisibility(View.VISIBLE);
                }
            });
        }

        /////////Mariscos Frescos//////////////
        if (type.equalsIgnoreCase("fresco")){
            firestore.collection("AllProducts").whereEqualTo("tipo", "fresco").get().addOnCompleteListener(task -> {
                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                    VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                    verTodoModelList.add(verTodoModel);
                    verTodoAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    verTodorec.setVisibility(View.VISIBLE);
                }
            });
        }

        /////////Picadas//////////////
        if (type.equalsIgnoreCase("picada")){
            firestore.collection("AllProducts").whereEqualTo("tipo", "picada").get().addOnCompleteListener(task -> {
                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                    VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                    verTodoModelList.add(verTodoModel);
                    verTodoAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    verTodorec.setVisibility(View.VISIBLE);
                }
            });
        }

        ////////DESAYUNOS////////
        /////////Bolones//////////////
        if (type.equalsIgnoreCase("bolon")){
            firestore.collection("AllProducts").whereEqualTo("tipo", "bolon").get().addOnCompleteListener(task -> {
                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                    VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                    verTodoModelList.add(verTodoModel);
                    verTodoAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    verTodorec.setVisibility(View.VISIBLE);
                }
            });
        }

        /////////Muchines//////////////
        if (type.equalsIgnoreCase("muchin")){
            firestore.collection("AllProducts").whereEqualTo("tipo", "muchin").get().addOnCompleteListener(task -> {
                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                    VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                    verTodoModelList.add(verTodoModel);
                    verTodoAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    verTodorec.setVisibility(View.VISIBLE);
                }
            });
        }

        /////////Café//////////////
        if (type.equalsIgnoreCase("cafe")){
            firestore.collection("AllProducts").whereEqualTo("tipo", "cafe").get().addOnCompleteListener(task -> {
                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                    VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                    verTodoModelList.add(verTodoModel);
                    verTodoAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    verTodorec.setVisibility(View.VISIBLE);
                }
            });
        }
    }

}