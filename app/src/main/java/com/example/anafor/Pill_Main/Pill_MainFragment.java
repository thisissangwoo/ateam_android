package com.example.anafor.Pill_Main;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.MainActivity;

import com.example.anafor.R;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.ArrayList;


public class Pill_MainFragment extends Fragment {

    ArrayList<Pill_MainDTO> list;
    Pill_MainAdapter adapter;
    TextView tv_pill_main_date, tv_pill_main_camera, tv_pill_main_none;
    RecyclerView recv_select;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pill_main, container, false);

        list = new ArrayList<>();
        recv_select = v.findViewById(R.id.recv_select);
        tv_pill_main_date = v.findViewById(R.id.tv_pill_main_date);
        tv_pill_main_none = v.findViewById(R.id.tv_pill_main_none);


        tv_pill_main_camera = v.findViewById(R.id.tv_pill_main_camera);
        tv_pill_main_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.showQr();
               // Intent intent = new Intent(getContext(), Pill_QRcodeActivity.class);
               // startActivity(intent);

            }
        });
        onResume();
        return v;
    }

    public void selectList() {
        Gson gson = new Gson();
        AskTask task = new AskTask("/select");
        task.addParam("user_id", CommonVal.loginInfo.getUser_id());


        list = gson.fromJson(CommonMethod.executeAskGet(task), new TypeToken<ArrayList<Pill_MainDTO>>() {
        }.getType());
        if (list.size() == 0) {
            tv_pill_main_none.setVisibility(View.VISIBLE);
            recv_select.setVisibility(View.GONE);
        } else {
            adapter = new Pill_MainAdapter(getLayoutInflater(), list, getContext(), this);
            recv_select.setAdapter(adapter);
            recv_select.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        }
    }

    //새로고침 로직00
    //여기서 리스트 추가하는 로직 작성
    @Override
    public void onResume() {
        super.onResume();
        selectList();

    }
}
