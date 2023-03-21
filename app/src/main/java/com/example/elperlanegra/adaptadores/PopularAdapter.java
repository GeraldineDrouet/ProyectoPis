package com.example.elperlanegra.adaptadores;

import android.annotation.SuppressLint;
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
import com.example.elperlanegra.modelos.PopularModel;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelList;

    public PopularAdapter(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.popImg);
        holder.nombre.setText(popularModelList.get(position).getNombre());
        holder.descripcion.setText(popularModelList.get(position).getDescripcion());
        holder.rating.setText(popularModelList.get(position).getRating());
        holder.promo.setText(popularModelList.get(position).getPromo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPP = new Intent(context, VerTodoActivity.class);
                intentPP.putExtra("tipo", popularModelList.get(position).getTipo());
                context.startActivity(intentPP);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView popImg;
        TextView nombre, descripcion, rating, promo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_img);
            nombre = itemView.findViewById(R.id.pop_name);
            descripcion = itemView.findViewById(R.id.pop_desc);
            rating = itemView.findViewById(R.id.pop_rating);
            promo = itemView.findViewById(R.id.pop_promo);
        }
    }
}
