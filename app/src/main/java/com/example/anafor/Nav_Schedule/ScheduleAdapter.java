package com.example.anafor.Nav_Schedule;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<ScheduleDTO> list;
    Context context;
    ScheduleDTO dto;
    String schedule;

    public ScheduleAdapter(LayoutInflater inflater, ArrayList<ScheduleDTO> list, Context context, String schedule) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.schedule = schedule;
    }// ScheduleAdapter

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;

        // list 사이즈가 0 이면 화면을 교체를 해줌
        if (list.size() == 0) {
            // 기존에 리사이클러뷰가 들어있는 xml 에 등록된 일정이 없습니다. 라는 TextView 를 썻는데
            // inflate 로 해당 TextView 가 있는 프래그먼트를 붙여보니 문구가 두번 적혀 나와서
            // 따로 item 을 추가하여 TextView 를 붙여줬음
            itemView = inflater.inflate(R.layout.item_schedule_empty, parent, false);
        } else {
            itemView = inflater.inflate(R.layout.item_schedule, parent, false);
        }// if
        return new ViewHolder(itemView);
    }// onCreateViewHolder

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // list 의 size 가 0 이 아닐 때
        if (list.size() != 0) {
            holder.tv_schedule_title.setText(list.get(position).getSc_title());
            holder.tv_schedule_content.setText(list.get(position).getSc_memo());
        }// if
    }// onBindViewHolder

    @Override
    public int getItemCount() {
        // 해당하는 list.size 가 0 이면 리턴을 1 을 주고,
        // 0 이 아닐 때는 해당하는 데이터의 list.size 만큼 보여줌
        if (list.size() == 0){
            return 1;
        }else{
            return list.size();
        }// if
    }// getItemCount

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_schedule_title, tv_schedule_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // list 의 size 가 0 이 아닐 때
            if(list.size() != 0){
                tv_schedule_title = itemView.findViewById(R.id.tv_schedule_title);
                tv_schedule_content = itemView.findViewById(R.id.tv_schedule_content);
            }// if

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
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
                                    // 0 번째 == 수정
                                    // 액티비티를 제외한 프래그먼트, 어댑터는 본인의 출처 즉, 부모가 누구인지
                                    // 알아야 서로 넘겨주면서 사용이 가능하므로 Context 로 받아와서 사용하여야 함
                                    CalendarDay day =  CalendarDay.from(LocalDate.of(2022, Integer.parseInt( schedule.substring(schedule.indexOf("년")+1,schedule.indexOf("월")).trim()),
                                            Integer.parseInt( schedule.substring(schedule.indexOf("월")+1,schedule.indexOf("일")).trim())));
                                    ((ScheduleActivity)context).changeFragment(new ScheduleFragment3(list.get(position),schedule),day);
                                    dialog.dismiss();
                                }else if (which == 1){
                                    // 1 번째 == 삭제
                                    Gson gson = new Gson();
                                    AskTask task = new AskTask("schedule_delete");
                                    dto = list.get(position);
                                    task.addParam("dto", gson.toJson(dto));
                                    CommonMethod.executeAskGet(task);

                                    ((ScheduleActivity)context).changeFragment(new ScheduleFragment2(schedule),null);
                                    Toast.makeText(context.getApplicationContext(), "해당 일정이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        }).show();
                    }
                    return false;
                }// onLongClick
            });// LongClickListener
        }// ViewHolder

    }// class ViewHolder
}// class ScheduleAdapter
