package com.example.anafor.Box_Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anafor.R;

public class Box_SettingActivity extends AppCompatActivity {

    ImageView imgv_box_setting_back;
    TextView tv_iotmap;                     //웹뷰로 이동할 텍스트뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_setting);
        tv_iotmap = findViewById(R.id.tv_iotmap);
        tv_iotmap.setOnClickListener(new View.OnClickListener() {   //클릭시 웹뷰로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Box_SettingActivity.this, WebMapActivity.class);
                startActivity(intent);
            }
        });

        imgv_box_setting_back = findViewById(R.id.imgv_box_setting_back);

        imgv_box_setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });
    }
}