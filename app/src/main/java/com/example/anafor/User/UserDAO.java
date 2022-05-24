package com.example.anafor.User;

import android.util.Log;
import android.widget.Toast;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.google.gson.Gson;

import java.io.InputStreamReader;

public class UserDAO {
    private static final String TAG = "로그인";
    LoginActivity activity; //로그인 액티비티에 있는 모든 위젯 사용가능

    public UserDAO(LoginActivity activity) {
        this.activity = activity;
    }

    public boolean isUserLogin(){
        Log.d(TAG,"onClick: ");
        AskTask task = new AskTask("login");
        task.addParam("user_id",activity.tiedt_id.getText().toString());
        task.addParam("user_pw",activity.tiedt_pw.getText().toString());

        InputStreamReader isr = CommonMethod.executeAskGet(task);
        Gson gson = new Gson();

        UserVO vo = gson.fromJson(isr, UserVO.class);


        if (vo != null){
            CommonVal.loginInfo= vo;
            Log.d(TAG, "isUserLoginName: " + vo.getUser_name());

            return true;
        }else{
            Toast.makeText(activity,"아이디 또는 비밀번호가 틀립니다",Toast.LENGTH_SHORT).show();

            activity.tiedt_id.setText("");
            activity.tiedt_pw.setText("");
            activity.tiedt_id.requestFocus();
            return false;
        }
    }

}
