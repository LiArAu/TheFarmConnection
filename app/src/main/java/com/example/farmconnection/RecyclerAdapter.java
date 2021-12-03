package com.example.farmconnection;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProductHolder> {

    private List<Product> products = new ArrayList<>();
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public Product getProducts(int position){
        return products.get(position);
    }

    public void setProducts(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(Product product);
    }

    class ProductHolder extends RecyclerView.ViewHolder{
        TextView viewtitle, viewdescrip, viewprovider, viewprice;
        ImageView viewimage;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            viewtitle = itemView.findViewById(R.id.plantname);
            viewprovider = itemView.findViewById(R.id.plantprovider);
            viewprice = itemView.findViewById(R.id.plantprice);
            viewimage = itemView.findViewById(R.id.plantimage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(products.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.oneplant, parent, false);
        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product p = products.get(position);
        holder.viewtitle.setText(p.getTitle());
        holder.viewprovider.setText("Provider: "+p.getProvider());
        holder.viewprice.setText("Price: $"+p.getPrice());
        if (p.getImage_id() != -1){
            holder.viewimage.setImageResource(p.getImage_id());
        }
        else{
            holder.viewimage.setImageBitmap(
                    BitmapFactory.decodeByteArray(p.getImage()
                            ,0,p.getImage().length));
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

}
