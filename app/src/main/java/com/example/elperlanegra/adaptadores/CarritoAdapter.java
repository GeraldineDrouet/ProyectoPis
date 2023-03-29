package com.example.elperlanegra.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.elperlanegra.R;
import com.example.elperlanegra.modelos.CarritoModel;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    Context context;
    List<CarritoModel> carritoModelList;

    public CarritoAdapter(Context context, List<CarritoModel> carritoModelList) {
        this.context = context;
        this.carritoModelList = carritoModelList;
    }

    @NonNull
    @Override
    public CarritoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.carrito_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(carritoModelList.get(position).getImg()).into(holder.carritoProd);

        holder.nombre.setText(carritoModelList.get(position).getNombre());
        holder.precio.setText(carritoModelList.get(position).getPrecio());
        holder.fecha.setText(carritoModelList.get(position).getFecha());
        holder.hora.setText(carritoModelList.get(position).getHora());
        holder.cantidad.setText(carritoModelList.get(position).getCantidad());
        holder.precioTotal.setText(carritoModelList.get(position).getPrecioTotal() + "");
    }

    @Override
    public int getItemCount() {
        return carritoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, precio, fecha, hora, cantidad, precioTotal;
        ImageView carritoProd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre_prodcarrito);
            precio = itemView.findViewById(R.id.precio_prodcarrito);
            fecha = itemView.findViewById(R.id.fecha_prodcarrito);
            hora = itemView.findViewById(R.id.hora_prodcarrito);
            cantidad = itemView.findViewById(R.id.cant_prodcarrito);
            precioTotal = itemView.findViewById(R.id.total_prodcarrito);
            carritoProd = itemView.findViewById(R.id.carritoImag);
        }
    }
}
