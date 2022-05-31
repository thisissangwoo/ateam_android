package com.example.anafor.Hp_Hash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.anafor.Hp_List.Hp_ListActivity;
import com.example.anafor.Hp_List.Hp_ListAdapter;
import com.example.anafor.R;

import java.util.ArrayList;

public class Hp_HashActivity extends AppCompatActivity {

    ImageView imgv_hp_hash_back;
    TextView search_text;
    SearchView schv_hp_hash_search;
    ArrayList<HpDTO> list;
    Hp_HashAdapter adapter;
    RecyclerView recv_hp_hash;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp_hash);
        list = new ArrayList<>();
        context = this;
        imgv_hp_hash_back = findViewById(R.id.imgv_hp_hash_back);
        recv_hp_hash = findViewById(R.id.recv_hp_hash);
        search_text = findViewById(R.id.search_text);

        schv_hp_hash_search = findViewById(R.id.schv_hp_hash_search);


        list.add(new HpDTO("비염", "이비인후과"));
        list.add(new HpDTO("감기", "내과"));
        list.add(new HpDTO("디스크", "신경외과"));

        adapter = new Hp_HashAdapter(getLayoutInflater(), list, context);
        // 리사이클러뷰에 어댑터를 세팅
        recv_hp_hash.setAdapter(adapter);
        recv_hp_hash.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        imgv_hp_hash_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        schv_hp_hash_search.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                // 검색 버튼 누를 때 호출

             /*   MemberSelect memberSelect = new MemberSelect(dtos, query);
                try {
                    memberSelect.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                // Intent
                Intent intent = new Intent(Hp_HashActivity.this, Hp_ListActivity.class);
                intent.putExtra("query", query);

                startActivity(intent);

                // GPS 좌표를 받아, 주소로 변환

                // DB에서 받은 값을 리스트로 출력 > new Activity 로 넘겨 출력

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });



    }
}