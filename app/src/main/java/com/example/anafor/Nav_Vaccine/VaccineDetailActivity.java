package com.example.anafor.Nav_Vaccine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anafor.Hp_Main.Hp_MainFragment;
import com.example.anafor.Nav_Schedule.ScheduleFragment1;
import com.example.anafor.Pill_Main.Pill_MainDTO;
import com.example.anafor.R;

public class VaccineDetailActivity extends AppCompatActivity {

    LinearLayout container_vaccine_detail;
    ImageView a1, imgv_vaccine_detail_back;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_detail);

        imgv_vaccine_detail_back = findViewById(R.id.imgv_vaccine_detail_back);
        container_vaccine_detail = findViewById(R.id.container_vaccine_detail);
        a1 = findViewById(R.id.a1);

        imgv_vaccine_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        changeFragment(new VaccineDetailFragment1());
        Intent intent = getIntent();
        number = intent.getIntExtra("1", 0);

        if (number == 1){
            changeFragment(new VaccineDetailFragment2());
        }else if (number == 2){
            changeFragment(new VaccineDetailFragment3());
        }else if (number == 3){
            changeFragment(new VaccineDetailFragment4());
        }else if (number == 4){
            changeFragment(new VaccineDetailFragment5());
        }

    }
    public void changeFragment(Fragment fragment){   /* 플래그먼트가 바뀌면서 */
        getSupportFragmentManager().beginTransaction().replace(R.id.container_vaccine_detail, fragment).commit();
    }
}