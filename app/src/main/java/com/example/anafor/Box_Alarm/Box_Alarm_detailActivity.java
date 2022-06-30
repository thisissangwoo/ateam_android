package com.example.anafor.Box_Alarm;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.Hp_Information.Hp_InformationActivity;
import com.example.anafor.Hp_Information.Hp_InformationReviewActivity;
import com.example.anafor.MainActivity;
import com.example.anafor.R;
import com.example.anafor.User.UserVO;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Box_Alarm_detailActivity extends AppCompatActivity {
    
    private static final String TAG = "알람저장";
    
    ImageView imgv_box_detail_back;
    Button btn_box_detail_cancel, btn_box_detail_insert;
    EditText edt_box_alarm_content;
    CheckBox btn_box_alarm_location1, btn_box_alarm_location2, btn_box_alarm_location3, btn_box_alarm_location4;
    String case_number,box_time,box_minute,case_time;
    TextView tv_box_detail_blue;

    //블루투스======================================
    private ActivityResultLauncher<Void> overlayPermissionLauncher;

    // 사용자 정의 함수로 블루투스 활성 상태의 변경 결과를 App으로 알려줄때 식별자로 사용됨(0보다 커야함)
    static final int REQUEST_ENABLE_BT = 10;
    int mPariedDeviceCount = 0;
    Set<BluetoothDevice> mDevices;

    BluetoothAdapter mBluetoothAdapter;

    BluetoothDevice mRemoteDevie;

    // 스마트폰과 페어링 된 디바이스간 통신 채널에 대응 하는 BluetoothSocket
    BluetoothSocket mSocket = null;
    OutputStream mOutputStream = null;
    InputStream mInputStream = null;

    String mStrDelimiter = "\n";
    char mCharDelimiter = '\n';


    Thread mWorkerThread = null;
    byte[] readBuffer;
    int readBufferPosition;


    //푸시알림=================================================
    private TimePicker timePicker;
    private Object UserVO;


    CountDownTimer CDT;
    int drugCount = 0;
    Handler handler;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_alarm_detail);

        registerReceiver(broadcastReceiver, new IntentFilter("sendData"));


        handler = new Handler();


        timePicker=(TimePicker)findViewById(R.id.time_picker);
        imgv_box_detail_back = findViewById(R.id.imgv_box_detail_back);
        btn_box_alarm_location1 = findViewById(R.id.btn_box_alarm_location1);
        btn_box_alarm_location2 = findViewById(R.id.btn_box_alarm_location2);
        btn_box_alarm_location3 = findViewById(R.id.btn_box_alarm_location3);
        btn_box_alarm_location4 = findViewById(R.id.btn_box_alarm_location4);
        tv_box_detail_blue = findViewById(R.id.tv_box_detail_blue);
        btn_box_detail_insert = findViewById(R.id.btn_box_detail_insert);
        btn_box_detail_cancel = findViewById(R.id.btn_box_detail_cancel);
        edt_box_alarm_content = findViewById(R.id.edt_box_alarm_content);

        imgv_box_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        tv_box_detail_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBluetooth();
            }
        });

        // 알람 저장(insert)을 위한 값이 없을 때 또는 내용 없이 공백일 때 유무의 유효성 검사 후
        // 다시 작성을 해야하는 부분에 포커스 주기
        // 유효성 검사를 거친 후 통과가 되면 등록 되었다는 Toast 를 띄워줌과 동시에
        // 이 전 화면(Box_AlarmActivity)으로 넘어가게끔 intent 처리

        //알람시간에 보내기=================================================================================

        btn_box_alarm_location1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    case_number="1";
                    btn_box_alarm_location2.setChecked(false);
                    btn_box_alarm_location3.setChecked(false);
                    btn_box_alarm_location4.setChecked(false);
                }
            }
        });

        btn_box_alarm_location2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    case_number="2";
                    btn_box_alarm_location1.setChecked(false);
                    btn_box_alarm_location3.setChecked(false);
                    btn_box_alarm_location4.setChecked(false);
                }
            }
        });

        btn_box_alarm_location3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    case_number="3";
                    btn_box_alarm_location1.setChecked(false);
                    btn_box_alarm_location2.setChecked(false);
                    btn_box_alarm_location4.setChecked(false);
                }
            }
        });

        btn_box_alarm_location4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    case_number="4";
                    btn_box_alarm_location1.setChecked(false);
                    btn_box_alarm_location2.setChecked(false);
                    btn_box_alarm_location3.setChecked(false);
                }
            }
        });

        btn_box_detail_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //시간설정
        timePicker.setIs24HourView(true);

        timePicker.setHour(0);
        timePicker.setMinute(0);
        case_time = "00시00분";

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //캘린더
                Calendar calendar = Calendar.getInstance();
                //타임피커에서 설정한 시간을 캘린더에 저장
                hourOfDay=timePicker.getHour();
                minute = timePicker.getMinute();
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);


