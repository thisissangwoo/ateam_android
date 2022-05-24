package com.example.anafor.Hp_Information;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anafor.R;

public class Hp_InformationReviewActivity extends AppCompatActivity {

    ImageView imgv_hp_infor_review_back;
    Button btn_infor_review_insert;
    EditText edt_infor_review_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_review);

        imgv_hp_infor_review_back = findViewById(R.id.imgv_hp_infor_review_back);
        btn_infor_review_insert = findViewById(R.id.btn_infor_review_insert);
        edt_infor_review_content = findViewById(R.id.edt_infor_review_content);

        imgv_hp_infor_review_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // 리뷰 작성 등록(insert)을 위한 값이 없을 때 또는 내용 없이 공백일 때 유무의 유효성 검사 후
        // 다시 작성을 해야하는 부분에 포커스 주기
        // 유효성 검사를 거친 후 통과가 되면 등록 되었다는 Toast 를 띄워줌과 동시에
        // 이 전 화면(Hp_InformationActivity)으로 넘어가게끔 intent 처리
        btn_infor_review_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_infor_review_content.getText().toString().length() == 0 || edt_infor_review_content.getText().toString().equals(" ")){
                    Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_infor_review_content.requestFocus();
                    return;
                }else{
                    Toast.makeText(getApplicationContext(), "소중한 리뷰 감사합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Hp_InformationReviewActivity.this, Hp_InformationActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}