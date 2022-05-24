package com.example.anafor.Hp_List;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anafor.Hp_Hash.hpVO;
import com.example.anafor.R;

import java.util.ArrayList;

public class Hp_ListFragment2 extends Fragment {

    ArrayList<hpVO> list;
    Hp_ListAdapter adapter;

    public Hp_ListFragment2(ArrayList<hpVO> list) {
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hp_list2, container, false);
//==================================================================================================
        RecyclerView recv_select = v.findViewById(R.id.recv_hp_list_hplist);

        // 리사이클러뷰의 진행 방향을 가로로 할 건지 세로로 할 건지 정하는 코드
        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getContext(), RecyclerView.VERTICAL, false);

        // 리사이클러뷰에 표시할 데이터의 리스트를 생성
        /*list = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            list.add(new Hp_ListDTO(R.drawable.seonghyeong,"성형외과" ));
        }

        adapter = new Hp_ListAdapter(inflater, list);*/
        adapter = new Hp_ListAdapter(inflater, list);
        // 리사이클러뷰에 어댑터를 세팅
        recv_select.setAdapter(adapter);
        recv_select.setLayoutManager(manager);

        return v;
//==================================================================================================
    }
}