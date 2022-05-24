package com.example.anafor.Pill_Main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.Pill_QRcode.Pill_QRcodeActivity;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Pill_MainFragment extends Fragment {

    ArrayList<Pill_MainDTO> list;
    Pill_MainAdapter adapter;
    TextView tv_pill_main_date,tv_pill_main_camera;
    RecyclerView recv_select;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pill_main, container, false);

        recv_select = v.findViewById(R.id.recv_select);
        tv_pill_main_date = v.findViewById(R.id.tv_pill_main_date);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getContext(), RecyclerView.VERTICAL, false);


        tv_pill_main_camera = v.findViewById(R.id.tv_pill_main_camera);
        tv_pill_main_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Pill_QRcodeActivity.class);
                startActivity(intent);

            }
        });



        /*list = new ArrayList<>();
        list.add(new Pill_MainDTO(0, 0, 0, 0, "", "", ""));
*/
        /*list.add(new Pill_MainDTO("A 조 병원", "2022.05.17"));
        list.add(new Pill_MainDTO("B 조 병원", "2022.06.17"));
        list.add(new Pill_MainDTO("C 조 병원", "2022.07.17"));*/

        /*AskTask task = new AskTask("/select");
        Gson gson = new Gson();
        list = gson.fromJson(CommonMethod.executeAskGet(task), new TypeToken<List<Pill_MainDTO>>(){}.getType());


        adapter = new Pill_MainAdapter(inflater, list);*/
        //selectList(CommonVal.loginInfo.getUser_id());
        selectList(CommonVal.loginInfo.getUser_id());
        Log.d("TAG", "onCreateView: " + list.get(0).getHp_code());
        adapter = new Pill_MainAdapter(inflater, list, getContext());
        recv_select.setAdapter(adapter);
        recv_select.setLayoutManager(manager);

        return v;

    }

    public ArrayList<Pill_MainDTO> selectList(String query){
        Gson gson = new Gson();
        AskTask task = new AskTask("/select");
        task.addParam("select", query);
        InputStreamReader ir = CommonMethod.executeAskGet(task);
         list = null;
        if(ir != null){
            list = gson.fromJson(ir,
                    new TypeToken< List<Pill_MainDTO> >(){}.getType());
        }else{
            Log.d("TAG", "selectList: ");
        }
        return list;
    }



    //새로고침 로직
    //여기서 리스트 추가하는 로직 작성
    @Override
    public void onResume() {
        super.onResume();
        selectList(CommonVal.loginInfo.getUser_id());


    }
}