package com.example.anafor.Nav_Vaccine;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class VaccAdapter extends RecyclerView.Adapter<VaccAdapter.ViewHolder> {
    ArrayList<VaccineDTO> list;
    LayoutInflater inflater;


    // 배너 컬러값 리스트
    String[] colorList = {
            "#a0b2da",
            "#efa830",
            "#bc6e81",
            "#4a5da9",
            "#6d8f47",
            "#ea865b"

    };
    /*ArrayList*/

    public VaccAdapter(ArrayList<VaccineDTO> list, LayoutInflater inflater) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_vaccine, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind( holder,  position);
        holder.vacc_title.setText(list.get(position).getVacc_title());
        holder.vacc_eng.setText(list.get(position).getVacc_eng());
        holder.vacc_content.setText(list.get(position).getVacc_content());
    }

    @Override
    public int getItemCount() {
        return 6;
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

        }



        // 배너 컬러 적용

        public void bind(@NonNull ViewHolder holder, int position) {


          if(position < colorList.length){
                holder.card_back.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorList[position])));

          }

        }
    }
}
