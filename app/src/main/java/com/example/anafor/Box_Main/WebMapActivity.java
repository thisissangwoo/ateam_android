package com.example.anafor.Box_Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.anafor.Common.CommonVal;
import com.example.anafor.R;

public class WebMapActivity extends AppCompatActivity {
    WebView webView;                            //웹뷰 선언
    WebSettings mWebSettings;           //웹뷰 세팅
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_map);
        webView = findViewById(R.id.webView);
        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);                         //웹페이지 자바 스크립트 허용 여부
        mWebSettings.setLoadWithOverviewMode(true);              //메타태그허용여부
        mWebSettings.setUseWideViewPort(true);                          //화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(false);                                 //화면 줌 퍼용 여부
        mWebSettings.setBuiltInZoomControls(false);                     //화면 확대 축소 허용 여부
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);     //컨텐츠 사이즈 맞추기
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);           //브라우저 캐시 허용 여부
        mWebSettings.setDomStorageEnabled(true);                                //로컬 저장소 허용 여부
        String url = "http://192.168.0.34/anafor/iotmobilemap?user_id="+ CommonVal.loginInfo.getUser_id();
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
        Toast.makeText(this, "해당 경로는 현재 기준 3일전까지만 확인 가능합니다.", Toast.LENGTH_SHORT).show();
    }
}