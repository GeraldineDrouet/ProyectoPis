package com.example.elperlanegra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MisPedidosFragment extends Fragment {

    RecyclerView rv_order;

    ConstraintLayout empty;

    public MisPedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mis_pedidos, container, false);

        rv_order = root.findViewById(R.id.rv_order);
        rv_order.setLayoutManager(new LinearLayoutManager(getActivity()));


        rv_order.setVisibility(View.GONE);

        empty = root.findViewById(R.id.constraint3);


        // Aquí llamarás al método que obtiene los pedidos desde Firebase y los muestra en el RecyclerView

        return root;
    }
}