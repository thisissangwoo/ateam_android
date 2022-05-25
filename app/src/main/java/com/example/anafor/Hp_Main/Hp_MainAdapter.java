package com.example.anafor.Hp_Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anafor.R;

import java.util.ArrayList;

public class Hp_MainAdapter extends BaseAdapter {

    // 화면 붙일 LayoutInflater 하나,
    // getCount 와 내용을 동적으로 보여줄 ArrayList
    ArrayList<Hp_MainDTO> list;
    LayoutInflater inflater ;

    public Hp_MainAdapter(ArrayList<Hp_MainDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.item_hp_main, parent , false);
        ImageView imgv_grid = convertView.findViewById(R.id.imgv_hp_main_kategorie);
        TextView tv_filename = convertView.findViewById(R.id.tv_hp_main_name);
        tv_filename.setText(list.get(position).getText());
        imgv_grid.setImageResource(list.get(position).getUrl());

        return convertView;
    }
}
