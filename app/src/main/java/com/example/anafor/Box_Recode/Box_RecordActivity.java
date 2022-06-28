package com.example.anafor.Box_Recode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anafor.Box_Alarm.IoTVO;
import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Box_RecordActivity extends AppCompatActivity {

    ImageView imgv_box_record_back;
    RecyclerView recv_box_record;
    ArrayList<IoTVO> list = new ArrayList<>();
    private Object Context;
    TextView tv_box_record_not_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_record);

        tv_box_record_not_list = findViewById(R.id.tv_box_record_not_list);
        imgv_box_record_back = findViewById(R.id.imgv_box_record_back);

        imgv_box_record_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        list = selectList();
        if (list.size()==0){
            tv_box_record_not_list.setVisibility(View.VISIBLE);
        }else{
            tv_box_record_not_list.setVisibility(View.INVISIBLE);
        }

        recv_box_record = findViewById(R.id.recv_box_record);

        Box_RecordAdapter adapter = new Box_RecordAdapter(getLayoutInflater(), list);

        recv_box_record.setAdapter(adapter);
        recv_box_record.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    public ArrayList<IoTVO> selectList() {
        Gson gson = new Gson();
        AskTask task = new AskTask("iot_recode_select");
        task.addParam("user_id", CommonVal.loginInfo.getUser_id());
        Log.d("TAG", "selectList: " + CommonVal.loginInfo.getUser_id() );
        InputStreamReader ir =  CommonMethod.executeAskGet(task);
        ArrayList<IoTVO> list = null ;

        Log.d("TAG", "selectList: " + ir);

        if(ir!=null){
            list = gson.fromJson(ir, new TypeToken<List<IoTVO>>(){}.getType());
        }else{
            Log.d("TAG", "selectList: " + "널임");
        }
        return list;
    }
}