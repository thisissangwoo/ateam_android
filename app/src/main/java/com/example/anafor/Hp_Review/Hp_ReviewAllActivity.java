package com.example.anafor.Hp_Review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.anafor.R;

import java.util.ArrayList;

public class Hp_ReviewAllActivity extends AppCompatActivity {
    ArrayList<ReviewVO> reviewList;
    RecyclerView recv_review_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp_review_all);
        Intent intent = getIntent();
        reviewList = (ArrayList<ReviewVO>) intent.getSerializableExtra("reviewList");
        recv_review_list = findViewById(R.id.recv_review_list);
        ReviewAllAdapter adapter = new ReviewAllAdapter(reviewList,getLayoutInflater());
        recv_review_list.setAdapter(adapter);
        recv_review_list.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }
}