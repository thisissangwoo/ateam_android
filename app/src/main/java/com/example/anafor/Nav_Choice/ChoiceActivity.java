package com.example.anafor.Nav_Choice;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class ChoiceActivity extends AppCompatActivity {

    ImageView imgv_hp_choice_back;
    RecyclerView recv_my_choice_list;
    ArrayList<ChoiceDTO> list = new ArrayList<>();
    //    TextView tv_choice_edit;
    ChoiceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        imgv_hp_choice_back = findViewById(R.id.imgv_hp_choice_back);

        imgv_hp_choice_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        recv_my_choice_list = findViewById(R.id.recv_my_choice_list);
        list.add(new ChoiceDTO(R.drawable.junghyeong, "전남대 병원", "전남로 93번길", "정형외과", "목요일 10:00 ~ 20:00"));
        list.add(new ChoiceDTO(R.drawable.angwa, "서울대 병원", "서울 93번길", "안과", "수요일 10:00 ~ 20:00"));
        list.add(new ChoiceDTO(R.drawable.jungsin, "성모 병원", "성모 93번길", "정신과", "금요일 10:00 ~ 20:00"));

        adapter = new ChoiceAdapter(getLayoutInflater(), list , ChoiceActivity.this);

        recv_my_choice_list.setAdapter(adapter);
        recv_my_choice_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

//        tv_choice_edit = findViewById(R.id.tv_choice_edit);
//        tv_choice_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // xml 에서 android:tag="N" boolean 참 거짓으로 다 사용 가능
//                // 그래서 기본 값은 N 으로 돼있고 click 했을 때 보여주고 감추고 처리
//                if(tv_choice_edit.getTag().toString().equals("N")){
//                    tv_choice_edit.setTag("Y");
//                    click(View.VISIBLE);
//                    adapter.setVisibleChk(true);
//                }else{
//                    tv_choice_edit.setTag("N");
//                    click(View.GONE);
//                    adapter.setVisibleChk(false);
//                }
//            }
//        });
    }

    // 메소드를 활용하여 사용하려고 만들었음
    public void click(int visible){
        for(int i = 0 ; i < recv_my_choice_list.getChildCount(); i++){
            recv_my_choice_list.getChildAt(i).findViewById(R.id.ckb_choice).setVisibility(visible);
            // CheckBox chk =   recv_my_choice_list.getChildAt(i).findViewById(R.id.ckb_choice);
            // chk.setChecked(true);
        }
    }

    public void delete(){
        //1.삭제 처리
        //2.데이터 조회
        list.clear();
        list.add(new ChoiceDTO(R.drawable.junghyeong, "전남대 병원", "전남로 93번길", "정형외과", "목요일 10:00 ~ 20:00"));
        list.add(new ChoiceDTO(R.drawable.angwa, "서울대 병원", "서울 93번길", "안과", "수요일 10:00 ~ 20:00"));
        adapter.notifyDataSetChanged();
    }

}