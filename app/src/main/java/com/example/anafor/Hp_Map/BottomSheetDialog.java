package com.example.anafor.Hp_Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Hp_Information.Hp_InformationActivity;
import com.example.anafor.Hp_Information.Hp_infoDTO;
import com.example.anafor.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import net.daum.mf.map.api.MapView;

import java.io.InputStreamReader;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    Context context;
    String place_name , distance, roadAddr, phone;
    Gson gson = new Gson();

    public BottomSheetDialog(Context context, String place_name, String distance, String roadAddr, String phone ) {
        this.context = context;
        this.place_name = place_name;
        this.distance = distance;
        this.roadAddr = roadAddr;
        this.phone = phone;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog, container,false);

        TextView  tv_hp_place_name = view.findViewById(R.id.tv_hp_place_name);
        TextView tv_hp_distance = view.findViewById(R.id.tv_hp_distance);
        TextView tv_hp_roadAddr = view.findViewById(R.id.tv_hp_roadAddr);

        LinearLayout linear = view.findViewById(R.id.linear);

        tv_hp_place_name.setText(place_name);
        tv_hp_distance.setText(distance+"m");
        tv_hp_roadAddr.setText(roadAddr);

       linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //카카오 api 조회해서 나온 장소 이름과 전화번호로 해당하는 상세 정보 조회
                AskTask task = new AskTask("detail.map");
                task.addParam("place_name", place_name);
                task.addParam("phone", phone);
                InputStreamReader isr = CommonMethod.executeAskGet(task);
                Hp_infoDTO infoDTO = gson.fromJson(isr, Hp_infoDTO.class);
                if (infoDTO != null) {                 //select 가 안 될 경우
                    Intent intent = new Intent(context, Hp_InformationActivity.class);
                    intent.putExtra("infoDTO", infoDTO);
                    startActivity(intent);                   // Hp_InformationActivity로 이동
                } else {
                    Toast.makeText(context, "상세정보를 조회할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
