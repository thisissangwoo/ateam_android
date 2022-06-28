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
import com.example.anafor.Hp_Hash.HpDTO;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class Hp_ListFragment extends Fragment {

    ArrayList<Hp_ListDTO> list;
    Hp_ListAdapter adapter;
    String query;
    String hash = null;


    public Hp_ListFragment(String query, String hash) {
        this.query = query;
        this.hash = hash;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hp_list, container, false);
//==================================================================================================
        RecyclerView recv_select = v.findViewById(R.id.recv_hp_list_hplist);

        // 리사이클러뷰의 진행 방향을 가로로 할 건지 세로로 할 건지 정하는 코드
        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getContext(), RecyclerView.VERTICAL, false);

        // 리사이클러뷰에 표시할 데이터의 리스트를 생성
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
            AskTask task = new AskTask("basic");
            Log.d("aaa", "selectList: " + hash);

            if ( hash == null){
                task.addParam("query", query);
            }else{
                task.addParam("query", hash);
            }
            InputStreamReader isr =  CommonMethod.executeAskGet(task);
            list = gson.fromJson(isr, new TypeToken<ArrayList<Hp_ListDTO>>(){}.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}