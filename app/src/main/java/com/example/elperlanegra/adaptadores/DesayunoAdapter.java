package com.example.elperlanegra.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.elperlanegra.R;
import com.example.elperlanegra.VerTodoActivity;
import com.example.elperlanegra.modelos.DesayunoModel;

import java.util.List;

public class DesayunoAdapter extends RecyclerView.Adapter<DesayunoAdapter.ViewHolder> {

    Context context;
    List<DesayunoModel> desayunoModelList;

    public DesayunoAdapter(Context context, List<DesayunoModel> desayunoModelList) {
        this.context = context;
        this.desayunoModelList = desayunoModelList;
    }

    @NonNull
    @Override
    public DesayunoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.desayuno_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DesayunoAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(desayunoModelList.get(position).getImg_url()).into(holder.desayunoImg);
        holder.nombre.setText(desayunoModelList.get(position).getNombre());
        holder.descripcion.setText(desayunoModelList.get(position).getDescripcion());
        holder.rating.setText(desayunoModelList.get(position).getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPP = new Intent(context, VerTodoActivity.class);
                intentPP.putExtra("tipo", desayunoModelList.get(position).getTipo());
                context.startActivity(intentPP);
            }
        });
    }

    @Override
    public int getItemCount() {
        return desayunoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView desayunoImg;
        TextView nombre, descripcion, rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            desayunoImg = itemView.findViewById(R.id.desayuno_img);
            nombre = itemView.findViewById(R.id.nombre_desayuno);
            descripcion = itemView.findViewById(R.id.desayuno_desc);
            rating = itemView.findViewById(R.id.desayuno_rating);
        }
    }
}
