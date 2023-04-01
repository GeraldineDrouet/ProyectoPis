package com.example.elperlanegra.ui.carrito;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
import com.example.elperlanegra.modelos.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CarritoFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    DatabaseReference dbUsuarios;

    RecyclerView rv_carrito;
    CarritoAdapter carritoAdapter;
    List<CarritoModel> carritoModelList;
    TextView overTotalAmount;
    UserModel modelUser;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_carrito, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        progressBar = root.findViewById(R.id.pb_carrito);
        progressBar.setVisibility(View.VISIBLE);

        /*currentUser = auth.getCurrentUser();
        dbUsuarios = FirebaseDatabase.getInstance().getReference().child("Users");*/

        rv_carrito = root.findViewById(R.id.rv_carrito);
        rv_carrito.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_carrito.setVisibility(View.GONE);

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
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                CarritoModel carritoModel = documentSnapshot.toObject(CarritoModel.class);
                                carritoModelList.add(carritoModel);
                                carritoAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                rv_carrito.setVisibility(View.VISIBLE);
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
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            overTotalAmount.setText("TOTAL A PAGAR: $" + nf.format(totalBill));
        }
    };


}