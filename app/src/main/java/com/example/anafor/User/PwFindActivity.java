package com.example.anafor.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.MainActivity;
import com.example.anafor.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.InputStreamReader;


public class PwFindActivity extends AppCompatActivity {
    private static final String TAG = "비밀번호찾기";
    Button btn_find;
    TextInputLayout til_id;
    TextInputEditText tiedt_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_find);

        //뒤로가기
        findViewById(R.id.imgv_pwFind_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //확인버튼
        btn_find = findViewById(R.id.btn_pwFind_btn);
        til_id = findViewById(R.id.til_pwFind_id);
        tiedt_id = findViewById(R.id.tiedt_pwFind_id);
        tiedt_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                til_id.setError(null);
            }
        });

        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskTask task = new AskTask("id_chk2");
                String user_id = tiedt_id.getText().toString();

                task.addParam("user_id", user_id);
                InputStreamReader isr = CommonMethod.executeAskGet(task);
                Gson gson = new Gson();

                Boolean idChk = gson.fromJson(isr,Boolean.class);

                Log.d(TAG, "아이디 존재여부: " + idChk);

                if(idChk==true) {
                    til_id.setHelperText("확인");
                    AskTask task2 = new AskTask("pw_find");
                    task2.addParam("user_id", user_id);
                    CommonMethod.executeAskGet(task2);
                    showDialog();
                }else{
                    til_id.setError("등록되지 않은 이메일 입니다.");
                    til_id.setHelperText(null);
                }
            }
        });
    }

    public void showDialog(){
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(PwFindActivity.this)
                .setTitle("알림")
                .setMessage("해당 이메일로 임시비밀번호를 발급했습니다. 로그인페이지로 이동합니다.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Intent intent = new Intent(PwFindActivity.this,LoginActivity.class);
                        intent.putExtra("finish", true);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
}

