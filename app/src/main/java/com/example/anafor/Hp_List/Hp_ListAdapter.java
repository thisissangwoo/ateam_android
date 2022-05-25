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
import com.example.anafor.Hp_Hash.hpVO;
import com.example.anafor.Hp_Information.Hp_InformationActivity;
import com.example.anafor.Hp_Information.Hp_infoDTO;
import com.example.anafor.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Hp_ListAdapter extends RecyclerView.Adapter<Hp_ListAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<hpVO> dto;
    LinearLayout hp_list_reviewchoic;
    Context context;
    Gson gson = new Gson();
    
    public ArrayList<hpVO> getList() {
        return dto;
    }

    public void setList(ArrayList<hpVO> dto) {
        this.dto = dto;
    }

    public Hp_ListAdapter (LayoutInflater inflater, ArrayList<hpVO> dto, Context context) {
        this.inflater = inflater;
        this.dto = dto;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;

        if (dto.size() == 0){
            itemView = inflater.inflate(R.layout.item_hp_list_empty, parent, false);
        }else{
            itemView = inflater.inflate(R.layout.item_hp_list, parent, false);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int i) {

        if (dto.size() != 0){
            holder.tv_hp_list_name.setText(dto.get(i).getHp_name());
            holder.tv_hp_list_addr.setText(dto.get(i).getHp_addr());
            holder.tv_hp_list_kategorie.setText(dto.get(i).getHp_tel());
        }

        hp_list_reviewchoic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskTask task = new AskTask("detail.hp");
                task.addParam("code",dto.get(i).getHp_code());

                Intent intent = new Intent(context, Hp_InformationActivity.class);
                Hp_infoDTO infoDTO =  gson.fromJson(CommonMethod.executeAskGet(task),Hp_infoDTO.class);
                intent.putExtra("infoDTO", infoDTO);
                Log.d("3333", "onClick: " + infoDTO.getHp_addr());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (dto.size() == 0){
            return 1;
        }
        return dto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemview;
        ImageView imgv_hp_list_img;
        TextView tv_hp_list_name, tv_hp_list_addr, tv_hp_list_kategorie;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;

            if (dto.size() != 0){
                tv_hp_list_name = itemview.findViewById(R.id.tv_hp_list_name);
                tv_hp_list_addr = itemview.findViewById(R.id.tv_hp_list_addr);
                tv_hp_list_kategorie = itemview.findViewById(R.id.tv_hp_list_categorie);
                imgv_hp_list_img = itemview.findViewById(R.id.imgv_hp_list_img);
            }
        }
    }
}
