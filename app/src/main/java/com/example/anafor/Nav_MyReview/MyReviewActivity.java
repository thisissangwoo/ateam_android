package com.example.anafor.Nav_MyReview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class MyReviewActivity extends AppCompatActivity {

    ImageView imgv_myreview_back;
    RecyclerView recv_my_review_list;
    ArrayList<MyReviewDTO> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreview);

        imgv_myreview_back = findViewById(R.id.imgv_myreview_back);

        imgv_myreview_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        recv_my_review_list = findViewById(R.id.recv_my_review_list);
        list.add(new MyReviewDTO("김상우 님", "2022.05.16", "A조 내과",
                "감기 몸살 기운이 있어서 내원 했는데\n다행히 코로나는 아니네요 !\n그리고 의사분도 매우 친절 하셨습니다.",
                "● 증상을 쉽게 설명해 주셨어요.", "● 꼼꼼하게 진단해 주셨어요.", "● 친절하게 알려 주셨어요."));

        list.add(new MyReviewDTO("신보배 님", "2022.05.17", "A조 이비인후과",
                "비염이 있어서 내원 했는데\n꼼꼼히 진료를 해주신 덕분에 금방 완치했어요 !\n그리고 의사분도 매우 친절 하셨습니다.",
                "● 증상을 쉽게 설명해 주셨어요.", "● 꼼꼼하게 진단해 주셨어요.", "● 친절하게 알려 주셨어요."));

        list.add(new MyReviewDTO("이정민 님", "2022.05.16", "A조 내과",
                "감기 몸살 기운이 있어서 내원 했는데\n다행히 코로나는 아니네요 !\n그리고 의사분도 매우 친절 하셨습니다.",
                "● 증상을 쉽게 설명해 주셨어요.", "● 꼼꼼하게 진단해 주셨어요.", "● 친절하게 알려 주셨어요."));

        list.add(new MyReviewDTO("탁은영 님", "2022.05.16", "A조 내과",
                "감기 몸살 기운이 있어서 내원 했는데\n다행히 코로나는 아니네요 !\n그리고 의사분도 매우 친절 하셨습니다.",
                "● 증상을 쉽게 설명해 주셨어요.", "● 꼼꼼하게 진단해 주셨어요.", "● 친절하게 알려 주셨어요."));

        list.add(new MyReviewDTO("박천일 님", "2022.05.16", "A조 내과",
                "감기 몸살 기운이 있어서 내원 했는데\n다행히 코로나는 아니네요 !\n그리고 의사분도 매우 친절 하셨습니다.",
                "● 증상을 쉽게 설명해 주셨어요.", "● 꼼꼼하게 진단해 주셨어요.", "● 친절하게 알려 주셨어요."));

        MyReviewAdapter adapter = new MyReviewAdapter(getLayoutInflater(), list);

        recv_my_review_list.setAdapter(adapter);
        recv_my_review_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }
}