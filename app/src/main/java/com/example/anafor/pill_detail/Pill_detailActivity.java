package com.example.anafor.pill_detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.anafor.Nav_Choice.ChoiceAdapter;
import com.example.anafor.Pill_Main.Pill_MainAdapter;
import com.example.anafor.Pill_Main.Pill_MainDTO;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Pill_detailActivity extends AppCompatActivity {

    Pill_MainDTO dto = new Pill_MainDTO();
    ImageView imgv_pill_detail_back;
    RecyclerView recv_pill_detail;
    ArrayList<Pill_detailDTO> list = new ArrayList<>();
    Pill_detailAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_detail);
        context= this;
        Intent intent = getIntent();
        dto = (Pill_MainDTO) intent.getSerializableExtra("dto");
        Log.d("TAG", "onCreate: " + dto.getPill_code());

        imgv_pill_detail_back = findViewById(R.id.imgv_pill_detail_back);
        recv_pill_detail = findViewById(R.id.recv_pill_detail_list);
        //뒤로가기
        imgv_pill_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        selectList();

        adapter = new Pill_detailAdapter(getLayoutInflater(), list,this);
        recv_pill_detail.setAdapter(adapter);
        recv_pill_detail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    public void selectList(){
        Gson gson = new Gson();
        AskTask task = new AskTask("/drug");
        task.addParam("drug1", dto.getPill_code1()+"");
        task.addParam("drug2", dto.getPill_code2()+"");
        task.addParam("drug3", dto.getPill_code3()+"");
        InputStreamReader ir = CommonMethod.executeAskGet(task);
        list = null;
        if(ir != null){
            list = gson.fromJson(ir,
                    new TypeToken< List<Pill_detailDTO> >(){}.getType());
        }else{
            Log.d("TAG", "selectList: ");
        }

    }

}

















