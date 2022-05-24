package com.example.anafor.pill_detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.Pill_Main.Pill_MainAdapter;
import com.example.anafor.R;

import java.util.ArrayList;

public class Pill_detailAdapter extends RecyclerView.Adapter<Pill_detailAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<Pill_detailDTO> list;
    Context context;

    public Pill_detailAdapter(LayoutInflater inflater, ArrayList<Pill_detailDTO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_pill_detail,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemview;
        ImageView drug_img;
        TextView drug_name,drug_cp,drug_efcy, drug_use,drug_se;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;

            drug_img = itemView.findViewById(R.id.drug_img);
            drug_name = itemView.findViewById(R.id.drug_name);
            drug_cp = itemView.findViewById(R.id.drug_cp);
            drug_efcy = itemView.findViewById(R.id.drug_efcy);
            drug_use = itemView.findViewById(R.id.drug_use);
            drug_se = itemView.findViewById(R.id.drug_se);

        }
    }
}
