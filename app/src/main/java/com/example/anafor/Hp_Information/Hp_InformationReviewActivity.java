package com.example.anafor.Hp_Information;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.Hp_Review.ReviewVO;
import com.example.anafor.R;
import com.example.anafor.utils.GetDate;
import com.google.gson.Gson;

public class Hp_InformationReviewActivity extends AppCompatActivity {

    ImageView imgv_hp_infor_review_back;
    Button btn_infor_review_insert;
    EditText edt_infor_review_content;
    String hp_name, hp_code;
    TextView tv_hp_name;
    RatingBar reviewRating;         //별점

    CheckBox chk_type1, chk_type2, chk_type3;

    ReviewVO rvo;               //리뷰 저장 VO

    Gson gson = new Gson();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_review);

        rvo = new ReviewVO();
        Intent  intent =getIntent();
        hp_name = intent.getStringExtra("hp_name");   //병원 이름
        hp_code = intent.getStringExtra("hp_code");       //병원 조회 위한 병원 코드

        imgv_hp_infor_review_back = findViewById(R.id.imgv_hp_infor_review_back);
        btn_infor_review_insert = findViewById(R.id.btn_infor_review_insert);
        edt_infor_review_content = findViewById(R.id.edt_infor_review_content);
        tv_hp_name = findViewById(R.id.tv_hp_name);
        reviewRating = findViewById(R.id.reviewRating);
        chk_type1 = findViewById(R.id.chk_type1);
        chk_type2 = findViewById(R.id.chk_type2);
        chk_type3 = findViewById(R.id.chk_type3);


        tv_hp_name.setText(hp_name);        //병원 이름

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
                }else if(reviewRating.getRating()==0){  //별점을 매기지 않았을때
                        Toast.makeText(Hp_InformationReviewActivity.this, "별점을 입력해주세요", Toast.LENGTH_SHORT).show();
                }else{                          //유효성 통과했을 경우 리뷰 등록
                    Toast.makeText(getApplicationContext(), "소중한 리뷰 감사합니다.", Toast.LENGTH_SHORT).show();
                    AskTask task = new AskTask("insert.review");
                    rvo.setHp_code(hp_code);
                    GetDate getDate = new GetDate();
                    rvo.setUser_id(CommonVal.loginInfo.getUser_id());
                    rvo.setRev_date(getDate.getCurrentDate());
                    rvo.setRev_text1(0);
                    rvo.setRev_text2(0);
                    rvo.setRev_text3(0);
                    rvo.setRev_text4(edt_infor_review_content.getText().toString());
                    rvo.setRev_grade(reviewRating.getRating());
                    cbxclick();
                    task.addParam("vo",gson.toJson(rvo));
                    CommonMethod.executeAskGet(task);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

    public void cbxclick(){
        if(chk_type1.isChecked()==true){
            rvo.setRev_text1(1);
        }
        if(chk_type2.isChecked()==true){
            rvo.setRev_text2(1);
        }
        if(chk_type3.isChecked()==true){
            rvo.setRev_text3(1);
        }
    }


}