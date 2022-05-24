package com.example.anafor.Box_Recode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.anafor.R;

import java.util.ArrayList;

public class Box_RecordActivity extends AppCompatActivity {

    ImageView imgv_box_record_back;
    RecyclerView recv_box_record;
    ArrayList<Box_RecordDTO> list = new ArrayList<>();
    private Object Context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_record);

        imgv_box_record_back = findViewById(R.id.imgv_box_record_back);

        imgv_box_record_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        recv_box_record = findViewById(R.id.recv_box_record);
        list.add(new Box_RecordDTO("오후 3시에 타이레놀 복용하기", "타이레놀 복용 완료",
                "15 : 00 PM"));

        list.add(new Box_RecordDTO("오후 4시에 이가탄탄 복용하기", "이가탄탄 복용 완료",
                "16 : 00 PM"));

        list.add(new Box_RecordDTO("오후 5시에 게보린 복용하기", "게보린 복용 완료",
                "17 : 00 PM"));

        list.add(new Box_RecordDTO("오후 6시에 감기약 복용하기", "감기약 복용 완료",
                "18 : 00 PM"));

        list.add(new Box_RecordDTO("오후 7시에 비염약 복용하기", "비염약 복용 완료",
                "19 : 00 PM"));

        Box_RecordAdapter adapter = new Box_RecordAdapter(getLayoutInflater(), list);

        recv_box_record.setAdapter(adapter);
        recv_box_record.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
}