package com.example.anafor.Hp_List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Hp_Hash.HpDTO;
import com.example.anafor.Hp_Information.Hp_InformationActivity;
import com.example.anafor.Hp_Information.Hp_infoDTO;
import com.example.anafor.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Hp_ListAdapter extends RecyclerView.Adapter<Hp_ListAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<Hp_ListDTO> list;
    Context context;
    Hp_ListDTO dto;
    Gson gson = new Gson();

    public ArrayList<Hp_ListDTO> getList() {
        return list;
    }

    public void setList(ArrayList<Hp_ListDTO> list) {
        this.list = list;
    }

    public Hp_ListAdapter (LayoutInflater inflater, ArrayList<Hp_ListDTO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;

        if (list.size() == 0){
            itemView = inflater.inflate(R.layout.item_hp_list_empty, parent, false);
        }else{
            itemView = inflater.inflate(R.layout.item_hp_list, parent, false);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int i) {

        if (list.size() != 0){
            holder.tv_hp_list_name.setText(list.get(i).getHp_name());
            holder.tv_hp_list_addr.setText(list.get(i).getHp_addr());
            holder.tv_hp_list_kategorie.setText(list.get(i).getHp_tel());

            if(i % 3 == 0){
                holder. imgv_hp_list_img.setImageResource(R.drawable.hpimg1);
            }else if(i % 3 == 1){
                holder. imgv_hp_list_img.setImageResource(R.drawable.hpimg2);
            }else if(i % 3 == 2){
                holder. imgv_hp_list_img.setImageResource(R.drawable.hpimg3);
            }

            holder.hp_list_reviewchoic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AskTask task = new AskTask("detail.hp");
                    dto = list.get(i);
                    task.addParam("code", list.get(i).getHp_code());
                    Intent intent = new Intent(context, Hp_InformationActivity.class);
                    Hp_infoDTO infoDTO =  gson.fromJson(CommonMethod.executeAskGet(task),Hp_infoDTO.class);
                    intent.putExtra("infoDTO", infoDTO);
                    Log.d("3333", "onClick: " + infoDTO.getHp_addr());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        if (list.size() == 0){
            return 1;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemview;
        ImageView imgv_hp_list_img;
        TextView tv_hp_list_name, tv_hp_list_addr, tv_hp_list_kategorie;
        LinearLayout hp_list_reviewchoic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;

            if (list.size() != 0){
                hp_list_reviewchoic = itemView.findViewById(R.id.hp_list_reviewchoic);
                tv_hp_list_name = itemview.findViewById(R.id.tv_hp_list_name);
                tv_hp_list_addr = itemview.findViewById(R.id.tv_hp_list_addr);
                tv_hp_list_kategorie = itemview.findViewById(R.id.tv_hp_list_categorie);
                imgv_hp_list_img = itemview.findViewById(R.id.imgv_hp_list_img);
            }
        }
    }
}
