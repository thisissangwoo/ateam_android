package com.example.anafor.Pill_Main;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Nav_Schedule.ScheduleActivity;
import com.example.anafor.Nav_Schedule.ScheduleFragment2;
import com.example.anafor.R;
import com.example.anafor.pill_detail.Pill_detailActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Pill_MainAdapter extends RecyclerView.Adapter<Pill_MainAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<Pill_MainDTO> list;
    Context context;
    Pill_MainDTO dto;
    Pill_MainFragment fragment;

    public Pill_MainAdapter(LayoutInflater inflater, ArrayList<Pill_MainDTO> list, Context context, Pill_MainFragment fragment) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    public Pill_MainAdapter(LayoutInflater inflater, ArrayList<Pill_MainDTO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    public ArrayList<Pill_MainDTO> getList() {
        return list;
    }

    public void setList(ArrayList<Pill_MainDTO> list) {
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_pill_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_pill_main_name.setText(list.get( position).getHp_name());
        holder.tv_pill_main_date.setText(list.get( position).getPill_date());

        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Pill_detailActivity.class);
                intent.putExtra("dto", list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemview;
        ImageView imgv_pill_main_img;
        TextView tv_pill_main_name,tv_pill_main_date;
        LinearLayout item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;
            this.imgv_pill_main_img = itemView.findViewById(R.id.imgv_pill_main_img);
            this.tv_pill_main_name = itemView.findViewById(R.id.tv_pill_main_name);
            this.tv_pill_main_date = itemView.findViewById(R.id.tv_pill_main_date);

            itemview.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    int position = getAdapterPosition();
                    //아이템 삭제 처리`
                    if (position != RecyclerView.NO_POSITION){

                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("원하시는 항목을 선택해주세요.");
                        builder.setSingleChoiceItems(new String[]{"삭제"}, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // setSingleChoiceItems 2개인 각각의 아이템 중
                                // 0 번째 == 수정, 1 번째 == 삭제
                                if (which == 0){
                                    Gson gson = new Gson();
                                    AskTask task = new AskTask("/pill_delete");
                                    dto = list.get(position);
                                    task.addParam("dto", gson.toJson(dto));
                                    CommonMethod.executeAskGet(task);

                                    fragment.selectList();
                                    Toast.makeText(context.getApplicationContext(), "해당 처방전이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        }).show();
                    }
                    return false;
                }
            });

        }

    }



}
