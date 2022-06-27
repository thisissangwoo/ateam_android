package com.example.anafor.Nav_Schedule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;

import com.example.anafor.Common.CommonVal;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.threeten.bp.LocalDate;

public class ScheduleFragment1 extends Fragment {

    TextView tv_schedule_title_schedule, tv_schedule_memo_schedule, tv_schedule_diary_date;
    EditText edt_schedule_title_schedule, edt_schedule_memo_schedule;
    Button btn_schedule_insert_schedule;

    String schedule;

    public ScheduleFragment1(String schedule) {
        this.schedule = schedule;
    }// ScheduleFragment1

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_schedule1, container, false);

        tv_schedule_title_schedule = v.findViewById(R.id.tv_schedule_title_schedule);
        tv_schedule_memo_schedule = v.findViewById(R.id.tv_schedule_memo_schedule);
        edt_schedule_title_schedule = v.findViewById(R.id.edt_schedule_title_schedule);
        edt_schedule_memo_schedule = v.findViewById(R.id.edt_schedule_memo_schedule);
        btn_schedule_insert_schedule = v.findViewById(R.id.btn_schedule_insert_schedule);
        tv_schedule_diary_date = v.findViewById(R.id.tv_schedule_diary_date);

//==================================================================================================
        
        // 일정 등록 버튼을 눌렀을 때, 유효성 검사를 거친 후
        // insert 되는 과정에서 마지막 else 부분에서 DB 와 통신(연결) 을 하고
        // 등록이 되게끔 처리하였음
        btn_schedule_insert_schedule.setOnClickListener(new View.OnClickListener() {

            Gson gson = new Gson();
            ScheduleDTO dto = new ScheduleDTO();

            @Override
            public void onClick(View v) {

                if(edt_schedule_title_schedule.getText().toString().length() == 0 || edt_schedule_title_schedule.getText().toString().equals(" ")){
                    Toast.makeText(getContext().getApplicationContext(), "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_schedule_title_schedule.requestFocus();
                }else if(edt_schedule_memo_schedule.getText().toString().length() == 0 || edt_schedule_memo_schedule.getText().toString().equals(" ")){
                    Toast.makeText(getContext().getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_schedule_memo_schedule.requestFocus();
                }else{
                    AskTask task = new AskTask("/schedule_insert");
                    dto.setSc_title(edt_schedule_title_schedule.getText() + "");
                    dto.setSc_memo(edt_schedule_memo_schedule.getText() + "");
                    dto.setSc_date(schedule);
                    dto.setUser_id(CommonVal.loginInfo.getUser_id());
                    task.addParam("schedule_insert", gson.toJson(dto));
                    CommonMethod.executeAskGet(task);
                    Toast.makeText(getContext().getApplicationContext(), "일정 등록 완료", Toast.LENGTH_SHORT).show();

                    CalendarDay day =  CalendarDay.from(LocalDate.of(2022, Integer.parseInt( schedule.substring(schedule.indexOf("년")+1,schedule.indexOf("월")).trim()),
                            Integer.parseInt( schedule.substring(schedule.indexOf("월")+1,schedule.indexOf("일")).trim())));

                    ((ScheduleActivity)getActivity()).changeFragment(new ScheduleFragment2(schedule),day);
                }// if

            }// onClick
        });// setOnClickListener
        return v;
//==================================================================================================
    }// onCreateView
}// class ScheduleFragment1