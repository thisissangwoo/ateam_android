package com.example.anafor.Box_Alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Box_AlarmActivity extends AppCompatActivity {
    private static final String TAG = "알람설정";

    ImageView imgv_box_alarm_back;
    TextView tv_box_alarm_insert;

    RecyclerView recv_box_alarm;
    ArrayList<IoTVO> list;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_alarm);
        context = this;
        tv_box_alarm_insert = findViewById(R.id.tv_box_alarm_insert);
        imgv_box_alarm_back = findViewById(R.id.imgv_box_alarm_back);

        tv_box_alarm_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Box_AlarmActivity.this, Box_Alarm_detailActivity.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        imgv_box_alarm_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        recv_box_alarm = findViewById(R.id.recv_box_alarm);

        list = selectList();

        Box_AlarmAdapter adapter = new Box_AlarmAdapter(getLayoutInflater(), list, context);
        recv_box_alarm.setAdapter(adapter);
        recv_box_alarm.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    public ArrayList<IoTVO> selectList() {
        Gson gson = new Gson();
        AskTask task = new AskTask("iot_select");
        task.addParam("user_id", CommonVal.loginInfo.getUser_id());
        Log.d(TAG, "selectList: " + CommonVal.loginInfo.getUser_id() );
        InputStreamReader ir =  CommonMethod.executeAskGet(task);
        ArrayList<IoTVO> list = null ;
        if(ir!=null){
            list = gson.fromJson(ir, new TypeToken<List<IoTVO>>(){}.getType());
        }else{
            Log.d("TAG", "selectList: " + "널임");
        }
        return list;
    }

}