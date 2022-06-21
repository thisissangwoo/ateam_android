package com.example.anafor.Box_Alarm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class Box_AlarmAdapter extends RecyclerView.Adapter<Box_AlarmAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<IoTVO> list;
    Context context;

    public ArrayList<IoTVO> getList() {
        return list;
    }

    public void setList(ArrayList<IoTVO> list) {
        this.list = list;
    }

    public Box_AlarmAdapter(LayoutInflater inflater, ArrayList<IoTVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    public Box_AlarmAdapter(LayoutInflater inflater, ArrayList<IoTVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    public Box_AlarmAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_box_alarm, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_box_alarm_number.setText(list.get(position).getCase_num());
        holder.tv_box_alarm_time.setText(list.get(position).getCase_time());
        holder.tv_box_alarm_memo.setText(list.get(position).getMemo());
        holder.card_box_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Box_Alarm_modifyActivity.class);
                intent.putExtra("vo",  list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_box_alarm_number,tv_box_alarm_time,tv_box_alarm_memo;
        ImageView imgv_box_alarm_pill;
        CardView card_box_alarm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_box_alarm_number = itemView.findViewById(R.id.tv_box_alarm_number);
            this.tv_box_alarm_time = itemView.findViewById(R.id.tv_box_alarm_time);
            this.tv_box_alarm_memo = itemView.findViewById(R.id.tv_box_alarm_memo);
            this.imgv_box_alarm_pill = itemView.findViewById(R.id.imgv_box_alarm_pill);
            this.card_box_alarm = itemView.findViewById(R.id.card_box_alarm);
        }
    }
}
