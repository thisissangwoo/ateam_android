package com.example.anafor.Box_Alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anafor.R;

import java.util.ArrayList;

public class Box_AlarmActivity extends AppCompatActivity {

    ImageView imgv_box_alarm_back;
    TextView tv_box_alarm_insert;
    RecyclerView recv_box_alarm;
    ArrayList<Box_AlarmDTO> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_alarm);

        tv_box_alarm_insert = findViewById(R.id.tv_box_alarm_insert);
        imgv_box_alarm_back = findViewById(R.id.imgv_box_alarm_back);

        tv_box_alarm_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Box_AlarmActivity.this, Box_Alarm_detailActivity.class);
                startActivity(intent);
            }
        });

        imgv_box_alarm_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        recv_box_alarm = findViewById(R.id.recv_box_alarm);
        list.add(new Box_AlarmDTO(R.drawable.chigwa, "오후 3시 치통약 먹기",
                "3 번 칸에 약 보관 중", "15 : 00 PM"));

        Box_AlarmAdapter adapter = new Box_AlarmAdapter(getLayoutInflater(), list);

        recv_box_alarm.setAdapter(adapter);
        recv_box_alarm.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


    }
}