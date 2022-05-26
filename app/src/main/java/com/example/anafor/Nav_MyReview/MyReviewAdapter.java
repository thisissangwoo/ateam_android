package com.example.anafor.Nav_MyReview;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.Hp_Review.ReviewVO;
import com.example.anafor.R;

import java.util.ArrayList;

public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewAdapter.MyReview>{

    LayoutInflater inflater;
    ArrayList<ReviewVO> list;

    public MyReviewAdapter(LayoutInflater inflater,  ArrayList<ReviewVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public MyReview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_my_review, parent, false);
        return new MyReview(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReview holder, int position) {
        holder.tv_my_review_name.setText(list.get(position).getHp_name());
        holder.tv_my_review_date.setText(list.get(position).getRev_date());
        holder.tv_my_review_content.setText(list.get(position).getRev_text4());
        if(list.get(position).getRev_text1()==0){
            holder.tv_my_review_survey1.setVisibility(View.GONE);
        }else if(list.get(position).getRev_text2()==0){
            holder.tv_my_review_survey2.setVisibility(View.GONE);
        }else if(list.get(position).getRev_text3()==0){
            holder.tv_my_review_survey3.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyReview extends RecyclerView.ViewHolder {

        TextView tv_my_review_name, tv_my_review_date, tv_my_review_content,
                tv_my_review_survey1, tv_my_review_survey2, tv_my_review_survey3;

        public MyReview(@NonNull View itemView) {
            super(itemView);
            tv_my_review_name = itemView.findViewById(R.id.tv_my_review_name);
            tv_my_review_date = itemView.findViewById(R.id.tv_my_review_date);
            tv_my_review_content = itemView.findViewById(R.id.tv_my_review_content);
            tv_my_review_survey1 = itemView.findViewById(R.id.tv_my_review_survey1);
            tv_my_review_survey2 = itemView.findViewById(R.id.tv_my_review_survey2);
            tv_my_review_survey3 = itemView.findViewById(R.id.tv_my_review_survey3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("원하시는 항목을 선택해주세요.");
                        builder.setSingleChoiceItems(new String[]{"수정", "삭제"}, 2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // setSingleChoiceItems 2개인 각각의 아이템 중
                                // 0 번째 == 수정, 1 번째 == 삭제
                                if (which == 0){

                                    //dialog.dismiss();
                                }else if (which == 1){

                                    //dialog.dismiss();
                                }
                            }
                        }).show();
                    }
                    return;
                }
            });
        }
    }
}
