package com.example.anafor.Box_Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.anafor.R;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Box_SettingActivity extends AppCompatActivity {

    ImageView imgv_box_setting_back;
    TextView tv_iotmap;                     //웹뷰로 이동할 텍스트뷰
    Switch iot_bluetooth, iot_sound;




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
        iot_bluetooth = findViewById(R.id.iot_bluetooth);
        iot_sound = findViewById(R.id.iot_sound);

        imgv_box_setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });


        iot_sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        iot_bluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

    }// oncreat()





}