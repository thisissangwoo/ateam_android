package com.example.anafor.Hp_Review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.anafor.R;

import java.util.ArrayList;

public class Hp_ReviewAllActivity extends AppCompatActivity {
    ArrayList<ReviewVO> reviewList;
    RecyclerView recv_review_list;

    ImageView imgv_myreview_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp_review_all);
        imgv_myreview_back = findViewById(R.id.imgv_myreview_back);

        imgv_myreview_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });
        Intent intent = getIntent();
        reviewList = (ArrayList<ReviewVO>) intent.getSerializableExtra("reviewList");
        recv_review_list = findViewById(R.id.recv_review_list);
        ReviewAllAdapter adapter = new ReviewAllAdapter(reviewList,getLayoutInflater());
        recv_review_list.setAdapter(adapter);
        recv_review_list.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }
}