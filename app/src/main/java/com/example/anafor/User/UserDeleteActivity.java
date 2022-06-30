package com.example.anafor.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.MainActivity;
import com.example.anafor.R;
import com.google.gson.Gson;

public class UserDeleteActivity extends AppCompatActivity {
    private static final String TAG = "회원탈퇴";

    ImageView back;
    CheckBox chk_delete;
    Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_delete);

        back = findViewById(R.id.imgv_userDelete_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        chk_delete = findViewById(R.id.chk_userDelete_chk);
        chk_delete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_delete.setTextColor(Color.parseColor("#000000"));
                }else{
                    chk_delete.setTextColor(Color.parseColor("#b6b6b6"));
                }
            }
        });

        btn_delete = findViewById(R.id.btn_userDelete_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(UserDeleteActivity.this);
                if (chk_delete.isChecked()){
                    builder.setTitle("알림");
                    builder.setMessage("정말 탈퇴를 진행하시겠습니까? 아나포 서비스의 이용기록이 모두 삭제 됩니다.");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            Intent i=new Intent(UserDeleteActivity.this, MainActivity.class);
                            i.putExtra("finish", true);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.remove("login");
                            editor.clear();
                            editor.apply();
                            editor.commit();
                            Gson gson = new Gson();
                            AskTask task = new AskTask("delete");
                            task.addParam("vo", gson.toJson(CommonVal.loginInfo));
                            CommonMethod.executeAskGet(task);
                            CommonVal.loginInfo = null;
                            Log.d(TAG, "onClick: 탈퇴");
                            Toast.makeText(UserDeleteActivity.this, "탈퇴가 완료되었습니다", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            finish();
                        }
                    });

                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert=builder.create();
                    alert.show();
                }else {
                    builder.setTitle("알림");
                    builder.setMessage("안내 사항 확인에 동의하여야 완료됩니다.");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();                        }
                    });
                    AlertDialog alert=builder.create();
                    alert.show();
                }
            }
        });
    }
}