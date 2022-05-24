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
import com.example.anafor.Pill_Main.Pill_MainAdapter;
import com.example.anafor.Pill_Main.Pill_MainDTO;
import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Pill_detailActivity extends AppCompatActivity {

    Pill_detailDTO drug_dto;
    Pill_MainDTO dto;
    Context context;
    ArrayList<Pill_detailDTO> list = new ArrayList<>();
    ImageView drug_img, imgv_pill_detail_back;
    TextView drug_name, drug_cp, drug_efcy, drug_use, drug_se, drug_atpn;
    RecyclerView recv_pill_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_detail);
        dto = new Pill_MainDTO();

        Intent intent = getIntent();
        dto = (Pill_MainDTO) intent.getSerializableExtra("dto");



        /*drug_name = findViewById(R.id.drug_name);
        drug_cp = findViewById(R.id.drug_cp);
        drug_efcy = findViewById(R.id.drug_efcy);
        drug_use = findViewById(R.id.drug_use);
        drug_se = findViewById(R.id.drug_se);

        imgv_pill_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        selectList(dto);

        Pill_detailAdapter adapter = new Pill_detailAdapter(getLayoutInflater(), list, context );
        recv_pill_detail.setAdapter(adapter);
        recv_pill_detail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }



    public void selectList(Pill_MainDTO query){
        Gson gson = new Gson();
        AskTask task = new AskTask("/drug");
        task.addParam("drug", query.getPill_code1()+"");
        task.addParam("drug", query.getPill_code2()+"");
        task.addParam("drug", query.getPill_code3()+"");
        InputStreamReader ir = CommonMethod.executeAskGet(task);
        list = null;
        if(ir != null){
            list = gson.fromJson(ir,
                    new TypeToken<List<Pill_MainDTO>>(){}.getType());
        }else{
            Log.d("TAG", "selectList: ");
        }*/
    }



}

















