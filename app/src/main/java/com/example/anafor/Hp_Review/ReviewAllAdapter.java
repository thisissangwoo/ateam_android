package com.example.anafor.Hp_Review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.anafor.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ReviewAllAdapter extends RecyclerView.Adapter<ReviewAllAdapter.ViewHolder>  {

    ArrayList<ReviewVO> reviewList;
  LayoutInflater inflater;


    public ReviewAllAdapter(ArrayList<ReviewVO> reviewList, LayoutInflater inflater) {
            this.reviewList = reviewList;
            this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_all_review,parent,false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.reviewRating.setRating((float) reviewList.get(position).getRev_grade());
            holder.tv_review_name.setText(reviewList.get(position).getUser_name()+ "님");
            holder.tv_review_content.setText(reviewList.get(position).getRev_text4());
            holder.tv_review_date.setText(reviewList.get(position).getRev_date());
            if(reviewList.get(position).getRev_text1()==0){
                     holder.tv_review_survey1.setVisibility(View.GONE);
            }else{
                holder.tv_review_survey1.setVisibility(View.VISIBLE);
            }
            if(reviewList.get(position).getRev_text2()==0){
                        holder.tv_review_survey2.setVisibility(View.GONE);
             }else{
                holder.tv_review_survey2.setVisibility(View.VISIBLE);
            }
            if(reviewList.get(position).getRev_text3()==0){
                        holder.tv_review_survey3.setVisibility(View.GONE);
          }else{
                holder.tv_review_survey3.setVisibility(View.VISIBLE);
            }
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View itemview;
        TextView tv_review_name, tv_review_date, tv_review_content, tv_review_survey1, tv_review_survey2, tv_review_survey3;
        RatingBar reviewRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;
            tv_review_name = itemview.findViewById(R.id.tv_review_name);
            tv_review_date = itemview.findViewById(R.id.tv_review_date);
            tv_review_content = itemview.findViewById(R.id.tv_review_content);
            tv_review_survey1 = itemview.findViewById(R.id.tv_review_survey1);
            tv_review_survey2 = itemview.findViewById(R.id.tv_review_survey2);
            tv_review_survey3 = itemview.findViewById(R.id.tv_review_survey3);
            tv_review_date = itemview.findViewById(R.id.tv_review_date);
            reviewRating  = itemview.findViewById(R.id.reviewRating);
        }
    }
}
