package com.example.anafor.pill_detail;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anafor.R;

import java.util.ArrayList;

public class Pill_detailAdapter extends RecyclerView.Adapter<Pill_detailAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<Pill_detailDTO> list;
    Pill_detailActivity context;

    public Pill_detailAdapter(LayoutInflater inflater, ArrayList<Pill_detailDTO> list, Pill_detailActivity context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemview = inflater.inflate(R.layout.item_pill_detail, parent, false);

        return new ViewHolder(itemview);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_pill_detail_name.setText(list.get(position).getDrug_name());
        holder.tv_pill_detail_cp.setText(list.get(position).getDrug_cp());
        holder.tv_pill_detail_efcy.setText(Html.fromHtml(list.get(position).getDrug_efcy()));
        holder.tv_pill_detail_use.setText(Html.fromHtml(list.get(position).getDrug_use()));
        holder.tv_pill_detail_se.setText(Html.fromHtml(list.get(position).getDrug_se()));
        if(! list.get(position).getDrug_img() .equals("null")){
            Glide.with(context).load(list.get(position).getDrug_img()).into(holder.imgv_pill_detail_img);
        }else{
            holder.imgv_pill_detail_img.setImageResource(R.drawable.pill_noimg);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemview;
        ImageView imgv_pill_detail_img;
        TextView tv_pill_detail_name, tv_pill_detail_cp, tv_pill_detail_efcy, tv_pill_detail_use, tv_pill_detail_se;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;
            imgv_pill_detail_img = itemView.findViewById(R.id.imgv_pill_detail_img);
            tv_pill_detail_name = itemView.findViewById(R.id.tv_pill_detail_name);
            tv_pill_detail_cp = itemView.findViewById(R.id.tv_pill_detail_cp);
            tv_pill_detail_efcy = itemView.findViewById(R.id.tv_pill_detail_efcy);
            tv_pill_detail_use = itemView.findViewById(R.id.tv_pill_detail_use);
            tv_pill_detail_se = itemView.findViewById(R.id.tv_pill_detail_se);

        }
    }
}
