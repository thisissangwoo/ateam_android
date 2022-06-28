package com.example.anafor.Box_Alarm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.MainActivity;
import com.example.anafor.R;
import com.example.anafor.User.UserDeleteActivity;
import com.example.anafor.User.UserInfoActivity;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;

public class Box_Alarm_modifyActivity extends Activity{

    private static final String TAG = "알람설정";

    ImageView imgv_box_modify_back;
    EditText edt_boxAlarm_content;
    TimePicker time_picker_boxAlarm;
    CheckBox checkBox_boxAlarm_number1,checkBox_boxAlarm_number2,checkBox_boxAlarm_number3,checkBox_boxAlarm_number4;
    Button btn_boxAlarm_delete,btn_boxAlarm_modify;
    String case_num,box_time,box_minute,case_time,ampm;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_box_alarm_modify);

        Intent intent = getIntent();
        IoTVO vo = (IoTVO) intent.getSerializableExtra("vo");
        Log.d(TAG, "약통 " + vo.getMemo());

        edt_boxAlarm_content = findViewById(R.id.edt_boxAlarm_content);
        time_picker_boxAlarm = findViewById(R.id.time_picker_boxAlarm);
        checkBox_boxAlarm_number1 = findViewById(R.id.checkBox_boxAlarm_number1);
        checkBox_boxAlarm_number2 = findViewById(R.id.checkBox_boxAlarm_number2);
        checkBox_boxAlarm_number3 = findViewById(R.id.checkBox_boxAlarm_number3);
        checkBox_boxAlarm_number4 = findViewById(R.id.checkBox_boxAlarm_number4);
        btn_boxAlarm_delete = findViewById(R.id.btn_boxAlarm_delete);
        btn_boxAlarm_modify = findViewById(R.id.btn_boxAlarm_modify);
        imgv_box_modify_back = findViewById(R.id.imgv_box_modify_back);

        imgv_box_modify_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //알람내용
        edt_boxAlarm_content.setText(vo.getMemo());
        /*        time_picker_boxAlarm.setOnTimeChangedListener(vo.getCase_date());*/

        if (vo.getCase_num().equals("1")){
            checkBox_boxAlarm_number1.setChecked(true);
            case_num = "1";
        }else if(vo.getCase_num().equals("2")){
            checkBox_boxAlarm_number2.setChecked(true);
            case_num = "2";
        }else if(vo.getCase_num().equals("3")){
            checkBox_boxAlarm_number3.setChecked(true);
            case_num = "3";
        }else if(vo.getCase_num().equals("4")){
            checkBox_boxAlarm_number4.setChecked(true);
            case_num = "4";
        }

        //시간설정
        time_picker_boxAlarm.setIs24HourView(true);
        case_time = vo.getCase_time();
        Log.d(TAG, "원래저장된시간 : " + case_time);
/*
        ampm =  case_time.substring(0,2);
        int h = Integer.parseInt(case_time.substring(2,4));
        int m = Integer.parseInt(case_time.substring(5,7));

        Log.d(TAG, "ampm: " + ampm);
        Log.d(TAG, "h: " + h);
        Log.d(TAG, "m: " + m);

        if (ampm.equals("오후")){
            if (h !=12){
                h = h+ 12;
            }
        }*/

        int h = Integer.parseInt(case_time.substring(0,2));
        int m = Integer.parseInt(case_time.substring(3,5));

        time_picker_boxAlarm.setHour(h);
        time_picker_boxAlarm.setMinute(m);

        //위치설정
        checkBox_boxAlarm_number1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    case_num ="1";
                    checkBox_boxAlarm_number2.setChecked(false);
                    checkBox_boxAlarm_number3.setChecked(false);
                    checkBox_boxAlarm_number4.setChecked(false);
                }
            }
        });

        checkBox_boxAlarm_number2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    case_num ="2";
                    checkBox_boxAlarm_number1.setChecked(false);
                    checkBox_boxAlarm_number3.setChecked(false);
                    checkBox_boxAlarm_number4.setChecked(false);
                }
            }
        });

        checkBox_boxAlarm_number3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    case_num ="3";
                    checkBox_boxAlarm_number1.setChecked(false);
                    checkBox_boxAlarm_number2.setChecked(false);
                    checkBox_boxAlarm_number4.setChecked(false);
                }
            }
        });

        checkBox_boxAlarm_number4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    case_num ="4";
                    checkBox_boxAlarm_number1.setChecked(false);
                    checkBox_boxAlarm_number2.setChecked(false);
                    checkBox_boxAlarm_number3.setChecked(false);
                }
            }
        });

        time_picker_boxAlarm.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //캘린더
                Calendar calendar = Calendar.getInstance();
                //타임피커에서 설정한 시간을 캘린더에 저장
                hourOfDay=time_picker_boxAlarm.getHour();
                minute = time_picker_boxAlarm.getMinute();
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);

/*                if (hourOfDay > 12) {
                    hourOfDay = hourOfDay - 12;
                    ampm = "오후";
                    if (hourOfDay <= 10) {
                        box_time = "0" + hourOfDay;
                    }
                }else if(hourOfDay == 12){
                    box_time = hourOfDay+"";
                    ampm ="오후";
                }else{
                    ampm = "오전";
                    if (hourOfDay <=10){
                        box_time = "0"+ hourOfDay;
                    }
                }*/

                if (hourOfDay >= 10){
                    box_time = hourOfDay + "";
                }else {
                    box_time = "0" + hourOfDay;
                }

                if (minute >= 10)
                    box_minute = minute + "";
                else
                    box_minute = "0" + minute;

                case_time = box_time + "시" + box_minute + "분";
                Log.d(TAG, "case_time : " + case_time);
            }
        });

        btn_boxAlarm_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String data = gson.toJson(vo.getNo());
                AskTask task = new AskTask("iot_delete");
                task.addParam("no", data);
                CommonMethod.executeAskGet(task);

                Intent i=new Intent(Box_Alarm_modifyActivity.this, Box_AlarmActivity.class);
                i.putExtra("finish", true);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        btn_boxAlarm_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_boxAlarm_content.getText().toString().length() == 0 || edt_boxAlarm_content.getText().toString().equals(" ")) {
                    Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_boxAlarm_content.requestFocus();
                }else {
                    AskTask task = new AskTask("iot_modify");
                    task.addParam("no", vo.getNo() + "");
                    task.addParam("memo", edt_boxAlarm_content.getText().toString());
                    task.addParam("case_num", case_num);
                    task.addParam("case_time",case_time);
                    CommonMethod.executeAskGet(task);
                    Intent intent = new Intent(Box_Alarm_modifyActivity.this, Box_AlarmActivity.class);
                    intent.putExtra("finish", true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    Toast.makeText(Box_Alarm_modifyActivity.this, "수정되었습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
