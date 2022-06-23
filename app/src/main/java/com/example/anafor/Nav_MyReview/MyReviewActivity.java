package com.example.anafor.Nav_MyReview;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.Hp_Review.ReviewVO;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyReviewActivity extends AppCompatActivity {

    ImageView imgv_myreview_back;
    RecyclerView recv_my_review_list;
    ArrayList<ReviewVO> list = null;
    Gson gson = new Gson();
    Context context;
    MyReviewAdapter adapter;
    TextView tv_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreview);
        context = this;
        imgv_myreview_back = findViewById(R.id.imgv_myreview_back);
        recv_my_review_list = findViewById(R.id.recv_my_review_list);
        tv_review = findViewById(R.id.tv_review);
        imgv_myreview_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });
        selectList();
    }

    //리뷰 조회
    public void selectList(){
        AskTask task = new AskTask("selectMy.review");
        task.addParam("user_id",CommonVal.loginInfo.getUser_id());
        list = gson.fromJson(CommonMethod.executeAskGet(task),new TypeToken<ArrayList<ReviewVO>>(){}.getType());
        if(list.size()==0){
            tv_review.setVisibility(View.VISIBLE);
            recv_my_review_list.setVisibility(View.INVISIBLE);
        }else{
            tv_review.setVisibility(View.GONE);
            adapter = new MyReviewAdapter(getLayoutInflater(), list,context,MyReviewActivity.this);
            recv_my_review_list.setAdapter(adapter);
            recv_my_review_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectList();
    }
}