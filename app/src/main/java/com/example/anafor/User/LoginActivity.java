package com.example.anafor.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anafor.Box_Main.Box_MainFragment;
import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.MainActivity;
import com.example.anafor.Pill_Main.Pill_MainFragment;
import com.example.anafor.R;
import com.google.android.material.textfield.TextInputEditText;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.oauth.NidOAuthLogin;
import com.navercorp.nid.oauth.OAuthLoginCallback;
import com.navercorp.nid.oauth.view.NidOAuthLoginButton;
import com.navercorp.nid.profile.NidProfileCallback;
import com.navercorp.nid.profile.data.NidProfileResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "로그인";
    TextInputEditText tiedt_id, tiedt_pw;
    Button btn_login;
    TextView tv_join, loginName, pwFind;
    Switch chk_auto ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getHashKey();

        //뒤로가기-메인이동
        findViewById(R.id.imgv_login_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //아이디찾기
        tiedt_id = findViewById(R.id.tiedt_login_id );
        tiedt_pw = findViewById(R.id.tiedt_login_pw );
        btn_login = findViewById(R.id.btn_login_login );
        tv_join = findViewById(R.id.tv_login_join);
        chk_auto = findViewById(R.id.switch_login_autologin);

        SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);
        tiedt_id.setText(preferences.getString("id" , ""));
        tiedt_pw.setText(preferences.getString("pw" , ""));
        loginName = findViewById(R.id.tv_main_header_login);
        pwFind = findViewById(R.id.tv_login_pwFind);


        //SharedPreferences id, pw정보를 String에 담고 그값이 .length() > 3 & 만족하면
        //UserDAO dao = new UserDAO(LoginActivity.this); 하고 메인에서 Navigation에 회원정보 보이게만 수정.
        //비번찾기
        pwFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,PwFindActivity.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        //로그인버튼 클릭
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( tiedt_id.getText().toString().trim().length() < 1 ) {
                    Toast.makeText(LoginActivity.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    tiedt_id.requestFocus();
                    return;
                }else if(tiedt_pw.getText().toString().trim().length() < 1){
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    tiedt_pw.requestFocus();
                    return;
                }else{
                    UserDAO dao = new UserDAO(tiedt_id.getText().toString(),tiedt_pw.getText().toString());
                    if(dao.isUserLogin()){
                        checkAutoLogin();
                        goMain();

                    }else{
                        Toast.makeText(LoginActivity.this,"아이디 또는 비밀번호가 틀립니다",Toast.LENGTH_SHORT).show();
                        tiedt_id.setText("");
                        tiedt_pw.setText("");
                        tiedt_id.requestFocus();
                        chk_auto.setChecked(false);
                        checkAutoLogin();
                    }
                }
            }
        });
        if(tiedt_id.getText().toString().length() > 3 && tiedt_pw.getText().toString().length() > 3){
            //btn_login.performClick();
        }
        //네아로SDK를 애플리케이션에 적용하려면 네아로 객체를 초기화
        NaverIdLoginSDK.INSTANCE.initialize(
                this,"szRRJL0N7PYQvmPTLsqe",
                "sP8w3ahjpG",
                "AnaFor");

        //카카오SDK적용 초기화 네이티브앱키입력
        KakaoSdk.init(this,"8b43a4029fe7a1169d57ad185993d4f0");

        //네이버 버튼 가져옴
        NidOAuthLoginButton btn_naver = findViewById(R.id.btn_naver);

        //네이버 콜백, 로그인 성공시 토큰이 로그캣에 뜸
        btn_naver.setOAuthLoginCallback(new OAuthLoginCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG,"onSuccess: " + NaverIdLoginSDK.INSTANCE.getAccessToken());
                Log.d(TAG,"onSuccess: " + NaverIdLoginSDK.INSTANCE.getRefreshToken());
                getNaverProfile(); //프로필메소드 호출
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d(TAG,"onFailure: " + s);
            }

            @Override
            public void onError(int i, String s) {
                Log.d(TAG,"onError: " + s);
            }
        });

        //네이버 버튼 이미지 변경
        ImageView fakeNaver = (ImageView) findViewById(R.id.fake_naver);
        fakeNaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_naver.performClick(); //fakeNaver클릭시 btn_naver메소드호출
            }
        });

        //카카오 버튼 가져옴
        ImageView btn_kakao = findViewById(R.id.btn_login_kakao);

        //카카오 callback
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null){
                    Log.d(TAG,"카카오 토큰이 있음. 로그인 정보를 뺴오면 됨");
                    getKakaoProfile();

                }else{
                    Log.d(TAG,"카카오 토큰이 없음." + throwable.toString());
                }
                return null;
            }
        };

        //카카오 버튼 클릭시
        btn_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    Log.d(TAG,"카톡설치 되있음");
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this,callback);
                }else{
                    Log.d(TAG,"카톡 설치 X");
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this,callback);
                }
            }
        });

        //회원가입 화면으로 이동
        tv_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,JoinActivity.class);
                startActivity(intent);
            }
        });


    }

    //네이버 프로필 가져오기
    public void getNaverProfile(){ //<- AccessToken 있을때만 성공함. 토큰없으면 무조건 fail,error
        NidOAuthLogin authLogin = new NidOAuthLogin(); //초기화
        authLogin.callProfileApi(new NidProfileCallback<NidProfileResponse>() {
            @Override
            public void onSuccess(NidProfileResponse nidProfileResponse) {
                Log.d(TAG, "onSuccess 이름: " + nidProfileResponse.getProfile().getName());
                Log.d(TAG, "onSuccess 이메일: " + nidProfileResponse.getProfile().getEmail());
                Log.d(TAG, "onSuccess 전화번호: " + nidProfileResponse.getProfile().getMobile());
                tiedt_id.setText(null);
                tiedt_pw.setText(null);
                socialLogin(nidProfileResponse.getProfile().getEmail());
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d(TAG, "onFailure: " + s);
            }

            @Override
            public void onError(int i, String s) {
                Log.d(TAG, "onFailure: " + s);
            }
        });

    }

    //카카오 프로필 가져오기
    public void getKakaoProfile(){
        // 사용자 정보 요청 (기본)
        UserApiClient.getInstance().me(  (user, throwable) -> {
            if(throwable != null){
                Log.d(TAG, "onFailure: "+ throwable.toString());
                //오류가 났을때는 어떤 오류인지를 Kakao에서 제공해줌 . KOE + 숫자
            }else{
                // [ { } ] json 구조처럼 바로 데이터가 있는게 아니라 Accuount라는 키로 한칸을 들어감(오브젝트)
                //그안에서 profile을 얻어 올수가있음.
                Account account = user.getKakaoAccount();
                if(account != null){
                    Log.d(TAG, "onSuccess 이름: "+account.getProfile().getNickname());
                    Log.d(TAG, "onSuccess 이메일: "+account.getEmail());
                    socialLogin(account.getEmail());
                }
            }
            return null;
        });
    }

    //해시키
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

    // 로그인 했을때 메인액티비티로 이동
    public void goMain(){
       onBackPressed();
    }

    //자동로그인
    public void checkAutoLogin(){
        SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(chk_auto.isChecked()){
            editor.putString("id" , tiedt_id.getText().toString());
            editor.putString("pw" , tiedt_pw.getText().toString());
        }else{
            editor.clear();
        }
        editor.apply();
    }

    //1. 소셜로그인 시 회원 아이디에 없으면, 이메일을 가지고 회원가입 화면으로 이동 => 나머지 정보를 입력받고 회원가입 시킴.!
    public void socialLogin(String user_id){
        UserDAO dao = new UserDAO(user_id);
        Intent intent = null;
        if(dao.isSocialLogin() ){
            goMain();
        }else{
            intent = new Intent(LoginActivity.this , SocialJoinActivity.class);
            intent.putExtra("user_id"  , user_id);
            startActivity(intent);
            finish();
        }

    }

}