/*                String ampm = "오전";


                if (hourOfDay > 12) {
                    hourOfDay = hourOfDay - 12;
                   ampm = "오후";
                    if (hourOfDay <= 10) {
                        box_time = "0" + hourOfDay;
                    }
                }else if(hourOfDay == 12){
                    box_time = hourOfDay+"";
                    ampm ="오후";
                }else{
                    ampm = "오전";
                    if (hourOfDay <=10){
                        box_time = "0"+ hourOfDay;
                    }
                }
*/

                if (hourOfDay >= 10){
                    box_time = hourOfDay + "";
                }else {
                    box_time = "0" + hourOfDay;
                }

                if (minute >= 10) {
                    box_minute = minute + "";
                }else {
                    box_minute = "0" + minute;
                }

                case_time = box_time+ "시" + box_minute + "분";
            }
        });



        //권한 허용 부분 저장 버튼 클릭
        btn_box_detail_insert.setOnClickListener(v -> {
            //권한이 허용됬을때 setAlarm메소드 실행
            if(checkPermissionOverlay(this)) {
                setAlarm();
            }else{
                Toast.makeText(Box_Alarm_detailActivity.this, "권한을 허용해주세요.", Toast.LENGTH_SHORT).show();
            }

            //유효성검사
            if (edt_box_alarm_content.getText().toString().length() == 0 || edt_box_alarm_content.getText().toString().equals(" ")) {
                Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                edt_box_alarm_content.requestFocus();
            }else if(case_time == null){
                Toast.makeText(getApplicationContext(), "시간을 설정해주세요.", Toast.LENGTH_SHORT).show();
            }else if(case_number == null){
                Toast.makeText(getApplicationContext(), "알약의 위치를 지정해주세요.", Toast.LENGTH_SHORT).show();
            }else{
                AskTask task = new AskTask("/iot_insert");
                task.addParam("user_id", CommonVal.loginInfo.getUser_id());
                task.addParam("memo",edt_box_alarm_content.getText().toString());
                task.addParam("case_num",case_number);
                task.addParam("case_time",case_time);
                CommonMethod.executeAskGet(task);

                Intent intent = new Intent(Box_Alarm_detailActivity.this, Box_AlarmActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "알람이 등록되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }//OnCreate

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            sendData("g");         // 상태 확인문자 우노보드로 보내기
            try {
                Thread.sleep(1000); // 잠시대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            beginListenForData();  // 상태 확인*/
            countDownTimer();
            CDT.start();
            Log.d(TAG, "onReceive: CDT 시작");
        }
    };

    //퍼미션 권한 체크 부분
    private final int OVERRAY_REQ_CODE = 1111;
    public  boolean checkPermissionOverlay(Context context) {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && Settings.canDrawOverlays(context) ){ //<=권한이 허용되었는지를 체크
            return true;
        }else{
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent,  OVERRAY_REQ_CODE);
        }
        return false;
    }

    //이부분 이해안됨 https://developer.android.com/training/scheduling/alarms?hl=ko#java
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setAlarm() {
        //캘린더
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));//5초후에 알람을 보냄 테스트 완료 후 시간값으로 바꾸게끔 수정.
        //타임피커에서 설정한 시간을 캘린더에 저장
        int hour=timePicker.getHour();
        int minute=timePicker.getMinute();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);

        //만약 현재시간 보다 전이라면 다음날부터 실행
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }



        AlarmManager alarmManager=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            Intent intent = new Intent(this, AlarmReceiver.class);

            PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);


            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, alarmIntent);

