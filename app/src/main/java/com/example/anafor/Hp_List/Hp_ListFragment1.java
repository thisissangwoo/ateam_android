package com.example.anafor.Hp_List;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Hp_Hash.hpVO;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class Hp_ListFragment1 extends Fragment {

    ArrayList<hpVO> list;
    Hp_ListAdapter adapter;
    String query;

    public Hp_ListFragment1(String query) {
        this.query = query;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hp_list1, container, false);
//==================================================================================================
        RecyclerView recv_select = v.findViewById(R.id.recv_hp_list_hplist);

        // 리사이클러뷰의 진행 방향을 가로로 할 건지 세로로 할 건지 정하는 코드
        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getContext(), RecyclerView.VERTICAL, false);

        selectList(query);
        adapter = new Hp_ListAdapter(inflater, list, getActivity());
        // 리사이클러뷰에 어댑터를 세팅
        recv_select.setAdapter(adapter);
        recv_select.setLayoutManager(manager);

        return v;
//==================================================================================================
    }
    public void selectList(String query){

        try{
            Gson gson = new Gson();
            AskTask task = new AskTask("hashreview");
            task.addParam("query", query);
            InputStreamReader isr =  CommonMethod.executeAskGet(task);
            list = gson.fromJson(isr, new TypeToken<ArrayList<hpVO>>(){}.getType());
            for(hpVO vo : list){
                Log.d("@@@@", "onQueryTextSubmit: " + vo.getHp_name());
            }
            Log.d("hash", "onQueryTextSubmit: " + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}