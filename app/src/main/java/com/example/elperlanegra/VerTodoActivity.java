package com.example.elperlanegra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.elperlanegra.adaptadores.VerTodoAdapter;
import com.example.elperlanegra.modelos.VerTodoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.cert.CertPathBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VerTodoActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    RecyclerView verTodorec;
    VerTodoAdapter verTodoAdapter;
    List<VerTodoModel> verTodoModelList;


    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_todo);

        Toolbar toolbar = findViewById(R.id.tb_vertodo);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();


        String type = getIntent().getStringExtra("tipo");
        //String type = String.valueOf(getIntent().getStringArrayListExtra("tipo"));
        verTodorec = findViewById(R.id.vertodo_rec);
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
                }
            });
        }

    }

}