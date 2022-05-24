package com.example.anafor.Hp_Information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.anafor.R;
import com.google.android.material.tabs.TabLayout;

public class Hp_InformationActivity extends AppCompatActivity {

    TabLayout hp_infor_tab_layout;
    ImageView imgv_hp_infor_back;
    TextView hp_infor_time, hp_infor_infor, hp_infor_review, tv_hp_today,tv_hp_todayTime, tv_hp_todayLunchTime, hp_info_name, hp_info_addr , tv_hp_intro;
    ScrollView hp_infor_scview;
    Button review_edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp_information);

        Intent intent = getIntent();
        Hp_infoDTO infoDTO = (Hp_infoDTO) intent.getSerializableExtra("infoDTO"); //DTO 값 저장

        hp_infor_scview = findViewById(R.id.hp_infor_scview);

        review_edt = findViewById(R.id.review_edt);
        hp_infor_time = findViewById(R.id.hp_infor_time);               //진료시간
        hp_infor_infor = findViewById(R.id.hp_infor_infor);             //병원정보
        hp_infor_review = findViewById(R.id.hp_infor_review);       //병원 리뷰

        hp_info_name = findViewById(R.id.hp_info_name);             //병원 이름
        hp_info_addr = findViewById(R.id.hp_info_addr);                 //병원 주소

        tv_hp_today = findViewById(R.id.tv_hp_today);                       // 오늘 요일
        tv_hp_todayTime = findViewById(R.id.tv_hp_todayTime);       //오늘 진료시간
        tv_hp_todayLunchTime =findViewById(R.id.tv_hp_todayLunchTime); // 오늘 점심시간

        tv_hp_intro = findViewById(R.id.tv_hp_intro);       //병원 소개

        hp_info_name.setText(infoDTO.getHp_name());
        hp_info_addr.setText(infoDTO.getHp_addr());

        hp_infor_tab_layout = findViewById(R.id.hp_infor_tab_layout);
        imgv_hp_infor_back = findViewById(R.id.imgv_hp_infor_back);

        imgv_hp_infor_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        hp_infor_tab_layout = findViewById(R.id.hp_infor_tab_layout);
        hp_infor_tab_layout.addTab(hp_infor_tab_layout.newTab().setText("진료시간").setId(0));
        hp_infor_tab_layout.addTab(hp_infor_tab_layout.newTab().setText("병원정보").setId(1));
        hp_infor_tab_layout.addTab(hp_infor_tab_layout.newTab().setText("리뷰").setId(2));

        //탭 클릭시 해당하는 위치로 이동
        hp_infor_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                if(position == 0){
                    hp_infor_scview.smoothScrollTo(0, hp_infor_time.getTop());
                }else if (position == 1){
                    hp_infor_scview.smoothScrollTo(0, hp_infor_infor.getTop());
                }else if (position == 2){
                    hp_infor_scview.smoothScrollTo(0, hp_infor_review.getTop());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //리뷰 등록 버튼 클릭시 리뷰 작성액티비티로 이동
        review_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hp_InformationActivity.this, Hp_InformationReviewActivity.class);

                startActivity(intent);
            }
        });
    }
}