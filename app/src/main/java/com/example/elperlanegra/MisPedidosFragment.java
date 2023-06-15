package com.example.elperlanegra;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;

public class MisPedidosFragment extends Fragment {

    RecyclerView rv_order;

    ///////////VARIOS//////////
    ConstraintLayout empty;
    ProgressBar progressBar;

    public MisPedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mis_pedidos, container, false);

        ///RECYCLERVIEW////////
        rv_order = root.findViewById(R.id.rv_order);
        rv_order.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_order.setVisibility(View.GONE);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference currentUserRef = db.collection("CurrentUser").document(userId);
        DocumentReference pedidosRef = currentUserRef.collection("CurrentUser").document("Pedidos");

        pedidosRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e(TAG, "Error al obtener los pedidos: " + e.getMessage());
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    // Los datos de los pedidos existen, puedes acceder a ellos utilizando documentSnapshot.getData()
                    Map<String, Object> pedidosData = documentSnapshot.getData();

                    // Aquí puedes realizar las operaciones necesarias para mostrar los datos en tu fragmento
                    // Por ejemplo, puedes actualizar una lista o un RecyclerView con los datos de los pedidos
                }
            }
        });



        empty = root.findViewById(R.id.constraint3);


        // Aquí llamarás al método que obtiene los pedidos desde Firebase y los muestra en el RecyclerView


        return root;
    }
}