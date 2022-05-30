package com.example.anafor.Nav_Vaccine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.MainActivity;
import com.example.anafor.R;

import java.util.ArrayList;


public class VaccineActivity extends AppCompatActivity {

    TextView tv_vaccine_logo;
    ImageView imgv_vaccine_back;
    RecyclerView rec_view;
    String vaccine;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        context = this;

        rec_view = findViewById(R.id.rec_view);
        imgv_vaccine_back = findViewById(R.id.imgv_vaccine_back);
        tv_vaccine_logo = findViewById(R.id.tv_vaccine_logo);

        tv_vaccine_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaccineActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imgv_vaccine_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        // 접종정보 배너값
        ArrayList<VaccineDTO>list = new ArrayList<>();
        list.add(new VaccineDTO("A형간염","(Hepatitis A vaccine, HepA)", "A형간염 백신의 예방접종 안내문입니다"));
        list.add(new VaccineDTO("B형간염","(Hepatitis B vaccine, HepB)", "B형간염 백신의 예방접종 안내문입니다"));
        list.add(new VaccineDTO("사람유두종 바이러스","(Human Papilomavirus, HPV)", "사람유두종 바이러스 감염증 예방접종"));
        list.add(new VaccineDTO("인플루엔자","(Inactivated influenza vaccine, IIV)", "인플루엔자 백신의 예방접종 안내문입니다"));
        list.add(new VaccineDTO("폐렴구균","(PCV / PPSV)", "폐렴구균 백신의 예방접종 안내문입니다"));

        VaccineAdapter adapter = new VaccineAdapter(list, getLayoutInflater(), context, vaccine);
        rec_view.setAdapter(adapter);
        rec_view.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL , false));

    }

}