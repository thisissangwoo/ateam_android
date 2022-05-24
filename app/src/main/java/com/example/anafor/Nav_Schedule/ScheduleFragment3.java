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
import com.example.anafor.R;
import com.google.gson.Gson;


public class ScheduleFragment3 extends Fragment {

    TextView tv_schedule_title_modify, tv_schedule_memo_modify;
    EditText edt_schedule_title_modify, edt_schedule_memo_modify;
    Button btn_schedule_insert_modify;

    ScheduleDTO dto;

    public ScheduleFragment3(ScheduleDTO dto) {
        this.dto = dto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule3, container, false);

        tv_schedule_title_modify = v.findViewById(R.id.tv_schedule_title_modify);
        tv_schedule_memo_modify = v.findViewById(R.id.tv_schedule_memo_modify);
        edt_schedule_title_modify = v.findViewById(R.id.edt_schedule_title_modify);
        edt_schedule_memo_modify = v.findViewById(R.id.edt_schedule_memo_modify);
        btn_schedule_insert_modify = v.findViewById(R.id.btn_schedule_insert_modify);

//==================================================================================================

        // 수정하기 를 눌렀을 때 기존에 작성했던 text 를 보여줌
        edt_schedule_title_modify.setText(dto.getSc_title());
        edt_schedule_memo_modify.setText(dto.getSc_memo());

        // 수정 완료 버튼을 눌렀을 때
        // 등록 부분과 마찬가지로 동일한 유효성 검사를 거친 후
        // 마지막 else 부분에 수정한 화면을 찍어 줌
        btn_schedule_insert_modify.setOnClickListener(new View.OnClickListener() {

            Gson gson = new Gson();

            @Override
            public void onClick(View v) {

                if(edt_schedule_title_modify.getText().toString().length() == 0 || edt_schedule_title_modify.getText().toString().equals(" ")){
                    Toast.makeText(getContext().getApplicationContext(), "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_schedule_title_modify.requestFocus();
                }else if(edt_schedule_memo_modify.getText().toString().length() == 0 || edt_schedule_memo_modify.getText().toString().equals(" ")){
                    Toast.makeText(getContext().getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_schedule_memo_modify.requestFocus();
                }else{
                    dto.setSc_title(edt_schedule_title_modify.getText() + "");
                    dto.setSc_memo(edt_schedule_memo_modify.getText() + "");

                    AskTask task = new AskTask("/schedule_update");
                    task.addParam("dto", gson.toJson(dto));
                    CommonMethod.executeAskGet(task);

                    Toast.makeText(getContext().getApplicationContext(), "일정 수정 완료", Toast.LENGTH_SHORT).show();
                    ((ScheduleActivity)getActivity()).changeFragment(new ScheduleFragment2());
                }
            }
        });
        return v;
    }
}