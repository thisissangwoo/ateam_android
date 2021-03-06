package com.example.anafor.Hp_List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Hp_Hash.HpDTO;

import com.example.anafor.Hp_Hash.Hp_HashActivity;
import com.example.anafor.MainActivity;
import com.example.anafor.R;

import com.example.anafor.User.UserInfoActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Hp_ListActivity extends AppCompatActivity {

    TabLayout hp_list_tab_layout;
    ImageView imgv_hp_list_back;
    SearchView schv_hp_hash_search;
    String query;
    TextView search_text;
    ArrayList<HpDTO> list = new ArrayList<>();
    RecyclerView recv_hp_list_hplist;
    String hash = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hp_list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        hp_list_tab_layout = findViewById(R.id.hp_list_tab_layout);
        imgv_hp_list_back = findViewById(R.id.imgv_hp_list_back);
        search_text = findViewById(R.id.search_text);
        schv_hp_hash_search =findViewById(R.id.schv_hp_hash_search);


        Intent intent = getIntent();
//        query = intent.getStringExtra("gamgi");
        query = intent.getStringExtra("query");
        hash = intent.getStringExtra("text");

        imgv_hp_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hp_ListActivity.this, Hp_HashActivity.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


        // ?????? ?????? ?????? ??? ??????
        // ?????? keyword??? Hp_name ?????? ?????? ??????
        search_text.setText(query + " ??? ?????? ?????? ???????????????.");

        schv_hp_hash_search.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search_text.setText(query + " ??? ?????? ?????? ???????????????.");

                getSupportFragmentManager().beginTransaction().replace(R.id.container_hp_list, new Hp_ListFragment(query,hash)).commit();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });


        // ?????? ???????????? ???????????? ?????? setText ??? ???????????? ????????? ??????
        hp_list_tab_layout = findViewById(R.id.hp_list_tab_layout);
        hp_list_tab_layout.addTab(hp_list_tab_layout.newTab().setText("?????????").setId(0));
        hp_list_tab_layout.addTab(hp_list_tab_layout.newTab().setText("?????? ?????????").setId(1));

        // ????????? ??????????????? ???????????? ???????????? ????????? ??????
        getSupportFragmentManager().beginTransaction().replace(R.id.container_hp_list, new Hp_ListFragment(query,hash)).commit();

        // ?????? Select ?????? ??? Position ?????? ?????? ?????? ?????? ?????? ?????????????????? ???????????? ???????????? ???
        hp_list_tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_hp_list, new Hp_ListFragment(query,hash)).commit();
                }else if (tab.getPosition() == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_hp_list, new Hp_ListFragment1(query, hash)).commit();
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


    public void selectList(String query){

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}