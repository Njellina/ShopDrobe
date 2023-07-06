package com.example.shopdrobe;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.MyViewHolder> {
    private Context context;
    private ArrayList name, price, size;

    public AdapterCart(Context context, ArrayList name, ArrayList price, ArrayList size) {
        this.context = context;
        this.name = name;
        this.price = price;
        this.size = size;
    }

    @NonNull
    @Override
    public AdapterCart.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cartbanner, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.MyViewHolder holder, int position) {
        holder.name.setText(String.valueOf((name.get(position))));
        holder.price.setText(String.valueOf((price.get(position))));
        holder.size.setText(String.valueOf((size.get(position))));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, size;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textName);
            size = itemView.findViewById(R.id.textSize);
            price = itemView.findViewById(R.id.textPrice);
        }
    }

}
