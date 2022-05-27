package com.example.anafor.Hp_Review;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.anafor.Hp_Review.ReviewVO;
import com.example.anafor.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Hp_infoReviewFragment extends Fragment {
    TextView tv_user_name, tv_review_date, tv_review_content, tv_review_survey1, tv_review_survey2, tv_review_survey3;
    ArrayList<ReviewVO> reviewList;
    RatingBar reviewRating;

    public Hp_infoReviewFragment(ArrayList<ReviewVO> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(reviewList != null && reviewList.size()!=0){
            View v =  inflater.inflate(R.layout.fragment_hp_info_review, container, false);
            tv_user_name = v.findViewById(R.id.tv_user_name);                     //유저 이름
            tv_review_date = v.findViewById(R.id.tv_review_date);                 //작성 날짜
            tv_review_content = v.findViewById(R.id.tv_review_content);       // 리뷰 내용
            tv_review_survey1 = v.findViewById(R.id.tv_review_survey1) ;      // 병원이 깨끗했어요
            tv_review_survey2 = v.findViewById(R.id.tv_review_survey2);       //친절하게 진단해주셨어요
            tv_review_survey3 = v.findViewById(R.id.tv_review_survey3);       //부대시설이 만족스러웠어요
            reviewRating = v.findViewById(R.id.reviewRating);                           //리뷰 별점

            reviewRating.setIsIndicator(false);                                     //사용자가 별점 수정 못함
            reviewRating.setRating((float) reviewList.get(0).getRev_grade());
            tv_user_name.setText(reviewList.get(0).getUser_name() +"님");
            tv_review_content.setText(reviewList.get(0).getRev_text4());
            tv_review_date.setText(reviewList.get(0).getRev_date());
            if(reviewList.get(0).getRev_text1()==0){
                tv_review_survey1.setVisibility(View.GONE);
            }
            if(reviewList.get(0).getRev_text2()==0){
                tv_review_survey2.setVisibility(View.GONE);
            }
            if(reviewList.get(0).getRev_text3()==0){
                tv_review_survey3.setVisibility(View.GONE);
            }
            return v;
        }else{
            View v = inflater.inflate(R.layout.item_non_review,container,false);
            return v;
        }
    }
}