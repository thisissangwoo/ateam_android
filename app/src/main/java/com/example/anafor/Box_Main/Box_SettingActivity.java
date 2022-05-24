package com.example.anafor.Box_Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.anafor.R;

public class Box_SettingActivity extends AppCompatActivity {

    ImageView imgv_box_setting_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_setting);

        imgv_box_setting_back = findViewById(R.id.imgv_box_setting_back);

        imgv_box_setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });
    }
}