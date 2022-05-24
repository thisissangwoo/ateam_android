package com.example.anafor.Box_Alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anafor.Hp_Information.Hp_InformationActivity;
import com.example.anafor.Hp_Information.Hp_InformationReviewActivity;
import com.example.anafor.R;

public class Box_Alarm_detailActivity extends AppCompatActivity {

    ImageView imgv_box_detail_back;
    Button btn_box_alarm_location1, btn_box_alarm_location2, btn_box_alarm_location3, btn_box_alarm_location4,
            btn_box_detail_cancel, btn_box_detail_insert;
    EditText edt_box_alarm_content, edt_box_alarm_day, edt_box_alarm_time, edt_box_alarm_minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_alarm_detail);

        imgv_box_detail_back = findViewById(R.id.imgv_box_detail_back);
        btn_box_alarm_location1 = findViewById(R.id.btn_box_alarm_location1);
        btn_box_alarm_location2 = findViewById(R.id.btn_box_alarm_location2);
        btn_box_alarm_location3 = findViewById(R.id.btn_box_alarm_location3);
        btn_box_alarm_location4 = findViewById(R.id.btn_box_alarm_location4);
        btn_box_detail_cancel = findViewById(R.id.btn_box_detail_cancel);
        btn_box_detail_insert = findViewById(R.id.btn_box_detail_insert);
        edt_box_alarm_content = findViewById(R.id.edt_box_alarm_content);
        edt_box_alarm_day = findViewById(R.id.edt_box_alarm_day);
        edt_box_alarm_time = findViewById(R.id.edt_box_alarm_time);
        edt_box_alarm_minute = findViewById(R.id.edt_box_alarm_minute);

        imgv_box_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        btn_box_detail_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        // 알람 저장(insert)을 위한 값이 없을 때 또는 내용 없이 공백일 때 유무의 유효성 검사 후
        // 다시 작성을 해야하는 부분에 포커스 주기
        // 유효성 검사를 거친 후 통과가 되면 등록 되었다는 Toast 를 띄워줌과 동시에
        // 이 전 화면(Box_AlarmActivity)으로 넘어가게끔 intent 처리
        btn_box_detail_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_box_alarm_content.getText().toString().length() == 0 || edt_box_alarm_content.getText().toString().equals(" ")){
                    Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_box_alarm_content.requestFocus();

                }else if(edt_box_alarm_day.getText().toString().length() == 0 || edt_box_alarm_day.getText().toString().equals(" ")){
                    Toast.makeText(getApplicationContext(), "요일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_box_alarm_day.requestFocus();

                }else if(edt_box_alarm_time.getText().toString().length() == 0 || edt_box_alarm_time.getText().toString().equals(" ")){
                    Toast.makeText(getApplicationContext(), "시간을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_box_alarm_time.requestFocus();

                }else if(edt_box_alarm_minute.getText().toString().length() == 0 || edt_box_alarm_minute.getText().toString().equals(" ")){
                    Toast.makeText(getApplicationContext(), "분을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_box_alarm_minute.requestFocus();

                }else if(edt_box_alarm_day.getText().toString().length() == 0 || edt_box_alarm_day.getText().toString().equals(" ")){
                    Toast.makeText(getApplicationContext(), "요일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_box_alarm_day.requestFocus();

                }else{
                    Toast.makeText(getApplicationContext(), "알람이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Box_Alarm_detailActivity.this, Box_AlarmActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}