/*            Toast.makeText(Box_Alarm_detailActivity.this,"알람이 저장되었습니다.",Toast.LENGTH_LONG).show();*/
        }//if

    }//setAlarm

    ///////////Bluethooth///////////////////////////////////////////////////////////
    @SuppressLint("MissingPermission")
    void checkBluetooth() {
        /**
         * getDefaultAdapter() : 만일 폰에 블루투스 모듈이 없으면 null 을 리턴한다.
         이경우 Toast를 사용해 에러메시지를 표시하고 앱을 종료한다.
         */
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {  // 블루투스 미지원
            Toast.makeText(getApplicationContext(), "기기가 블루투스를 지원하지 않습니다.", Toast.LENGTH_LONG).show();
//            finish();  // 앱종료
        } else { // 블루투스 지원
            /** isEnable() : 블루투스 모듈이 활성화 되었는지 확인.
             *               true : 지원 ,  false : 미지원
             */
            if (!mBluetoothAdapter.isEnabled()) { // 블루투스 지원하며 비활성 상태인 경우.
                Toast.makeText(getApplicationContext(), "현재 블루투스가 비활성 상태입니다.", Toast.LENGTH_LONG).show();
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                // REQUEST_ENABLE_BT : 블루투스 활성 상태의 변경 결과를 App 으로 알려줄 때 식별자로 사용(0이상)
                /**
                 startActivityForResult 함수 호출후 다이얼로그가 나타남
                 "예" 를 선택하면 시스템의 블루투스 장치를 활성화 시키고
                 "아니오" 를 선택하면 비활성화 상태를 유지 한다.
                 선택 결과는 onActivityResult 콜백 함수에서 확인할 수 있다.
                 */
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            } else // 블루투스 지원하며 활성 상태인 경우.
                selectDevice();
        }
    }

    // 블루투스 지원하며 활성 상태인 경우.
    @SuppressLint("MissingPermission")
    void selectDevice() {
        // 블루투스 디바이스는 연결해서 사용하기 전에 먼저 페어링 되어야만 한다
        // getBondedDevices() : 페어링된 장치 목록 얻어오는 함수.
        mDevices = mBluetoothAdapter.getBondedDevices();
        mPariedDeviceCount = mDevices.size();

        if (mPariedDeviceCount == 0) { // 페어링된 장치가 없는 경우.
            Toast.makeText(getApplicationContext(), "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
//            finish(); // App 종료.
        }
        // 페어링된 장치가 있는 경우.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("블루투스 장치 선택");

        // 각 디바이스는 이름과(서로 다른) 주소를 가진다. 페어링 된 디바이스들을 표시한다.
        List<String> listItems = new ArrayList<String>();

        //페어링된 기기 쿼리
        for (BluetoothDevice device : mDevices) {
            // device.getName() : 단말기의 Bluetooth Adapter 이름을 반환.
            listItems.add(device.getName());
        }

        listItems.add("취소");  // 취소 항목 추가.

        // CharSequence : 변경 가능한 문자열.
        // toArray : List형태로 넘어온것 배열로 바꿔서 처리하기 위한 toArray() 함수.
        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);
        // toArray 함수를 이용해서 size만큼 배열이 생성 되었다.
        listItems.toArray(new CharSequence[listItems.size()]);

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                // TODO Auto-generated method stub
                if (item == mPariedDeviceCount) { // 연결할 장치를 선택하지 않고 '취소' 를 누른 경우.
                    Toast.makeText(getApplicationContext(), "연결할 장치를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
//                    finish();
                } else { // 연결할 장치를 선택한 경우, 선택한 장치와 연결을 시도함.
                    connectToSelectedDevice(items[item].toString());
                }
            }

        });

        builder.setCancelable(false);  // 뒤로 가기 버튼 사용 금지.
        AlertDialog alert = builder.create();
        alert.show();
    }

    //  connectToSelectedDevice() : 원격 장치와 연결하는 과정을 나타냄.
    //   실제 데이터 송수신을 위해서는 소켓으로부터 입출력 스트림을 얻고 입출력 스트림을 이용하여 이루어 진다.
    @SuppressLint("MissingPermission")
    void connectToSelectedDevice(String selectedDeviceName) {
        // BluetoothDevice 원격 블루투스 기기를 나타냄.
        mRemoteDevie = getDeviceFromBondedList(selectedDeviceName);
        // java.util.UUID.fromString : 자바에서 중복되지 않는 Unique 키 생성.
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        try {
            // 소켓 생성, RFCOMM 채널을 통한 연결.
            // createRfcommSocketToServiceRecord(uuid) : 이 함수를 사용하여 원격 블루투스 장치와
            //                                           통신할 수 있는 소켓을 생성함.
            // 이 메소드가 성공하면 스마트폰과 페어링 된 디바이스간 통신 채널에 대응하는
            //  BluetoothSocket 오브젝트를 리턴함.

            mSocket = mRemoteDevie.createRfcommSocketToServiceRecord(uuid);
            mSocket.connect(); // 소켓이 생성 되면 connect() 함수를 호출함으로써 두기기의 연결은 완료된다.

            // 데이터 송수신을 위한 스트림 얻기.
            // BluetoothSocket 오브젝트는 두개의 Stream을 제공한다.
            // 1. 데이터를 보내기 위한 OutputStrem
            // 2. 데이터를 받기 위한 InputStream
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();

            // 데이터 수신 준비.
            //beginListenForData();

        } catch (Exception e) { // 블루투스 연결 중 오류 발생
            Toast.makeText(getApplicationContext(),
                    "블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
//            finish();  // App 종료
        }
    }

    // 우노보드로 데이터 보내기
    public void sendData(String msg) {
        try {
            this.mOutputStream.write(new StringBuilder(String.valueOf(msg)).append(this.mStrDelimiter).toString().getBytes());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "\ub370\uc774\ud130 \uc804\uc1a1 \uc911 \uc624\ub958\uac00 \ubc1c\uc0dd\ud588\uc2b5\ub2c8\ub2e4.", Toast.LENGTH_SHORT).show();

        }
    }

    public void beginListenForData() {

        readBufferPosition = 0;                 // 버퍼 내 수신 문자 저장 위치.
        readBuffer = new byte[1024];            // 수신 버퍼.
        Log.d("beginListenForData", "1");
        // 문자열 수신 쓰레드.
        mWorkerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("beginListenForData", "2");
                // interrupt() 메소드를 이용 스레드를 종료시키는 예제이다.
                // interrupt() 메소드는 하던 일을 멈추는 메소드이다.
                // isInterrupted() 메소드를 사용하여 멈추었을 경우 반복문을 나가서 스레드가 종료하게 된다.
//                while (!Thread.currentThread().isInterrupted()) {
                try {
                    // InputStream.available() : 다른 스레드에서 blocking 하기 전까지 읽은 수 있는 문자열 개수를 반환함.
                    int byteAvailable = mInputStream.available();   // 수신 데이터 확인

                    Log.d("byteAvailable", byteAvailable + "");

                    if (byteAvailable > 0) {                        // 데이터가 수신된 경우.
                        byte[] packetBytes = new byte[byteAvailable];
                        // read(buf[]) : 입력스트림에서 buf[] 크기만큼 읽어서 저장 없을 경우에 -1 리턴.

                        //                       Log.d("byteAvailable", String.valueOf(byteAvailable));
                        Log.d("packetBytes", packetBytes.length + "");

                        mInputStream.read(packetBytes);
                        //                           readBufferPosition = 0;
                        for (int i = 0; i < byteAvailable; i++) {
                            byte b = packetBytes[i];
                            if (b == mCharDelimiter) {

                                Log.d("b", "b if : " + (int) b);
//                                readBuffer[readBufferPosition++] = b;
                                byte[] encodedBytes = new byte[readBufferPosition];
                                //  System.arraycopy(복사할 배열, 복사시작점, 복사된 배열, 붙이기 시작점, 복사할 개수)
                                //  readBuffer 배열을 처음 부터 끝까지 encodedBytes 배열로 복사.
                                System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);

                                final String data = new String(encodedBytes, "US-ASCII");

                                //                                   Log.d("data", data + "\n");

                                readBufferPosition = 0;

                                handler.post(new Runnable() {
                                    // 수신된 문자열 데이터에 대한 처리.
                                    @Override
                                    public void run() {
                                        // mStrDelimiter = '\n';
//                                            receiveHeart.setText(data.substring(0, 3));
//                                            receiveTemperature.setText(data.substring(3, 8));
                                        Log.d("run", "문자 처리");
                                        Log.d("ACAC", "" + data.length());

                                        String tempData =  data.substring(0, 1);
                                        Log.d("tempData", tempData);

                                        if(tempData.equals("p")){
                                            CDT.cancel();
                                            Log.d("Main","약을 먹었습니다.");

                                            //약 먹었을때 기록 저장==============================================================================


                                            AskTask task = new AskTask("/iot_recode");
                                            task.addParam("user_id", CommonVal.loginInfo.getUser_id());
                                            task.addParam("case_number",case_number);
                                            CommonMethod.executeAskGet(task);





                                            //==============================================================================


                                        }else if(tempData.equals("n")){


                                        }


                                    }

                                });

                                break;

                            } else {
                                readBuffer[readBufferPosition++] = b;
                                Log.d("b", "b else : " + (int) b + " / readBufferPosition : " + readBufferPosition);
                            }


                        }
                    }

                } catch (Exception e) {    // 데이터 수신 중 오류 발생.
                    Log.d("Error ", e.getMessage());
                    //                       Toast.makeText(getApplicationContext(), "데이터 수신 중 오류가 발생 했습니다.", Toast.LENGTH_LONG).show();
//                        finish();            // App 종료.
                }
                //               } //while문 종료
            }

        });
        mWorkerThread.start();
        try {
            mWorkerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void countDownTimer(){
        // 반복하는 부분 몇분동안 신호 보내고 있을지
        CDT = new CountDownTimer(300 * 1000, 30 * 1000) {
            public void onTick(long millisUntilFinished) {

                //반복실행할 구문
                sendData("a");         // 상태 확인문자 우노보드로 보내기
                try {
                    Thread.sleep(1000); // 잠시대기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                beginListenForData();
            }
            public void onFinish() {
                //마지막에 실행할 구문
                CDT.cancel();
                drugCount++;
                if(drugCount >= 3){
                    CDT.cancel();
                    drugCount = 0;
                }else{
                    //반복실행할 구문
                    sendData("g");         // 상태 확인문자 우노보드로 보내기
                    try {
                        Thread.sleep(1000); // 잠시대기
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    beginListenForData();
                    CDT.start();
                }

            }
        };

        Log.d("Main","약을 아직 먹지 않았습니다.");
    }

    // 블루투스 장치의 이름이 주어졌을때 해당 블루투스 장치 객체를 페어링 된 장치 목록에서 찾아내는 코드.
    @SuppressLint("MissingPermission")
    BluetoothDevice getDeviceFromBondedList(String name) {
        // BluetoothDevice : 페어링 된 기기 목록을 얻어옴.
        BluetoothDevice selectedDevice = null;
        // getBondedDevices 함수가 반환하는 페어링 된 기기 목록은 Set 형식이며,
        // Set 형식에서는 n 번째 원소를 얻어오는 방법이 없으므로 주어진 이름과 비교해서 찾는다.
        for (BluetoothDevice deivce : mDevices) {
            // getName() : 단말기의 Bluetooth Adapter 이름을 반환
            if (name.equals(deivce.getName())) {
                selectedDevice = deivce;
                break;
            }
        }
        return selectedDevice;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // startActivityForResult 를 여러번 사용할 땐 이런 식으로
        // switch 문을 사용하여 어떤 요청인지 구분하여 사용함.
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK) { // 블루투스 활성화 상태
                    selectDevice();
                    Toast.makeText(getApplicationContext(), "블루투스 켜짐", Toast.LENGTH_SHORT).show();
                } else if (resultCode == RESULT_CANCELED) { // 블루투스 비활성화 상태 (종료)
                    Toast.makeText(getApplicationContext(), "블루투스를 사용할 수 없어 프로그램을 종료안해",
                            Toast.LENGTH_SHORT).show();
//                    finish();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

/////Bluethooth/////////////////////////////////////////////////////////////////

}