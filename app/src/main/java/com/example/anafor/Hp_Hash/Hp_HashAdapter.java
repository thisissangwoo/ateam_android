package com.example.anafor.Hp_Hash;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.Hp_List.Hp_ListActivity;
import com.example.anafor.R;

import java.util.ArrayList;

public class Hp_HashAdapter extends RecyclerView.Adapter<Hp_HashAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<HpDTO> list;
    Context context;

    public Hp_HashAdapter(LayoutInflater inflater, ArrayList<HpDTO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;

        itemView = inflater.inflate(R.layout.item_hp_hash, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder,position);

        //holder.tv_hp_hash_top_gamgi.setText(list.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemview;
        TextView tv_hp_hash_top;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;
            tv_hp_hash_top = itemView.findViewById(R.id.tv_hp_hash_top);


        }
        public void bind(@NonNull ViewHolder holder, int position){
            tv_hp_hash_top.setText(list.get(position).getText());
           // holder.tv_hp_hash_top_gamgi.setText(list.get(position).getText());
        /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //아이템 클릭시 이동
                    Log.d("TAG", "onClick: " + list.get());
                }
            });*/
            holder.tv_hp_hash_top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "onClick: " + list.get(position).getText());

                    Intent intent = new Intent(context , Hp_ListActivity.class);
                    intent.putExtra("query", list.get(position).getText());
                    intent.putExtra("text", list.get(position).getName());


                    context.startActivity(intent);
                    //db에서 처리 하면됨

                }
            });
        }
    }
}
