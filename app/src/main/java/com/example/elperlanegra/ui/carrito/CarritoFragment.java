package com.example.elperlanegra.ui.carrito;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elperlanegra.R;
import com.example.elperlanegra.adaptadores.CarritoAdapter;
import com.example.elperlanegra.modelos.CarritoModel;
import com.example.elperlanegra.modelos.CategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CarritoFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView rv_carrito;
    CarritoAdapter carritoAdapter;
    List<CarritoModel> carritoModelList;
    TextView overTotalAmount;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_carrito, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        rv_carrito = root.findViewById(R.id.rv_carrito);
        rv_carrito.setLayoutManager(new LinearLayoutManager(getActivity()));

        overTotalAmount = root.findViewById(R.id.totalamount);

        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mMessageReceiver, new IntentFilter("MiPrecioTotal"));

        carritoModelList = new ArrayList<>();
        carritoAdapter = new CarritoAdapter(getActivity(), carritoModelList);
        rv_carrito.setAdapter(carritoAdapter);

        db.collection("Carrito").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                                CarritoModel carritoModel = documentSnapshot.toObject(CarritoModel.class);
                                carritoModelList.add(carritoModel);
                                carritoAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        return root;
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            double totalBill = intent.getDoubleExtra("montoTotal", 0.00);
            overTotalAmount.setText("TOTAL A PAGAR: $" + totalBill);
        }
    };


}