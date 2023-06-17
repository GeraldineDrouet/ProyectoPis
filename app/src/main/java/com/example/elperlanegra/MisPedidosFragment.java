package com.example.elperlanegra;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elperlanegra.adaptadores.PedidoAdapter;
import com.example.elperlanegra.modelos.PedidoModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MisPedidosFragment extends Fragment {

    RecyclerView rv_order;
    PedidoAdapter pedidoAdapter;
    List<PedidoModel> pedidoModelList;

    //////////FIREBASE//////
    FirebaseFirestore firestore;

    ///////////VARIOS//////////
    ConstraintLayout empty;
    ConstraintLayout nonEmpty;
    ProgressBar progressBar;

    public MisPedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mis_pedidos, container, false);

        ///////PROGRESS BAR
        progressBar = root.findViewById(R.id.pb_pedidos);

        ///RECYCLERVIEW////////
        rv_order = root.findViewById(R.id.rv_order);
        rv_order.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_order.setVisibility(View.GONE);

        pedidoModelList = new ArrayList<>();
        pedidoAdapter = new PedidoAdapter(pedidoModelList);
        rv_order.setAdapter(pedidoAdapter);

        empty = root.findViewById(R.id.constraint3);
        nonEmpty = root.findViewById(R.id.constraint4);

        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Pedidos");
        firestore = FirebaseFirestore.getInstance();

        firestore.collection("Pedidos")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Orden")
                .addSnapshotListener((querySnapshot, e) -> {
                    if (e != null) {
                        // Ha ocurrido un error al obtener los datos de Firestore
                        Log.e("MisPedidosFragment", "Error al obtener los pedidos", e);
                        return;
                    }

                    pedidoModelList.clear();
                    double montoTotal = 0.0;
                    if (querySnapshot != null){
                        Map<String, PedidoModel> pedidoMap = new HashMap<>();

                        for (DocumentSnapshot document : querySnapshot.getDocuments()){
                            PedidoModel pedido = document.toObject(PedidoModel.class);
                            pedido.setEstado("PAGADO");
                            String idPedido = pedido.getIdPedido();
                            pedidoMap.put(idPedido, pedido);

                        }

                        pedidoModelList.addAll(pedidoMap.values());
                        pedidoAdapter.notifyDataSetChanged();
                    }


                    //pedidoAdapter.notifyDataSetChanged();

                    if (pedidoModelList.isEmpty()) {
                        empty.setVisibility(View.VISIBLE);
                        nonEmpty.setVisibility(View.GONE);
                        rv_order.setVisibility(View.GONE);
                    } else {
                        empty.setVisibility(View.GONE);
                        nonEmpty.setVisibility(View.VISIBLE);
                        rv_order.setVisibility(View.VISIBLE);
                    }

                    progressBar.setVisibility(View.GONE);
                });

        return root;
    }

}