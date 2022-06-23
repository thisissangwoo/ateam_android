package com.example.anafor.User;

import static android.content.res.ColorStateList.valueOf;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.MainActivity;
import com.example.anafor.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class UserInfoActivity extends AppCompatActivity{
    private static final String TAG = "회원정보수정";

    TextView tv_logout,tv_idDelete;
    ImageView back;
    TextInputLayout til_id,til_pw,til_pwChk,til_name,til_birth,til_tel;
    TextInputEditText tiedt_id,tiedt_pw,tiedt_pwChk,tiedt_name,tiedt_birth,tiedt_tel;
    Button btn_edit;
    RadioGroup radioGroup;
    RadioButton rdo_male, rdo_female;
    String  pwInput, pwChkInput, nameInput, telInput, birthInput;
    boolean pwChk = true, nameChk = true, telChk = true, birthChk = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        //TextInputLayout 아이디 찾기
        til_id = findViewById(R.id.til_userInfo_email);
        til_pw = findViewById(R.id.til_userInfo_pw);
        til_pwChk = findViewById(R.id.til_userInfo_pwChk);
        til_name = findViewById(R.id.til_userInfo_name);
        til_birth = findViewById(R.id.til_userInfo_birth);
        til_tel = findViewById(R.id.til_userInfo_tel);

        //TextInputEditText 아이디 찾기
        tiedt_id = findViewById(R.id.tiet_userInfo_email);
        tiedt_pw = findViewById(R.id.tiedt_userInfo_pw);
        tiedt_pwChk = findViewById(R.id.tiedt_userInfo_pwChk);
        tiedt_name = findViewById(R.id.tiedt_userInfo_name);
        tiedt_birth = findViewById(R.id.tiedt_userInfo_birth);
        tiedt_tel = findViewById(R.id.tiedt_userInfo_tel);

        //성별 라디오버튼 아이디 찾기
        radioGroup = findViewById(R.id.rdoG_userInfo_rdoG);
        rdo_male = findViewById(R.id.rdoBtn_userInfo_male);
        rdo_female = findViewById(R.id.rdoBtn_userInfo_female);

        //하단메뉴 아이디 찾기
        tv_logout = findViewById(R.id.tv_userInfo_logout);
        tv_idDelete = findViewById(R.id.tv_userInfo_idDelete);
        btn_edit = findViewById(R.id.btn_userInfo_edit);

        //뒤로가기
        back = findViewById(R.id.imgv_userInfo_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //회원정보 가져오기
        tiedt_id.setText(CommonVal.loginInfo.getUser_id());
        if (!(CommonVal.loginInfo.getUser_pw()== null)){
            tiedt_pw.setText(CommonVal.loginInfo.getUser_pw());
            tiedt_pwChk.setText(CommonVal.loginInfo.getUser_pw());
        }else{
            tiedt_pw.setText(null);
            tiedt_pwChk.setText(null);
        }

        tiedt_name.setText(CommonVal.loginInfo.getUser_name());

        if (!(CommonVal.loginInfo.getUser_tel()==null)){
            tiedt_tel.setText(CommonVal.loginInfo.getUser_tel());
        }else{
            tiedt_tel.setText(null);
        }

        //생년월일 정규식
        if (!(CommonVal.loginInfo.getUser_birth()==null)){
            String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
            tiedt_birth.setText(CommonVal.loginInfo.getUser_birth().replaceAll(match, ""));
        }else{
            tiedt_birth.setText(null);
        }

        //성별체크
        if(CommonVal.loginInfo.getUser_gender().equals("여")){
            rdo_female.setChecked(true);
        }else{
            rdo_male.setChecked(true);
        }


        /* 유효성 검사 (1.비번, 2.비번확인, 3.이름, 4.전화번호, 5.생년월일) */

        // 1.비밀번호
        tiedt_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                pwInput = tiedt_pw.getText().toString();
                if(!pwInput.isEmpty() && Pattern.matches("^[a-zA-Z0-9]{8,20}$", pwInput)){
                    til_pw.setHelperText("사용 가능한 비밀번호입니다.");
                    til_pw.setHelperTextColor(valueOf(Color.parseColor("#FF6200EE")));
                    pwChk = true;
                }else if(pwInput.replace(" ", "").equals("")){
                    til_pw.setError("비밀번호를 입력하세요");
                    til_pwChk.setHelperText(null);
                    til_pwChk.setError(null);
                    pwChk = false;
                }else{
                    til_pw.setError("사용할 수 없는 비밀번호입니다.");
                    pwChk = false;
                }
            }
        });

        // 2.비밀번호확인
        tiedt_pwChk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                pwChkInput = tiedt_pw.getText().toString();
                String pwChkInput = tiedt_pwChk.getText().toString();
                if(pwChkInput.equals(pwInput) && !(pwChkInput.replace(" ", "").equals("")) && !(pwInput.isEmpty()) ) {
                    til_pwChk.setHelperText("비밀번호가 일치합니다");
                    til_pwChk.setHelperTextColor(valueOf(Color.parseColor("#FF6200EE")));
                    til_pwChk.setError(null);
                }else if(pwChkInput.replace(" ", "").equals("")){
                    til_pwChk.setError("비밀번호를 입력하세요");
                }else{
                    til_pwChk.setError("비밀번호 정보가 일치하지 않습니다.");
                }
            }
        });

        // 3.이름
        tiedt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                nameInput = tiedt_name.getText().toString();
                if (!nameInput.isEmpty() && Pattern.matches("^[가-힣a-zA-Z]{2,10}$", nameInput)) {
                    til_name.setHelperText("");
                    til_name.setError(null);
                    nameChk = true;
                } else if(nameInput.replace(" ", "").equals("")) {
                    til_name.setError("이름을 입력하세요");
                    nameChk = false;
                } else {
                    til_name.setError("이름을 입력해주세요");
                    nameChk = false;
                }
            }
        });

        // 4.전화번호
        tiedt_tel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                telInput = tiedt_tel.getText().toString();
                if (!telInput.isEmpty() && Pattern.matches("\\d{11}", telInput)) {
                    til_tel.setHelperText(" ");
                    til_tel.setError(null);
                    telChk = true;
                } else if (telInput.replace(" ", "").equals("")) {
                    til_tel.setError("핸드폰번호를 입력하세요");
                    telChk = false;
                } else {
                    til_tel.setError("번호만 입력하세요 예)01012345678");
                    telChk = false;
                }
            }
        });

        // 5.생년월일
        tiedt_birth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                birthInput = tiedt_birth.getText().toString();
                Date nowDate = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); //검증할 날짜 포맷 설정
                String strNowDate = format.format(nowDate);

                format.setLenient(false); //false일경우 처리시 입력한 값이 잘못된 형식일 시 오류가 발생
                try {
                    format.parse(birthInput); //대상 값 포맷에 적용되는지 확인
                    String startAge = CommonMethod.AddDate(strNowDate,-14,0,0);
                    format.parse(startAge);
                    String endAge = CommonMethod.AddDate(strNowDate,-100,0,0);
                    format.parse(endAge);

                    if (birthInput.compareTo(startAge) <= 0 && birthInput.compareTo(endAge) >= 0){
                        til_birth.setHelperText("");
                        til_birth.setError(null);
                        birthChk = true;
                    }else if(birthInput.compareTo(endAge) < 0){
                        til_birth.setError("정말이세요?");
                        birthChk = false;
                    }else if(birthInput.compareTo(startAge) > 0 && birthInput.compareTo(strNowDate) <= 0){
                        til_birth.setHelperText(null);
                        til_birth.setError("만14세 미만은 아나포를 이용할수 없습니다");
                        birthChk = false;
                    }else if(birthInput.compareTo(strNowDate) > 0){
                        til_birth.setHelperText(null);
                        til_birth.setError("미래에서 오셨군요^^");
                        birthChk = false;
                    }else{
                        til_birth.setHelperText(null);
                        til_birth.setError("가입할수 없는 나이입니다 죄송합니다");
                        birthChk = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    til_birth.setError("생년월일 입력하세요 예)19950101");
                    birthChk = false;
                }

            }
        });

        // 6. 성별
        rdo_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    CommonVal.loginInfo.setUser_gender("남");
                    rdo_female.setChecked(false);
                }
            }
        });

        rdo_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    CommonVal.loginInfo.setUser_gender("여");
                    rdo_male.setChecked(false);
                }
            }
        });

        //로그아웃
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(UserInfoActivity.this);
                builder.setMessage("로그아웃 하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i=new Intent(UserInfoActivity.this,MainActivity.class);
                        i.putExtra("finish", true);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        CommonVal.loginInfo = null;
                        CommonVal.bottom_menu = "0";
                        SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("login");
                        editor.clear();
                        editor.apply();
                        editor.commit();
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

        //회원탈퇴
        tv_idDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this,UserDeleteActivity.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        //정보수정등록
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tiedt_pw.getText().toString().length() < 1 || pwChk == false){
                    Toast.makeText(UserInfoActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    tiedt_pw.requestFocus();
                    return;
                }else if(!(tiedt_pwChk.getText().toString().equals(tiedt_pw.getText().toString()))){
                    Toast.makeText(UserInfoActivity.this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                    tiedt_pwChk.requestFocus();
                    return;
                }else if(tiedt_name.getText().toString().length() < 1 || nameChk == false){
                    Toast.makeText(UserInfoActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    tiedt_name.requestFocus();
                    return;
                }else if(tiedt_tel.getText().toString().length() < 1 || telChk == false){
                    Toast.makeText(UserInfoActivity.this, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    tiedt_tel.requestFocus();
                    return;
                }else if(tiedt_birth.getText().toString().length() < 1 || birthChk == false) {
                    Toast.makeText(UserInfoActivity.this, "생년월일을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                Gson gson = new Gson();

                CommonVal.loginInfo.setUser_id(tiedt_id.getText()+"");
                CommonVal.loginInfo.setUser_pw(tiedt_pw.getText()+"");
                CommonVal.loginInfo.setUser_name(tiedt_name.getText()+"");
                CommonVal.loginInfo.setUser_tel(tiedt_tel.getText()+"");
                CommonVal.loginInfo.setUser_birth(tiedt_birth.getText()+"");
                CommonVal.loginInfo.setUser_gender(rdo_male.isChecked() == true ? "남" : "여");

                AskTask task = new AskTask("update");
                task.addParam("vo", gson.toJson(CommonVal.loginInfo));
                CommonMethod.executeAskGet(task);

                Intent intent = new Intent(UserInfoActivity.this,MainActivity.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                Toast.makeText(UserInfoActivity.this,"회원정보가 수정되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

}




