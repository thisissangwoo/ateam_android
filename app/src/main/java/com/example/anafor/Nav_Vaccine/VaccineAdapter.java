package com.example.anafor.Nav_Vaccine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.Hp_List.Hp_ListActivity;
import com.example.anafor.Nav_Schedule.ScheduleActivity;
import com.example.anafor.Nav_Schedule.ScheduleFragment3;
import com.example.anafor.R;
import com.example.anafor.pill_detail.Pill_detailActivity;

import java.util.ArrayList;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.ViewHolder> {

    ArrayList<VaccineDTO> list;
    LayoutInflater inflater;
    VaccineDTO dto;
    Context context;
    String vaccine;

    // 배너 컬러값 리스트
    String[] colorList = {
            "#a0b2da",
            "#efa830",
            "#bc6e81",
            "#4a5da9",
            "#ea865b"

    };
    /*ArrayList*/


    public VaccineAdapter(ArrayList<VaccineDTO> list, LayoutInflater inflater, Context context, String vaccine) {
        this.list = list;
        this.inflater = inflater;
        this.context = context;
        this.vaccine = vaccine;
    }



    //    public VaccineAdapter(ArrayList<VaccineDTO> list, LayoutInflater inflater) {
//        this.inflater = inflater;
//        this.list = list;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_vaccine, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind( holder,  position);
        holder.vacc_title.setText(list.get(position).getVacc_title());
        holder.vacc_eng.setText(list.get(position).getVacc_eng());
        holder.vacc_content.setText(list.get(position).getVacc_content());

        holder.card_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VaccineDetailActivity.class);
                intent.putExtra("1", position);
                context.startActivity(intent);

                    //풋엑스트라 첫번째가 백신 0번
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView card_back;
        TextView vacc_title, vacc_eng, vacc_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card_back  = itemView.findViewById(R.id.card_back);
            vacc_title = itemView.findViewById(R.id.vacc_title);
            vacc_eng = itemView.findViewById(R.id.vacc_eng);
            vacc_content = itemView.findViewById(R.id.vacc_content);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //풋엑스트라 첫번째가 백신 0번

                }
            });
        }

        // 배너 컬러 적용
        public void bind(@NonNull ViewHolder holder, int position) {

          if(position < colorList.length){
                holder.card_back.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorList[position])));

          }
        }

    }
}
