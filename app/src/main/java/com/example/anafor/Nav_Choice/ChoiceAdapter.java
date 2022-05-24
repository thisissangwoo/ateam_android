package com.example.anafor.Nav_Choice;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class ChoiceAdapter extends RecyclerView.Adapter<ChoiceAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<ChoiceDTO> list;
    ChoiceActivity activity;

    boolean visibleChk = false;

    public ChoiceAdapter(LayoutInflater inflater, ArrayList<ChoiceDTO> list , ChoiceActivity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = activity;
    }

    public boolean isVisibleChk() {
        return visibleChk;
    }

    public void setVisibleChk(boolean visibleChk) {
        this.visibleChk = visibleChk;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_my_choice, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imgv_my_choice_img.setImageResource(list.get(position).getImg_url());
        holder.tv_my_choice_name.setText(list.get(position).getName());
        holder.tv_my_choice_addr.setText(list.get(position).getAddr());
        holder.tv_my_choice_category.setText(list.get(position).getCategory());
        holder.tv_my_choice_date.setText(list.get(position).getDate());

        /*if(visibleChk)holder.ckb_choice.setVisibility(View.VISIBLE);
        else holder.ckb_choice.setVisibility(View.GONE);*/

//        holder.ckb_choice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (holder.ckb_choice.isChecked()) {
//                    holder.btn_my_choice_delete.setVisibility(View.VISIBLE);
//                } else if( ! holder.ckb_choice.isChecked()){
//                    holder.btn_my_choice_delete.setVisibility(View.GONE);
//                }
//            }
//        });

// 체크박스 안에다가 삭제버튼도 괜찮을듯 디자인
        //프래그먼트에 변수를하나 넘겨주는게 좋음
//        holder.btn_my_choice_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (holder.ckb_choice.isChecked()){
//                    //activity.delete(한건씩해도되고, 여러건해도되고);
//                    activity.delete();
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View itemview;
        ImageView imgv_my_choice_img;
        TextView tv_my_choice_name, tv_my_choice_addr, tv_my_choice_category, tv_my_choice_date;
        CheckBox ckb_choice;
        //Button btn_my_choice_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;

            imgv_my_choice_img = itemView.findViewById(R.id.imgv_my_choice_img);
            tv_my_choice_name = itemView.findViewById(R.id.tv_my_choice_name);
            tv_my_choice_addr = itemView.findViewById(R.id.tv_my_choice_addr);
            tv_my_choice_category = itemView.findViewById(R.id.tv_my_choice_category);
            tv_my_choice_date = itemView.findViewById(R.id.tv_my_choice_date);
            ckb_choice = itemView.findViewById(R.id.ckb_choice);
            //btn_my_choice_delete = itemView.findViewById(R.id.btn_my_choice_delete);

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
