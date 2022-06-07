package com.example.anafor.Box_Alarm;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class Box_AlarmAdapter extends RecyclerView.Adapter<Box_AlarmAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<Box_AlarmDTO> list;

    public Box_AlarmAdapter(LayoutInflater inflater, ArrayList<Box_AlarmDTO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_box_alarm, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_box_alarm_title.setText(list.get(position).getMemo());
        holder.tv_box_alarm_location.setText(list.get(position).getCase_num1());
        holder.tv_box_alarm_date.setText(list.get(position).getCase_date1());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemview;
        TextView tv_box_alarm_title, tv_box_alarm_location, tv_box_alarm_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;

            tv_box_alarm_title = itemview.findViewById(R.id.tv_box_alarm_title);
            tv_box_alarm_location = itemview.findViewById(R.id.tv_box_alarm_location);
            tv_box_alarm_date = itemview.findViewById(R.id.tv_box_alarm_date);
            int count, checked;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("원하시는 항목을 선택해주세요.");
                        builder.setSingleChoiceItems(new String[]{"수정", "삭제"}, 2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // setSingleChoiceItems 2개인 각각의 아이템 중
                                // 0 번째 == 수정, 1 번째 == 삭제
                                if (which == 0){

                                    dialog.dismiss();
                                }else if (which == 1){

                                    dialog.dismiss();
                                }
                            }
                        }).show();
                    }
                    return;
                }
            });
        }
    }
}
