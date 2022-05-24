package com.example.anafor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 코드 외우지 마시고 필요하면 복붙해서 사용하세요 ~
        // 단, 인터페이스를 파라메터로 입력받는 메소드 호출 하는 방법을 알고 있어야 함
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);  // 새로운 액티비티로 연결(MainActivity 로 시작함)
                finish();   // 뒤로가기 했을 때 현재 화면을 완전히 종료 처리
            }
        } , 3000);  // 3 초를 지연시키고 코드를 실행 함 딜레이


    }//onCreate

}//class