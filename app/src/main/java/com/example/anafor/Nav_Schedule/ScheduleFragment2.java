package com.example.anafor.Nav_Schedule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;

import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment2 extends Fragment {

    private static final String TAG = "";
    ScheduleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule2, container, false);
//==================================================================================================
        RecyclerView recv_schedule = v.findViewById(R.id.recv_schedule);

        // 리사이클러뷰의 진행 방향을 가로로 할 건지 세로로 할 건지 정하는 코드
        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getContext(), RecyclerView.VERTICAL, false);

        // 리사이클러뷰에 보여줄 데이터의 리스트를 생성
        // 프래그먼트에서 어싱크로 내가 보낼 where 절에 쓸 조건을 보내면
        // 스프링에서 조회를 하면 selectList 로 옴
        Gson gson = new Gson();
        AskTask task = new AskTask("/schedule_select");
        task.addParam("select", "admin");
        ArrayList<ScheduleDTO> list = gson.fromJson(CommonMethod.executeAskGet(task),
                new TypeToken<List<ScheduleDTO>>(){}.getType());

        adapter = new ScheduleAdapter(inflater, list, getActivity());

        // 리사이클러뷰에 어댑터를 세팅
        recv_schedule.setAdapter(adapter);
        recv_schedule.setLayoutManager(manager);

        return v;
//==================================================================================================
    }
}