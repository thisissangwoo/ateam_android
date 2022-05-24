package com.example.anafor.Box_Main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.anafor.Box_Alarm.Box_AlarmActivity;
import com.example.anafor.Box_Recode.Box_RecordActivity;
import com.example.anafor.R;


public class Box_MainFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_box_main, container, false);

        Button btn_box_main_alarm = v.findViewById(R.id.btn_box_main_alarm);
        Button btn_box_main_record = v.findViewById(R.id.btn_box_main_record);
        Button btn_box_main_setting = v.findViewById(R.id.btn_box_main_setting);


        btn_box_main_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Box_AlarmActivity.class);
                startActivity(intent);
            }
        });

        btn_box_main_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Box_RecordActivity.class);
                startActivity(intent);
            }
        });

        btn_box_main_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Box_SettingActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }


}