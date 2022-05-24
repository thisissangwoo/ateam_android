package com.example.anafor.Hp_List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.MemberSelect;
import com.example.anafor.Hp_Hash.Hp_HashActivity;

import com.example.anafor.Hp_Hash.hpVO;
import com.example.anafor.R;

import com.example.anafor.gps.GpsTracker;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Hp_ListActivity extends AppCompatActivity {

    TabLayout hp_list_tab_layout;
    ImageView imgv_hp_list_back;
    ArrayList<hpVO> dtos;
    FrameLayout container_hp_list;
    SearchView schv_hp_hash_search;
    String query="";
    TextView search_text;
    ArrayList<hpVO> list = new ArrayList<>();
    RecyclerView recv_hp_list_hplist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp_list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        hp_list_tab_layout = findViewById(R.id.hp_list_tab_layout);
        imgv_hp_list_back = findViewById(R.id.imgv_hp_list_back);
        search_text = findViewById(R.id.search_text);

        Intent intent = getIntent();
        query = "내과"; //intent.getStringExtra("query");

        imgv_hp_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });


         // 검색 버튼 누를 때 호출
         TextView text = findViewById(R.id.search_text);
         text.setText( query  + " 에 대한 검색 결과입니다.");

         MemberSelect memberSelect = new MemberSelect(dtos, query);
         try {
             memberSelect.execute().get();
         } catch (ExecutionException e) {
             e.printStackTrace();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

        try{
            Gson gson = new Gson();
            AskTask task = new AskTask("hash");
            task.addParam("query","내과");
            InputStreamReader isr =  CommonMethod.executeAskGet(task);
            list = gson.fromJson(isr, new TypeToken<ArrayList<hpVO>>(){}.getType());
            for(hpVO vo : list){
                Log.d("@@@@", "onQueryTextSubmit: "+vo.getHp_name());
            }
            Log.d("hash", "onQueryTextSubmit: " + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

           GpsTracker gpsTracker = new GpsTracker(Hp_ListActivity.this);
             double Lng = gpsTracker.getLongitude();
             double Lat = gpsTracker.getLatitude();


        // 탭에 텍스트를 넣어주기 위해 setText 를 이용하여 텍스트 적용
        hp_list_tab_layout = findViewById(R.id.hp_list_tab_layout);
        hp_list_tab_layout.addTab(hp_list_tab_layout.newTab().setText("기본순").setId(0));
        hp_list_tab_layout.addTab(hp_list_tab_layout.newTab().setText("거리순").setId(1));
        hp_list_tab_layout.addTab(hp_list_tab_layout.newTab().setText("리뷰 많은순").setId(2));

        // 화면을 띄우자마자 리스트를 보여주기 위하여 적용
        getSupportFragmentManager().beginTransaction().replace(R.id.container_hp_list, new Hp_ListFragment(list)).commit();

        // 탭을 Select 했을 때 Position 번지 수를 주고 각자 다른 프래그먼트의 리스트를 띄우도록 함
        hp_list_tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_hp_list, new Hp_ListFragment(list)).commit();
                }else if (tab.getPosition() == 1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_hp_list, new Hp_ListFragment1(list)).commit();
                    Location locationA = new Location("pointA");
                    double Lng = gpsTracker.getLongitude();
                    double Lat = gpsTracker.getLatitude();
                }else if (tab.getPosition() == 2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_hp_list, new Hp_ListFragment2(list)).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        recv_hp_list_hplist = findViewById(R.id.recv_hp_list_hplist);


    }
}