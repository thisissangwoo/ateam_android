package com.example.anafor.Hp_Information;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.Hp_Map.Hp_MapActivity;
import com.example.anafor.Hp_Review.Hp_ReviewAllActivity;
import com.example.anafor.Hp_Review.Hp_infoReviewFragment;
import com.example.anafor.Hp_Review.ReviewTotalVO;
import com.example.anafor.Hp_Review.ReviewVO;
import com.example.anafor.MainActivity;
import com.example.anafor.R;
import com.example.anafor.User.LoginActivity;
import com.example.anafor.gps.GpsTracker;
import com.example.anafor.utils.GetDate;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class Hp_InformationActivity extends AppCompatActivity implements MapView.POIItemEventListener {

    TabLayout hp_infor_tab_layout;
    ImageView imgv_hp_infor_back,imgv_hp_photo,imgv_hp_infor_home;
    TextView hp_infor_time, hp_infor_infor, hp_infor_review, tv_hp_today,tv_hp_todayTime,
            tv_hp_tlunch, tv_hp_name, tv_hp_addr , tv_hp_url, tv_hp_phone, tv_hp_wlunch,tv_hp_holi,
            tv_hp_mon, tv_hp_tue, tv_hp_wed, tv_hp_thu, tv_hp_fri, tv_hp_sat, tv_hp_sun, tv_hp_dlunch,
            tv_review_total, tv_review_rate, tv_review_mbtn, tv_total_survey1, tv_total_survey2, tv_total_survey3;

    ScrollView hp_infor_scview;
    Button btn_review;
    ImageView imgv_heartclick;
    String [] weekName = {"일요일","월요일","화요일","수요일","목요일","금요일","토요일"};  //요일
    TextView[] tv_time = { tv_hp_mon, tv_hp_tue, tv_hp_wed, tv_hp_thu, tv_hp_fri, tv_hp_sat,tv_hp_holi,tv_hp_dlunch,tv_hp_wlunch}; //8번째 마지막
    String [] weekTime;             //진료시간
    int today = GetDate.getCurrentWeek(); //오늘 요일
    Hp_infoDTO infoDTO;             //병원 상세 정보
    Gson gson;
    int flag = 0;               //상태 변수
    boolean heartclick = false; //조회 여부 확인 (default)
    LinearProgressIndicator pro_survey1, pro_survey2, pro_survey3;
    ReviewTotalVO totalReview = null ;
    ArrayList<ReviewVO> reviewList;
    MapView KakaoMapView;
    ViewGroup mMapViewContainer;
    private double mCurrentLng;     //경도
    private double mCurrentLat;     //위도


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp_information);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //프로그램 화면 세로 고정
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Intent intent = getIntent();
        infoDTO = (Hp_infoDTO) intent.getSerializableExtra("infoDTO"); //DTO 값 저장
        gson = new Gson();
        weekTime = new String[]{infoDTO.getStart_m(), infoDTO.getEnd_m(), infoDTO.getStart_t(), infoDTO.getEnd_t(), infoDTO.getStart_w(),
                infoDTO.getEnd_w(), infoDTO.getStart_th(), infoDTO.getEnd_th(), infoDTO.getStart_f(), infoDTO.getEnd_f(), infoDTO.getStart_s(), infoDTO.getEnd_s(),
                infoDTO.getClose_ho(), infoDTO.getLunch_d(), infoDTO.getLunch_w()};

        imgv_heartclick = findViewById(R.id.imgv_heartclick);               //찜하기 기능
        imgv_hp_photo = findViewById(R.id.imgv_hp_photo);               //병원 이미지

        //DB에 이미지파일이 있을때
        if(infoDTO.getFilepath()!=null){
            Glide.with(Hp_InformationActivity.this).load(infoDTO.getFilepath()).into(imgv_hp_photo);
        }

        hp_infor_scview = findViewById(R.id.hp_infor_scview);

        btn_review = findViewById(R.id.btn_review);                                 //리뷰 등록
        //탭 이동시 사용
        hp_infor_time = findViewById(R.id.hp_infor_time);                    //진료시간
        hp_infor_infor = findViewById(R.id.hp_infor_infor);                 //병원정보
        hp_infor_review = findViewById(R.id.hp_infor_review);                //병원 리뷰

        tv_hp_name = findViewById(R.id.tv_hp_name);                         //병원 이름
        tv_hp_addr = findViewById(R.id.tv_hp_addr);                         //병원 주소

        tv_hp_url = findViewById(R.id.tv_hp_url);                                        //병원 url
        tv_hp_phone = findViewById(R.id.tv_hp_phone);                        //병원 전화번호

        tv_hp_today = findViewById(R.id.tv_hp_today);                                 // 오늘 요일
        tv_hp_todayTime = findViewById(R.id.tv_hp_todayTime);               //오늘 진료시간
        tv_hp_tlunch =findViewById(R.id.tv_hp_tlunch);                              // 오늘 점심시간

        //요일별 진료시간
        tv_time[0] = findViewById(R.id.tv_hp_mon);                           //월요일
        tv_time[1] = findViewById(R.id.tv_hp_tue);                           //화요일
        tv_time[2] = findViewById(R.id.tv_hp_wed);                           //수요일
        tv_time[3] = findViewById(R.id.tv_hp_thu);                            //목요일
        tv_time[4] = findViewById(R.id.tv_hp_fri);                           //금요일
        tv_time[5] = findViewById(R.id.tv_hp_sat);                            //토요일
        tv_time[6] = findViewById(R.id.tv_hp_holi);                          //공휴일
        tv_time[7] = findViewById(R.id.tv_hp_dlunch);                      //평일 점심시간
        tv_time[8] = findViewById(R.id.tv_hp_wlunch);                      //주말 점심시간
        tv_hp_sun = findViewById(R.id.tv_hp_sun);                            //일요일

        tv_review_total = findViewById(R.id.tv_review_total);               //총 리뷰 수
        tv_review_rate = findViewById(R.id.tv_review_rate);                  //별점 평균
        tv_review_mbtn = findViewById(R.id.tv_review_mbtn);             //더보기 버튼

        tv_total_survey1 =findViewById(R.id.tv_total_survey1);          //병원이 깨끗했어요
        tv_total_survey2 = findViewById(R.id.tv_total_survey2);         //친절하게 진단해주셨어요
        tv_total_survey3 = findViewById(R.id.tv_total_survey3);         //부대시설 만족

        pro_survey1 = findViewById(R.id.pro_surevey1);
        pro_survey2 = findViewById(R.id.pro_surevey2);
        pro_survey3 = findViewById(R.id.pro_surevey3);

        tv_review_mbtn.setVisibility(View.GONE);
        writeTextView();                        //전체 진료시간 출력
        writeHpInfo();                          //병원 정보 출력
        initView();                                 //카카오 맵 상세 정보
        selectReviewtList();                //리뷰 정보 조회

        //더보기 버튼 클릭시 (리뷰 리스트 출력)
        tv_review_mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(Hp_InformationActivity.this, Hp_ReviewAllActivity.class);
                        intent.putExtra("reviewList",reviewList);
                        startActivity(intent);
            }
        });

        //로그인이 된 경우
        if(CommonVal.loginInfo !=null) {
            heartclick = gson.fromJson(aTask("check.heart"),boolean.class);           //해당병원을 찜했는지 확인
            if(heartclick){
                flag =1;
                imgv_heartclick.setImageResource(R.drawable.heartclick);
            }
        }

        //찜하기 기능
        imgv_heartclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CommonVal.loginInfo ==null) {          //비로그인상태 - 로그인이 필요한 기능
                    alertLogin();
                }else{                          //로그인상태
                    if (flag==1){    //찜한 상태
                        imgv_heartclick.setImageResource(R.drawable.heartnull);
                        flag= 0;
                    }else{          //찜하지 않은 상태
                        imgv_heartclick.setImageResource(R.drawable.heartclick);
                        flag = 1;
                    }
                }
            }
        });


        hp_infor_tab_layout = findViewById(R.id.hp_infor_tab_layout);  //탭 레이아웃
        imgv_hp_infor_back = findViewById(R.id.imgv_hp_infor_back);

        imgv_hp_infor_home = findViewById(R.id.imgv_hp_infor_home);
        imgv_hp_infor_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //뒤로 가기 했을때 한번만 DB에 값 전달
                if(heartclick){       //원래 값이 있었을때
                    if(flag == 1){                //찜한 날짜 바꿔서 업데이트
                        aTask("update.heart");
                    }else{                                 //찜한 병원 정보 삭제
                        aTask("delete.heart");
                    }
                }else{                        // 처음 찜한 상태
                    if(flag == 1){
                        aTask("insert.heart");
                    }
                }
                Intent intent2 = new Intent(Hp_InformationActivity.this,MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });

        imgv_hp_infor_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        hp_infor_tab_layout = findViewById(R.id.hp_infor_tab_layout);
        hp_infor_tab_layout.addTab(hp_infor_tab_layout.newTab().setText("진료시간").setId(0));
        hp_infor_tab_layout.addTab(hp_infor_tab_layout.newTab().setText("병원정보").setId(1));
        hp_infor_tab_layout.addTab(hp_infor_tab_layout.newTab().setText("리뷰").setId(2));

        //탭 클릭시 해당하는 위치로 이동
        hp_infor_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                if(position == 0){          //진료시간
                    hp_infor_scview.smoothScrollTo(0, hp_infor_time.getTop());
                }else if (position == 1){           //병원정보
                    hp_infor_scview.smoothScrollTo(0, hp_infor_infor.getTop());
                }else if (position == 2){           //리뷰
                    hp_infor_scview.smoothScrollTo(0, hp_infor_review.getTop());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //리뷰 등록 버튼 클릭시 리뷰 작성액티비티로 이동
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CommonVal.loginInfo != null){            //로그인 상태
                    Intent intent = new Intent(Hp_InformationActivity.this, Hp_InformationReviewActivity.class);
                    intent.putExtra("hp_name",infoDTO.getHp_name()); //병원 이름
                    intent.putExtra("hp_code",infoDTO.getHp_code());    //병원 코드 
                    startActivityForResult(intent , 100);
                }else{
                    alertLogin();                           //비로그인 상태일때
                }

            }
        });
    }

    //전체 진료시간 TextView에 작성하는 메소드
    public void writeTextView(){

        //오늘 진료시간
        tv_hp_today.setText(weekName[today]); //오늘 요일

        //오늘 점심시간
        if(today == 6 || today == 0){     //주말일때
            if(infoDTO.getLunch_w()!=null){
                tv_hp_tlunch.setText(infoDTO.getLunch_w());
            }
        }else{
            if(infoDTO.getLunch_d()!=null){         //평일일때
                tv_hp_tlunch.setText(infoDTO.getLunch_d());
            }
        }

        //전제 진료시간 출력
        int cnt = 0;
        for(int i = 0; i<9; i++){
            if(i<6){               //월~토요일
                if(today == i+1){
                    if(weekTime[cnt]!= null && weekTime[cnt+1]!= null){
                     //   Log.d("today@@@@@@", i+" "+today+"  writeTextView: "+cnt);
                        tv_hp_todayTime.setText(weekTime[cnt]+"~"+weekTime[cnt+1]);
                    }
                }
                if(weekTime[cnt]!= null && weekTime[cnt+1] != null){
                       // Log.d("@@@@@@@@", i+"  writeTextView: "+cnt);
                    tv_time[i].setText(weekTime[cnt]+"~"+weekTime[cnt+1]);
                }
                cnt+=2;
            }else{                              //평일, 주말점심시간
                if(weekTime[cnt]!= null){
                       //  Log.d("@@@@@@@@", i+"  writeTextView: "+cnt);
                    tv_time[i].setText(weekTime[cnt]);
                }
                cnt++;
            }
        }

        //일요일은 휴무이거나 휴무가 아닌 병원이 존재
        if(infoDTO.getStart_su()!= null && infoDTO.getEnd_su()!= null){  //일요일 진료
            if(today==0){
                tv_hp_todayTime.setText(infoDTO.getStart_su()+"~"+infoDTO.getEnd_su());
            }
            tv_hp_sun.setText(infoDTO.getStart_su()+"~"+infoDTO.getEnd_su());

        }else if (infoDTO.getClose_su()!= null){                        //일요일 휴무
            if(today==0){
                tv_hp_todayTime.setText(infoDTO.getClose_su());
            }
            tv_hp_sun.setText(infoDTO.getClose_su());
        }
    }

    //병원 정보 출력(이름, 주소, 전화번호,  사이트 링크)
    public void writeHpInfo(){
        //병원 정보 띄우기
        tv_hp_name.setText(infoDTO.getHp_name());
        tv_hp_addr.setText(infoDTO.getHp_addr());

        // 병원 사이트 링크가 존재할 경우 해당 링크로 이동
        if(infoDTO.getHp_url()!= null){
            tv_hp_url.setText(infoDTO.getHp_url());
            tv_hp_url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                    urlIntent.setData(Uri.parse(tv_hp_url.getText().toString()));
                    startActivity(urlIntent);
                }
            });
        }
        //병원 전화번호가 존재할 경우 전화 걸기 기능
        if(infoDTO.getHp_tel()!= null){
            tv_hp_phone.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_call_24,0,0,0);
            tv_hp_phone.setText(infoDTO.getHp_tel());
            tv_hp_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent phIntent = new Intent(Intent.ACTION_VIEW);
                    phIntent.setData(Uri.parse("tel:"+tv_hp_phone.getText().toString()));
                    startActivity(phIntent);
                }
            });
        }

    }

    //Spring 통신(찜하기)
    public InputStreamReader aTask (String mapping){
        AskTask task = new AskTask(mapping);
        task.addParam("user_id", CommonVal.loginInfo.getUser_id());
        task.addParam("hp_code", infoDTO.getHp_code());
        return CommonMethod.executeAskGet(task);
    }

    //리뷰 조회
    public void selectReviewtList(){
            //전체 리뷰 총합계 조회
            AskTask task = new AskTask("total.review");
            task.addParam("code",infoDTO.getHp_code());
            totalReview = gson.fromJson(CommonMethod.executeAskGet(task),ReviewTotalVO.class);
            if(totalReview != null){
                AskTask  task2 = new AskTask("selectAll.review");
                task2.addParam("code",infoDTO.getHp_code());
                reviewList = gson.fromJson(CommonMethod.executeAskGet(task2), new TypeToken<ArrayList<ReviewVO>>() {}.getType());
                countReview();            //해당 병원 전체 리뷰 조회
            }
        getSupportFragmentManager().beginTransaction().replace(R.id.container_hp_reivew,new Hp_infoReviewFragment(reviewList)).commit();
    }
    public void countReview(){
        tv_review_total.setText("리뷰  "+totalReview.getTotalcnt()+" 개");             //총 리뷰 수
        tv_total_survey1.setText("("+totalReview.getSurvey1cnt()+")");
        tv_total_survey2.setText("("+totalReview.getSurvey2cnt()+")");
        tv_total_survey3.setText("("+totalReview.getSurvey3cnt()+")");
        tv_review_rate.setText(totalReview.getTotalrate()+" 점");
        pro_survey1.setProgressCompat((int)(totalReview.getSurvey1rate()*100.0),false);
        pro_survey2.setProgressCompat((int)(totalReview.getSurvey2rate()*100.0),false);
        pro_survey3.setProgressCompat((int)(totalReview.getSurvey3rate()*100.0),false);
        if(totalReview.getTotalcnt() == 0){                 //더보기 버튼 숨김 처리
            tv_review_mbtn.setVisibility(View.GONE);
        }else{
            tv_review_mbtn.setVisibility(View.VISIBLE);
        }
    }
    
    @Override
    public void onBackPressed() {

              //뒤로 가기 했을때 한번만 DB에 값 전달
            if(heartclick){       //원래 값이 있었을때
                if(flag == 1){                //찜한 날짜 바꿔서 업데이트
                    aTask("update.heart");
                }else{                                 //찜한 병원 정보 삭제
                aTask("delete.heart");
                }
            }else{                        // 처음 찜한 상태
                if(flag == 1){
                    aTask("insert.heart");
                }
            }
            finish();
    }
    //비로그인상태일때 로그인해야 이용가능하다는 알림
    public void alertLogin(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Hp_InformationActivity.this);
        builder.setTitle("로그인");
        builder.setMessage("로그인이 필요한 기능입니다. 로그인 하시겠습니까?");
        builder.setPositiveButton("로그인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent loginIntent = new Intent(Hp_InformationActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //카카오맵 실행 (길찾기할때)
    public void showMap(Uri geoLocation) {
        Intent intent2;
        try {
            intent2 = new Intent(Intent.ACTION_VIEW, geoLocation);
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        } catch (Exception e) {
            intent2 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/apps/details?id=net.daum.android.map&hl=ko"));
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode == RESULT_OK){
            selectReviewtList();
        }
    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        
    }

    //마커 말풍선 클릭했을때
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        double lat = mapPOIItem.getMapPoint().getMapPointGeoCoord().latitude;
        double lng = mapPOIItem.getMapPoint().getMapPointGeoCoord().longitude;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("길찾기");
        builder.setMessage("길찾기를 원하시면 확인 버튼을 눌러주세요");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMap(Uri.parse("daummaps://route?sp=" + mCurrentLat + "," + mCurrentLng + "&ep=" + lat + "," + lng + "&by=FOOT"));
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapViewContainer.removeView(KakaoMapView);
        Log.d("", "onPause: ");
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //if (mMapViewContainer.is) {
        if(mMapViewContainer.getChildCount() == 0){
            initView();
        }else{
            mMapViewContainer.removeView(KakaoMapView);
        }

    }

    
    //처음에 맵 시작하는 함수
    public void initView(){

        KakaoMapView = new MapView(this);
        mMapViewContainer = findViewById(R.id.KakaoMapView);
        mMapViewContainer.addView(KakaoMapView);
        GpsTracker gpsTracker = new GpsTracker(Hp_InformationActivity.this);
        mCurrentLat = gpsTracker.getLatitude();
        mCurrentLng = gpsTracker.getLongitude();
        KakaoMapView.setPOIItemEventListener(this);
        if(infoDTO.getHp_x() != null && infoDTO.getHp_y()!=null){       //병원의 좌표가 null이 아닐때 마커를 찍음
            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(Double.parseDouble(infoDTO.getHp_y()),Double.parseDouble(infoDTO.getHp_x()));
            KakaoMapView.setMapCenterPoint(mapPoint,true);  //지도 중심점 병원 좌표로
            KakaoMapView.setZoomLevel(1,true); //값이 작을수록 더 확대됨
            MapPOIItem marker = new MapPOIItem();
            marker.setMapPoint(mapPoint);
            marker.setItemName(infoDTO.getHp_name());
            marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
            KakaoMapView.addPOIItem(marker);
        }


    }

}

