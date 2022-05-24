package com.example.anafor.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
    Button btn_delete;
    ImageView back;

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

        btn_delete = findViewById(R.id.btn_userDelete_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(UserDeleteActivity.this);
                builder.setMessage("정말 탈퇴를 진행하시겠습니까? 아나포 서비스의 이용기록이 모두 삭제 됩니다.");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        Intent i=new Intent(UserDeleteActivity.this, MainActivity.class);
                        i.putExtra("finish", true);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Gson gson = new Gson();
                        AskTask task = new AskTask("delete");
                        task.addParam("vo", gson.toJson(CommonVal.loginInfo));
                        CommonMethod.executeAskGet(task);
                        CommonVal.loginInfo = null;
                        Log.d(TAG, "onClick: 탈퇴");
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

            }
        });
    }
}