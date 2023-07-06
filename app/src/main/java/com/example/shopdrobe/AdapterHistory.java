package com.example.shopdrobe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.MyViewHolder> {
    private Context context;
    private ArrayList id, name, price, size;

    public AdapterHistory(Context context, ArrayList id, ArrayList name, ArrayList price, ArrayList size) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
    }

    @NonNull
    @Override
    public AdapterHistory.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.historybanner, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistory.MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf((id.get(position))));
        holder.name.setText(String.valueOf((name.get(position))));
        holder.price.setText(String.valueOf((price.get(position))));
        holder.size.setText(String.valueOf((size.get(position))));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, price, size;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textId);
            name = itemView.findViewById(R.id.textName);
            size = itemView.findViewById(R.id.textSize);
            price = itemView.findViewById(R.id.textPrice);
        }
    }

}
