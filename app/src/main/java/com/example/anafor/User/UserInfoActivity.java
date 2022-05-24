package com.example.anafor.User;

import static android.content.res.ColorStateList.valueOf;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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
import java.util.regex.Pattern;

public class UserInfoActivity extends AppCompatActivity {
    private static final String TAG = "회원정보수정";

    TextView tv_logo,tv_logout,tv_idDelete;
    ImageView back;
    TextInputLayout til_id,til_pw,til_pwChk,til_name,til_birth,til_tel;
    TextInputEditText tiedt_id,tiedt_pw,tiedt_pwChk,tiedt_name,tiedt_birth,tiedt_tel;
    Button btn_edit;
    RadioGroup radioGroup;
    RadioButton rdo_male, rdo_female;

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

        //로고클릭
        tv_logo = findViewById(R.id.tv_userInfo_anaforlogo);
        tv_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
        tiedt_pw.setText(CommonVal.loginInfo.getUser_pw());
        tiedt_pwChk.setText(CommonVal.loginInfo.getUser_pw());
        tiedt_name.setText(CommonVal.loginInfo.getUser_name());
        tiedt_tel.setText(CommonVal.loginInfo.getUser_tel());

        //생년월일 정규식
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
        tiedt_birth.setText(CommonVal.loginInfo.getUser_birth().replaceAll(match, ""));

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
                String pwInput = tiedt_pw.getText().toString();
                if(!pwInput.isEmpty() && Pattern.matches("^[a-zA-Z0-9]{8,16}$", pwInput)){
                    til_pw.setHelperText("사용 가능한 비밀번호입니다.");
                    til_pw.setHelperTextColor(valueOf(Color.parseColor("#FF6200EE")));
                }else if(pwInput.replace(" ", "").equals("")){
                    til_pw.setError("비밀번호를 입력하세요");
                    til_pwChk.setHelperText(null);
                    til_pwChk.setError(null);
                }else{
                    til_pw.setError("사용할 수 없는 비밀번호입니다.");
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
                String pwInput = tiedt_pw.getText().toString();
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
                String nameInput = tiedt_name.getText().toString();
                if (!nameInput.isEmpty() && Pattern.matches("^[가-힣]*$", nameInput)) {
                    til_name.setHelperText(" ");
                } else if (nameInput.replace(" ", "").equals("")) {
                    til_name.setError("이름을 입력하세요");
                } else {
                    til_name.setError("이름을 한글로 입력해주세요");
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
                String telInput = tiedt_tel.getText().toString();
                if (!telInput.isEmpty() && Pattern.matches("\\d{11}", telInput)) {
                    til_tel.setHelperText(" ");
                } else if (telInput.replace(" ", "").equals("")) {
                    til_tel.setError("핸드폰번호를 입력하세요");
                } else {
                    til_tel.setError("번호만 입력하세요 예)01012345678");
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
                SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyyMMdd"); //검증할 날짜 포맷 설정
                dateFormatParser.setLenient(false); //false일경우 처리시 입력한 값이 잘못된 형식일 시 오류가 발생
                String birthInput = tiedt_birth.getText().toString();

                try {
                    dateFormatParser.parse(birthInput); //대상 값 포맷에 적용되는지 확인
                    til_birth.setHelperText(null);
                    til_birth.setError(null);
                } catch (ParseException e) {
                    e.printStackTrace();
                    til_birth.setError("생년월일 입력하세요 예)19950101");
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
                        finish();
                        Intent i=new Intent(UserInfoActivity.this,MainActivity.class);
                        i.putExtra("finish", true);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        CommonVal.loginInfo = null;
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
                startActivity(intent);
            }
        });

        //정보수정등록
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(tiedt_pw.getText().toString().length() < 1){
                    Toast.makeText(UserInfoActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }else if(tiedt_pwChk.getText().toString().length() < 1){
                    Toast.makeText(UserInfoActivity.this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }else if(tiedt_name.getText().toString().length() < 1){
                    Toast.makeText(UserInfoActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }else if(tiedt_tel.getText().toString().length() < 1){
                    Toast.makeText(UserInfoActivity.this, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }else if(tiedt_birth.getText().toString().length() < 1){
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
                startActivity(intent);
                Toast.makeText(UserInfoActivity.this,"회원정보가 수정되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


