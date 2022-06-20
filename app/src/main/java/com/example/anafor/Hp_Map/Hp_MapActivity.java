package com.example.anafor.Hp_Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anafor.R;
import com.example.anafor.api.ApiClient;
import com.example.anafor.api.ApiInterface;
import com.example.anafor.api.ResultSearchKeyword;
import com.example.anafor.gps.GpsTracker;
import com.example.anafor.utils.BusProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.otto.Bus;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hp_MapActivity extends AppCompatActivity implements MapView.MapViewEventListener, MapView.POIItemEventListener, MapView.CurrentLocationEventListener {

    ImageView imgv_hp_map_back;
    final String restapi_key = "KakaoAK 332e22f7f60243ff3e09b710e7cfd590";
    private long backTime = 0;
    private GpsTracker gpsTracker;          //gps provider, network provider 사용
    MapPOIItem currentMarker;              //현재위치 특정마커
    MapView mMapView;                                   //카카오 맵
    ViewGroup mMapViewContainer;
    FloatingActionButton btn_fab;                   //플로팅 버튼
    MapPoint currentMapPoint;                       //현재 위치
    private double mCurrentLng;                     //경도
    private double mCurrentLat;                     //위도
    String category = "";                                     //검색할 진료과목
    Bus bus = BusProvider.getInstance();
    ResultSearchKeyword searchResult = new ResultSearchKeyword();  //REST API 통신 후 응답 저장
    ArrayList<MapPOIItem> markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //프로그램 화면 세로 고정
        setContentView(R.layout.activity_hp_map);
        imgv_hp_map_back = findViewById(R.id.imgv_hp_map_back);

        Intent intent = getIntent();
        category = intent.getStringExtra("subject");
        Log.d("검색할 카테고리", "onCreate: "+category);
        bus.register(this);

        initView();
        btn_fab = findViewById(R.id.btn_fab);
        imgv_hp_map_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //플로팅 버튼 클릭시 현재 위치로 지도 중앙 이동

        btn_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCurrentMarker();
                selectList(mCurrentLat, mCurrentLng, category);
            }
        });

    }

    public void initView(){

        currentMarker = new MapPOIItem();  //현재 위치 마커
        mMapView = new MapView(this);
        mMapViewContainer = findViewById(R.id.view_map);
        mMapViewContainer.addView(mMapView);

        mMapView.setMapViewEventListener(this);
        mMapView.setPOIItemEventListener(this);
        mMapView.setCurrentLocationEventListener(this);

        //현재위치 좌표 추적
        gpsTracker = new GpsTracker(Hp_MapActivity.this);
        makeCurrentMarker();   //현재 위치 마커 찍기
        selectList(mCurrentLat,mCurrentLng,category);           //진료과목별 조회

    }


    //현재 위치 마커 찍기 (커스텀마커 사용)
    public void makeCurrentMarker(){
        mCurrentLng = gpsTracker.getLongitude();
        mCurrentLat = gpsTracker.getLatitude();
        currentMapPoint = MapPoint.mapPointWithGeoCoord(mCurrentLat,mCurrentLng);
        mMapView.setMapCenterPoint(currentMapPoint, true);   //현재 좌표로 지도 중심 이동
        currentMarker.setMapPoint(currentMapPoint);    //현재 좌표로 지도 중심 이동
        currentMarker.setTag(0);
        currentMarker.setItemName("현재위치");
        currentMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        currentMarker.setCustomImageResourceId(R.drawable.curloc64);
        currentMarker.setCustomImageAutoscale(true);       //해상도에 맞게 조절(true) , false : 사이즈 고정
        mMapView.addPOIItem(currentMarker);
    }



    //키워드 검색 메소드
    public void selectList(double mCurrentLat, double mCurrentLng, String category){
        for (int i = 0; i<markers.size(); i++){
            mMapView.removePOIItem(markers.get(i));
        }
        markers.clear();

        //키워드검색
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResultSearchKeyword> calls = apiInterface.getSearchLocationDetail(restapi_key, category,String.valueOf(mCurrentLng), String.valueOf(mCurrentLat),15);
        calls.enqueue(new Callback<ResultSearchKeyword>() {
            @Override
            public void onResponse(@NonNull Call<ResultSearchKeyword> call, @NonNull Response<ResultSearchKeyword> response) {
                searchResult = response.body();
                List<ResultSearchKeyword.Documents> list = searchResult.documents;
                int tag = 1;  //마커의 태그 따로 저장
                for (ResultSearchKeyword.Documents doc : list) {
                    MapPOIItem marker = new MapPOIItem();
                    Log.d("@@@@", "onResponse: "+doc.getRoad_address_name());
                    marker.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(doc.getY()), Double.parseDouble(doc.getX())));
                    marker.setItemName(doc.getPlace_name());
                    marker.setTag(tag);
                    marker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                    marker.setCustomImageResourceId(R.drawable.hppoint);
                    marker.setCustomImageAutoscale(true);       //해상도에 맞게 조절(true) , false : 사이즈 고정
                    markers.add(marker);
                    tag++;
                }
                mMapView.addPOIItems(markers.toArray(new MapPOIItem[markers.size()]));
            }

            @Override
            public void onFailure(Call<ResultSearchKeyword> call, Throwable t) {

            }
        });

    }

    //카카오맵 실행 (길찾기할때)
    public void showMap(Uri geoLocation) {
        Intent intent;
        try {
            intent = new Intent(Intent.ACTION_VIEW, geoLocation);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/apps/details?id=net.daum.android.map&hl=ko"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
        makeCurrentMarker();
        selectList(mCurrentLat, mCurrentLng, category);
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {
        makeCurrentMarker();
        selectList(mCurrentLat, mCurrentLng, category);
    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {
        makeCurrentMarker();
        selectList(mCurrentLat, mCurrentLng, category);
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }


    //마커 클릭시
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        //진료과목을 조회하여 찍은 마커일 경우
        if(mapPOIItem.getTag()!=0){

            String place_name =  searchResult.documents.get(mapPOIItem.getTag()-1).getPlace_name();
            String distance =  searchResult.documents.get(mapPOIItem.getTag()-1).getDistance();
            String roadAddr =  searchResult.documents.get(mapPOIItem.getTag()-1).getRoad_address_name();
            String phone = searchResult.documents.get(mapPOIItem.getTag()-1).getPhone();
            BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialog(this,place_name,distance,roadAddr,phone);
            bottomSheetDialogFragment.show(getSupportFragmentManager(),"bsdetail");
        }
    }

    //마커 말풍선 클릭했을 때
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        double lat = mapPOIItem.getMapPoint().getMapPointGeoCoord().latitude;
        double lng = mapPOIItem.getMapPoint().getMapPointGeoCoord().longitude;

        //병원 마커를 클릭했을때
        if (mapPOIItem.getTag() != 0) {
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
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }

    @Override
    public void finish() {
        super.finish();
        bus.register(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.setShowCurrentLocationMarker(false);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        //if (mMapViewContainer.is) {
        if(mMapViewContainer.getChildCount() == 0){
            initView();
        }else{
            mMapViewContainer.removeView(mMapView);
        }

    }



    @Override
    protected void onPause() {
       super.onPause();

        mMapViewContainer.removeView(mMapView);
        Log.d("", "onPause: ");
    }


    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() > backTime + 2000){
            backTime = System.currentTimeMillis();
            Toast.makeText(this, "한 번 더 누르면 맵이 꺼집니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(System.currentTimeMillis() <= backTime + 2000){
            finish();
        }
    }
}