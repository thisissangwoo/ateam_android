package com.example.anafor.Nav_Choice;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ChoiceActivity extends AppCompatActivity {

    TextView tv_heart;
    ImageView imgv_hp_choice_back;
    RecyclerView recv_my_choice_list;
    ArrayList<ChoiceDTO> list = new ArrayList<>();
    ChoiceAdapter adapter;
    Gson gson = new Gson();
    Context context;

    //무조건 로그인했을때만 사용가능함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        context = this;
        imgv_hp_choice_back = findViewById(R.id.imgv_hp_choice_back);
        tv_heart = findViewById(R.id.tv_heart);
        recv_my_choice_list = findViewById(R.id.recv_my_choice_list);  //리사이클러뷰
        imgv_hp_choice_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });
        selectList();

    }

    //찜한 병원 조회
    public void selectList(){
        AskTask task = new AskTask("select.heart");
        task.addParam("user_id",CommonVal.loginInfo.getUser_id());
        list = gson.fromJson(CommonMethod.executeAskGet(task),new TypeToken<ArrayList<ChoiceDTO>>(){}.getType());
        if(list.size()==0){
            tv_heart.setVisibility(View.VISIBLE);
            recv_my_choice_list.setVisibility(View.INVISIBLE);
        }else{
            adapter = new ChoiceAdapter(getLayoutInflater(), list,context);
            recv_my_choice_list.setAdapter(adapter);
            recv_my_choice_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectList();
    }
}