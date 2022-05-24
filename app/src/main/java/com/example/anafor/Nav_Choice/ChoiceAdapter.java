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
//        holder.imgv_my_choice_img.setImageResource(list.get(position).getImg_url());
        holder.tv_my_choice_name.setText(list.get(position).getHp_name());
        holder.tv_my_choice_addr.setText(list.get(position).getHp_addr());
      //  holder.tv_my_choice_category.setText(list.get(position).getCategory());
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
        TextView tv_my_choice_name, tv_my_choice_addr, tv_my_choice_category;
        CardView card_mychoice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;
            card_mychoice = itemView.findViewById(R.id.card_mychoice);
            imgv_my_choice_img = itemView.findViewById(R.id.imgv_my_choice_img);
            tv_my_choice_name = itemView.findViewById(R.id.tv_my_choice_name);
            tv_my_choice_addr = itemView.findViewById(R.id.tv_my_choice_addr);
            tv_my_choice_category = itemView.findViewById(R.id.tv_my_choice_category);

        /*    itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        AskTask task = new AskTask("detail.hp");
                        task.addParam("code",list.get(position).getHp_code());
                        Intent intent = new Intent(context, Hp_InformationActivity.class);
                        Hp_infoDTO infoDTO =  gson.fromJson(CommonMethod.executeAskGet(task),Hp_infoDTO.class);
                        intent.putExtra("infoDTO", infoDTO);
                        Log.d("3333", "onClick: "+infoDTO.getHp_addr());
                        context.startActivity(intent);
                    }
                }
            });*/
        }
    }
}
