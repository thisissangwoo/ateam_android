package com.example.anafor.Hp_List;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Hp_Hash.hpVO;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.InputStreamReader;
import java.util.ArrayList;

public class Hp_ListAdapter extends RecyclerView.Adapter<Hp_ListAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<hpVO> dtos;

    public ArrayList<hpVO> getList() {
        return dtos;
    }

    public void setList(ArrayList<hpVO> dtos) {
        this.dtos = dtos;
    }

    public Hp_ListAdapter (LayoutInflater inflater, ArrayList<hpVO> dtos) {
        this.inflater = inflater;
        this.dtos = dtos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_hp_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.tv_hp_list_name.setText(dtos.get(i).getHp_name());
        holder.tv_hp_list_addr.setText(dtos.get(i).getHp_addr());
        holder.tv_hp_list_kategorie.setText(dtos.get(i).getHp_tel());


    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemview;
        ImageView imgv_hp_list_img;
        TextView tv_hp_list_name, tv_hp_list_addr, tv_hp_list_kategorie;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;

            tv_hp_list_name = itemview.findViewById(R.id.tv_hp_list_name);
            tv_hp_list_addr = itemview.findViewById(R.id.tv_hp_list_addr);
            tv_hp_list_kategorie = itemview.findViewById(R.id.tv_hp_list_categorie);
            imgv_hp_list_img = itemview.findViewById(R.id.imgv_hp_list_img);
        }

    }




}
