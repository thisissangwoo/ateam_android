package com.example.anafor.Hp_Main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import com.example.anafor.Hp_List.Hp_ListActivity;
import com.example.anafor.Hp_Map.Hp_MapActivity;
import com.example.anafor.R;

import java.util.ArrayList;

public class Hp_MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_hp_main, container, false);
        GridView gridView = v.findViewById(R.id.grid_hp_main_kategorie);

        //DTO , XML
        // fragment_self_grid <= 여기 안에 listview가 없음 find를 통해서 찾아도 null gridview.!
        ArrayList<Hp_MainDTO> list = new ArrayList<>();

        list.add(new Hp_MainDTO(R.drawable.naegwa , "내과"));
        list.add(new Hp_MainDTO(R.drawable.pibugwa , "피부과"));
        list.add(new Hp_MainDTO(R.drawable.sanbu , "산부인과"));
        list.add(new Hp_MainDTO(R.drawable.gajeong , "가정의학과"));
        list.add(new Hp_MainDTO(R.drawable.ibiinhugwa , "이비인후과"));
        list.add(new Hp_MainDTO(R.drawable.binyogigwa , "비뇨기과"));
        list.add(new Hp_MainDTO(R.drawable.soa , "소아청소년과"));
        list.add(new Hp_MainDTO(R.drawable.junghyeong , "정형외과"));
        list.add(new Hp_MainDTO(R.drawable.chigwa , "치과"));
        list.add(new Hp_MainDTO(R.drawable.seonghyeong , "성형외과"));
        list.add(new Hp_MainDTO(R.drawable.angwa , "안과"));
        list.add(new Hp_MainDTO(R.drawable.jungsin , "정신건강의학과"));
        list.add(new Hp_MainDTO(R.drawable.oegwa , "외과"));
        list.add(new Hp_MainDTO(R.drawable.singyeongoegwa , "신경외과"));
        list.add(new Hp_MainDTO(R.drawable.singyeonggwa , "신경과"));
        list.add(new Hp_MainDTO(R.drawable.machwitongjeung , "마취통증의학과"));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), Hp_MapActivity.class);
                intent.putExtra("subject",list.get(position).getText());
                startActivity(intent);
            }
        });

        Hp_MainAdapter adapter = new Hp_MainAdapter(list ,inflater);
        gridView.setAdapter(adapter);

        return v;
    }
}