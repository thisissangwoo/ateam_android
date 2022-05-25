package com.example.anafor.User;

import static android.content.res.ColorStateList.valueOf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.MainActivity;
import com.example.anafor.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {
    private static final String TAG = "회원가입";

    TextView tv_logo;
    ImageView back;
    Button btn_idChk, btn_code, btn_join;
    TextInputLayout til_id,til_code,til_pw,til_pwChk,til_name,til_birth,til_tel;
    TextInputEditText tiedt_id,tiedt_code,tiedt_pw,tiedt_pwChk,tiedt_name,tiedt_birth,tiedt_tel;
    RadioGroup radioGroup;
    RadioButton rdo_male, rdo_female;
    String idInput, pwInput, pwChkInput, nameInput, telInput, birthInput, codeInput, checkNum, str_result ;
    Boolean idChk, pwChk, nameChk, telChk, birthChk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //Button 아이디 찾기
        btn_idChk = findViewById(R.id.btn_join_idChek);
        btn_code = findViewById(R.id.btn_join_emailCode);
        btn_join = findViewById(R.id.btn_join_join);

        //TextInputLayout 아이디 찾기
        til_id = findViewById(R.id.til_join_email);
        til_code = findViewById(R.id.til_join_emailCode);
        til_pw = findViewById(R.id.til_join_pw);
        til_pwChk = findViewById(R.id.til_join_pwChk);
        til_name = findViewById(R.id.til_join_name);
        til_birth = findViewById(R.id.til_join_birth);
        til_tel = findViewById(R.id.til_join_tel);

        //TextInputEditText 아이디 찾기
        tiedt_id = findViewById(R.id.tiedt_join_email);
        tiedt_code = findViewById(R.id.tiedt_join_emailCode);
        tiedt_pw = findViewById(R.id.tiedt_join_pw);
        tiedt_pwChk = findViewById(R.id.tiedt_join_pwChk);
        tiedt_name = findViewById(R.id.tiedt_join_name);
        tiedt_birth = findViewById(R.id.tiedt_join_birth);
        tiedt_tel = findViewById(R.id.tiedt_join_tel);

        //성별 라디오버튼
        radioGroup = findViewById(R.id.radioGroup);
        rdo_male = findViewById(R.id.rdoBtn_join_male);
        rdo_female = findViewById(R.id.rdoBtn_join_female);

        //메인액티비티 이동
        tv_logo = findViewById(R.id.tv_join_anaforlogo);
        tv_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //뒤로가기
        back = findViewById(R.id.imgv_join_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /* 유효성 검사 (1.이메일, 2.비번, 3.비번확인, 4.이름, 5.전화번호, 6.생년월일, 7.성별 ) */

        // 1.아이디(=이메일) 유효성 검사 및 중복체크
        tiedt_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiedt_code.setText(null);
                btn_code.setText("이메일인증");
                btn_code.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable s) {
                idInput = tiedt_id.getText().toString();
                if(!idInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(idInput).matches()) {
                    til_id.setHelperText("중복확인 버튼을 눌러주세요");
                    til_id.setError(null);
                    btn_idChk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AskTask task = new AskTask("id_chk");
                            task.addParam("user_id",tiedt_id.getText().toString());

                            InputStreamReader isr = CommonMethod.executeAskGet(task);
                            Gson gson = new Gson();

                            idChk = gson.fromJson(isr,Boolean.class);

                            Log.d(TAG, "onClick: " + idChk);

                            if(idChk==true) {
                                til_id.setHelperText("사용 가능한 이메일입니다");
                                til_id.setHelperTextColor(valueOf(Color.parseColor("#FF6200EE")));
                                til_id.setError(null);
                                btn_code.setEnabled(true);
                            }else{
                                til_id.setError("등록된 이메일입니다 다시 입력하세요");
                                til_id.setHelperText(null);
                                btn_code.setEnabled(false);
                                idChk = false;
                            }
                        }
                    });
                }else{
                    til_id.setError("아이디는 이메일 형식으로 입력해주세요");
                    til_id.setHelperText(null);
                    btn_code.setEnabled(false);
                    idChk = false;
                }
            }
        });

        // 2.이메일 인증
        btn_code.setEnabled(false);
        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String data = gson.toJson(tiedt_id.getText().toString());
                AskTask task = new AskTask("emailCheck");
                task.addParam("user_id", data);

                InputStreamReader isr = CommonMethod.executeAskGet(task);
                EmailNumberVO evo = gson.fromJson(isr, EmailNumberVO.class);
                Log.d(TAG, "onClick: " + evo.getCheckNum());
                btn_code.setText("재전송");

                tiedt_code.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                        checkNum = String.valueOf(evo.getCheckNum());
                        codeInput = tiedt_code.getText().toString();
                        if(checkNum.equals(codeInput)) {
                            til_code.setHelperText("인증코드가 일치합니다.");
                            til_code.setHelperTextColor(valueOf(Color.parseColor("#FF6200EE")));
                            btn_code.setText("이메일인증");
                            btn_code.setEnabled(false);
                        }else{
                            til_code.setError("이메일 인증코드를 정확히 입력해주세요");
                            til_code.setHelperText(null);
                            btn_code.setText("재전송");
                            btn_code.setEnabled(true);
                        }
                    }
                });
            }
        });


        // 2.비밀번호
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
                if(!pwInput.isEmpty() && Pattern.matches("^[a-zA-Z0-9]{8,16}$", pwInput)){
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

        // 3.비밀번호확인
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

        // 4.이름
        tiedt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                nameInput = tiedt_name.getText().toString();
                if (!nameInput.isEmpty() && Pattern.matches("^[가-힣]*$", nameInput)) {
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

        // 5.전화번호
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

        // 6.생년월일
        tiedt_birth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                birthInput = tiedt_birth.getText().toString();
                SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyyMMdd"); //검증할 날짜 포맷 설정
                dateFormatParser.setLenient(false); //false일경우 처리시 입력한 값이 잘못된 형식일 시 오류가 발생
                try {
                    dateFormatParser.parse(birthInput); //대상 값 포맷에 적용되는지 확인
                    til_birth.setHelperText("");
                    til_birth.setError(null);
                    birthChk = true;
                } catch (ParseException e) {
                    e.printStackTrace();
                    til_birth.setError("생년월일 입력하세요 예)19950101");
                    birthChk = false;
                }
            }
        });

        // 7. 성별
        str_result = "남";
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdoBtn_join_male) {
                    str_result = "남";
                }else if (checkedId == R.id.rdoBtn_join_female){
                    str_result = "여";
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        //가입하기 버튼
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tiedt_id.getText().toString().length() < 1 || idChk == false){
                    Toast.makeText(JoinActivity.this, "이메일을 정확히 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }else if(tiedt_code.getText().toString().length() < 1 || !codeInput.equals(checkNum)){
                    Toast.makeText(JoinActivity.this, "코드를 정확히 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }else if(tiedt_pw.getText().toString().length() < 1 || pwChk == false){
                    Toast.makeText(JoinActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!(tiedt_pwChk.getText().toString().equals(tiedt_pw.getText().toString()))){
                    Toast.makeText(JoinActivity.this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }else if(tiedt_name.getText().toString().length() < 1 || nameChk == false){
                    Toast.makeText(JoinActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }else if(tiedt_tel.getText().toString().length() < 1 || telChk == false){
                    Toast.makeText(JoinActivity.this, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }else if(tiedt_birth.getText().toString().length() < 1 || birthChk == false) {
                    Toast.makeText(JoinActivity.this, "생년월일을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                AskTask task = new AskTask("join");
                task.addParam("user_id",tiedt_id.getText().toString());
                task.addParam("user_pw",tiedt_pw.getText().toString());
                task.addParam("user_name",tiedt_name.getText().toString());
                task.addParam("user_tel",tiedt_tel.getText().toString());
                task.addParam("user_birth",tiedt_birth.getText().toString());
                task.addParam("user_gender",str_result);
                CommonMethod.executeAskGet(task);

                Toast.makeText(JoinActivity.this, "아나포 회원가입을 축하합니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(JoinActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

