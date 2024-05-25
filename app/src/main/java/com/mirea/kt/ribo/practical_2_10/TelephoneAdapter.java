package com.mirea.kt.ribo.practical_2_10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TelephoneAdapter extends RecyclerView.Adapter<TelephoneAdapter.ViewHolder> {

    private ArrayList<Telephone> telephones;

    public TelephoneAdapter(ArrayList<Telephone> telephones) {
        this.telephones = telephones;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView modelView;
        private final TextView priceView;

        ViewHolder(View view) {
            super(view);
            modelView = view.findViewById(R.id.modelTelephone);
            priceView = view.findViewById(R.id.priceTelephone);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_telephone,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TelephoneAdapter.ViewHolder holder, int position) {
        Telephone telephone = telephones.get(position);
        holder.modelView.setText(String.format("%s %s", telephone.getModel(), telephone.getSerialNumber()));
        holder.priceView.setText(String.format("%d рублей", telephone.getPrice()));
    }

    @Override
    public int getItemCount() {
        return telephones.size();
    }
}
