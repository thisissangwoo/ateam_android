package com.example.anafor.Hp_Hash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.anafor.R;

public class Hp_Hash_ListActivity extends AppCompatActivity {

    ImageView imgv_hp_hash_list_back;
    RecyclerView recv_hp_hash_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp_hash_list);

        imgv_hp_hash_list_back = findViewById(R.id.imgv_hp_hash_list_back);
        recv_hp_hash_list = findViewById(R.id.recv_hp_hash_list);

        imgv_hp_hash_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}