package com.example.anafor.Hp_Hash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class Hp_HashAdapter extends RecyclerView.Adapter<Hp_HashAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<HpDTO> list;


    public Hp_HashAdapter(LayoutInflater inflater, ArrayList<HpDTO> list ) {
        this.inflater = inflater;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;

        itemView = inflater.inflate(R.layout.item_hp_hash, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_hp_hash_top_gamgi.setText(list.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemview;
        TextView tv_hp_hash_top_gamgi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;

            tv_hp_hash_top_gamgi = itemView.findViewById(R.id.tv_hp_hash_top_gamgi);
        }
    }
}
