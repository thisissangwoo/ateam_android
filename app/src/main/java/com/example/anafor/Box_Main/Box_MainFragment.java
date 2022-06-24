package com.example.anafor.Box_Main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anafor.Box_Alarm.Box_AlarmActivity;
import com.example.anafor.Box_Alarm.IoTVO;
import com.example.anafor.Box_Recode.Box_RecordActivity;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.R;
import com.example.anafor.User.UserInfoActivity;
import com.example.anafor.User.UserVO;
import com.kakao.sdk.user.model.User;


public class Box_MainFragment extends Fragment {
    
    private static final String TAG = "박스아이디";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_box_main, container, false);

        Button btn_box_main_alarm = v.findViewById(R.id.btn_box_main_alarm);
        Button btn_box_main_record = v.findViewById(R.id.btn_box_main_record);
        Button btn_box_main_setting = v.findViewById(R.id.btn_box_main_setting);
        TextView tv_box_main_box_id = v.findViewById(R.id.tv_box_main_box_id);
        TextView tv_box_main_box_real_id = v.findViewById(R.id.tv_box_main_box_real_id);
        TextView tv_box_main_boxID_edit = v.findViewById(R.id.tv_box_main_boxID_edit);

        if(CommonVal.loginInfo.getBox_id()!=0){
            tv_box_main_box_real_id.setText(String.valueOf(CommonVal.loginInfo.getBox_id()));
            tv_box_main_boxID_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Box_IdActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }else{
            tv_box_main_box_id.setText("");
            tv_box_main_box_real_id.setText("아나포박스를 등록해주세요");
            tv_box_main_boxID_edit.setText("등록");
            tv_box_main_boxID_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Box_IdActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }

        btn_box_main_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonVal.loginInfo.getBox_id() != 0){
                    Intent intent = new Intent(getContext(), Box_AlarmActivity.class);
                    intent.putExtra("finish", true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext().getApplicationContext(), "박스 아이디 등록후 이용하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_box_main_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonVal.loginInfo.getBox_id() != 0){
                    Intent intent = new Intent(getContext(), Box_RecordActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext().getApplicationContext(), "박스 아이디 등록후 이용하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_box_main_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonVal.loginInfo.getBox_id() != 0) {
                    Intent intent = new Intent(getContext(),  WebMapActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext().getApplicationContext(), "박스 아이디 등록후 이용하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

}