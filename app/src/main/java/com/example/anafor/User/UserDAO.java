package com.example.anafor.User;

import android.util.Log;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.google.gson.Gson;

import java.io.InputStreamReader;

public class UserDAO {
    private static final String TAG = "로그인";
    String id , pw ;

    public UserDAO(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public UserDAO(String id) {
        this.id = id;
    }

    public boolean isUserLogin(){
        Log.d(TAG,"onClick: ");
        AskTask task = new AskTask("login");
        task.addParam("user_id",id);
        task.addParam("user_pw",pw);

        InputStreamReader isr = CommonMethod.executeAskGet(task);
        Gson gson = new Gson();

        UserVO vo = gson.fromJson(isr, UserVO.class);


        if (vo != null){
            CommonVal.loginInfo= vo;
            Log.d(TAG, "isUserLoginName: " + vo.getUser_name());

            return true;
        }else{

            return false;
        }
    }
    // 비밀번호를 제외한 id 만 조회
    public boolean isSocialLogin(){
        AskTask task = new AskTask("/social");
        task.addParam("user_id", id);
        InputStreamReader isr = CommonMethod.executeAskGet(task);
        Gson gson = new Gson();
        UserVO vo = gson.fromJson(isr, UserVO.class);
        if (vo != null){
            CommonVal.loginInfo= vo;
            Log.d(TAG, "isUserLoginName: " + vo.getUser_name());
            return true;
        }else{
            return false;
        }
    }
}
