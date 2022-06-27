package com.example.anafor.Nav_Choice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Hp_Information.Hp_InformationActivity;
import com.example.anafor.Hp_Information.Hp_infoDTO;
import com.example.anafor.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ChoiceAdapter extends RecyclerView.Adapter<ChoiceAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<ChoiceDTO> list;
    Gson gson = new Gson();
    Context context;
    public ChoiceAdapter(LayoutInflater inflater, ArrayList<ChoiceDTO> list ,Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context =context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_my_choice, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(position % 3 == 0){
           holder.imgv_my_choice_img.setImageResource(R.drawable.hpimg1);
        }else if(position % 3 == 1){
            holder.imgv_my_choice_img.setImageResource(R.drawable.hpimg2);
        }else if(position % 3 == 2){
            holder.imgv_my_choice_img.setImageResource(R.drawable.hpimg3);
        }
        holder.tv_my_choice_name.setText(list.get(position).getHp_name());
        holder.tv_my_choice_addr.setText(list.get(position).getHp_addr());
        if(list.get(position).getHp_tel()!=null){
            holder.tv_my_choice_tel.setText(list.get(position).getHp_tel());
        }
        holder.card_mychoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AskTask task = new AskTask("detail.hp");
                    task.addParam("code",list.get(position).getHp_code());
                    Intent intent = new Intent(context, Hp_InformationActivity.class);
                    Hp_infoDTO infoDTO =  gson.fromJson(CommonMethod.executeAskGet(task),Hp_infoDTO.class);
                    intent.putExtra("infoDTO", infoDTO);
                    Log.d("3333", "onClick: "+infoDTO.getHp_addr());
                    context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View itemview;
        ImageView imgv_my_choice_img;
        TextView tv_my_choice_name, tv_my_choice_addr, tv_my_choice_tel;
        CardView card_mychoice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;
            card_mychoice = itemView.findViewById(R.id.card_mychoice);
            imgv_my_choice_img = itemView.findViewById(R.id.imgv_my_choice_img);
            tv_my_choice_name = itemView.findViewById(R.id.tv_my_choice_name);
            tv_my_choice_addr = itemView.findViewById(R.id.tv_my_choice_addr);
            tv_my_choice_tel = itemView.findViewById(R.id.tv_my_choice_tel);
        }
    }
}
