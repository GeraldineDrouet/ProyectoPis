package com.example.elperlanegra.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elperlanegra.R;
import com.example.elperlanegra.modelos.PedidoModel;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>{

    List<PedidoModel> pedidoModelList;

    public PedidoAdapter(List<PedidoModel> pedidoModelList) {
        this.pedidoModelList = pedidoModelList;
    }

    @NonNull
    @Override
    public PedidoAdapter.PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PedidoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pedidos_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoAdapter.PedidoViewHolder holder, int position) {

        holder.id.setText(pedidoModelList.get(position).getIdPedido());
        holder.estado.setText(pedidoModelList.get(position).getEstado());
        holder.fecha.setText(pedidoModelList.get(position).getFecha());
        holder.monto.setText(pedidoModelList.get(position).getMontoTotal());
    }

    @Override
    public int getItemCount() {
        return pedidoModelList.size();
    }

    public class PedidoViewHolder extends RecyclerView.ViewHolder {

        TextView id, fecha, estado, monto;
        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.pedido_id);
            fecha = itemView.findViewById(R.id.pedido_fecha);
            estado = itemView.findViewById(R.id.pedido_estado);
            monto = itemView.findViewById(R.id.pedido_monto);
        }
    }
}
