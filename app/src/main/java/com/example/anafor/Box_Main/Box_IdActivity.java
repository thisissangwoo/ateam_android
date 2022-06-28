package com.example.anafor.Box_Main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.anafor.Box_Alarm.Box_AlarmActivity;
import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.Hp_List.Hp_ListFragment;
import com.example.anafor.MainActivity;
import com.example.anafor.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.InputStreamReader;

public class Box_IdActivity extends AppCompatActivity {
    private static final String TAG = "박스아이디";
    
    ImageView imgv_BoxId_back;
    TextInputLayout til_BoxId_id;
    TextInputEditText tiedt_BoxId_id;
    Button btn_BoxId_btn;
    Boolean box_id_chk;
    String box_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_id);

        imgv_BoxId_back = findViewById(R.id.imgv_BoxId_back);
        til_BoxId_id = findViewById(R.id.til_BoxId_id);
        tiedt_BoxId_id = findViewById(R.id.tiedt_BoxId_id);
        btn_BoxId_btn = findViewById(R.id.btn_BoxId_btn);

        if (CommonVal.loginInfo.getBox_id()!=0) {
            tiedt_BoxId_id.setText(String.valueOf(CommonVal.loginInfo.getBox_id()));
        }else{
            tiedt_BoxId_id.setText(null);
        }

        imgv_BoxId_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_BoxId_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskTask task = new AskTask("box_id_chk");
                box_id = tiedt_BoxId_id.getText().toString();
                task.addParam("box_id",box_id);

                InputStreamReader isr = CommonMethod.executeAskGet(task);
                Gson gson = new Gson();

                Log.d(TAG, "onClick: " + isr);
                box_id_chk = gson.fromJson(isr,Boolean.class);

                Log.d(TAG, "아이디 존재여부: " + box_id_chk);

                if(box_id_chk==true) {
                    til_BoxId_id.setHelperText("확인");
                    AskTask task2 = new AskTask("box_id_insert");
                    task2.addParam("user_id", CommonVal.loginInfo.getUser_id());
                    task2.addParam("box_id", box_id);
                    CommonMethod.executeAskGet(task2);
                    showDialog();
                    CommonVal.loginInfo.setBox_id(Integer.parseInt(box_id));
                }else if(Integer.parseInt(String.valueOf(tiedt_BoxId_id.getText()))==CommonVal.loginInfo.getBox_id()){
                    til_BoxId_id.setHelperText("확인");
                    AskTask task2 = new AskTask("box_id_insert");
                    task2.addParam("user_id", CommonVal.loginInfo.getUser_id());
                    task2.addParam("box_id", box_id);
                    CommonMethod.executeAskGet(task2);
                    showDialog();
                    CommonVal.loginInfo.setBox_id(Integer.parseInt(box_id));
                }else{
                    til_BoxId_id.setError("이미 등록된 박스아이디입니다.");
                    til_BoxId_id.setHelperText(null);
                }
            }
        });

    }

    public void showDialog(){
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(Box_IdActivity.this)
                .setTitle("알림")
                .setMessage("등록완료")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
/*                      Intent intent = new Intent(Box_IdActivity.this, MainActivity.class);
                      intent.putExtra("finish", true);
                      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                      startActivity(intent);
                      finish();*/
                      CommonVal.bottom_menu = "3";
                      onBackPressed();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
}