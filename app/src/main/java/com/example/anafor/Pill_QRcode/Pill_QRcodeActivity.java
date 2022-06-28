package com.example.anafor.Pill_QRcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.MainActivity;
import com.example.anafor.Pill_Main.Pill_MainDTO;
import com.example.anafor.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pill_QRcodeActivity extends AppCompatActivity {


    IntentIntegrator qrScan;
    ArrayList<Pill_MainDTO> list;
    Pill_MainDTO dto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_qrcode);

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(true); // default 가 세로모드인데 휴대폰 방향에 따라 가로, 세로로 자동 변경됩니다.
        qrScan.setPrompt("QR 코드를 사각형 안에 넣어주세요.");
        // qrScan.setRequestCode(999);
        qrScan.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "취소되었습니다", Toast.LENGTH_SHORT).show();
                // todo
            } else {
                Toast.makeText(this, "큐알코드를 스캔합니다", Toast.LENGTH_SHORT).show();
                // todo
                //DB insert 처리를 함
                AskTask task = new AskTask("/pill");
                task.addParam("pill", result.getContents());



                CommonMethod.executeAskGet(task);
                //json 형식으로 받은 데이터를 변형 시켜서 인서트
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }





}