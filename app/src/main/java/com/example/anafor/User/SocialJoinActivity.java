package com.example.anafor.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.MainActivity;
import com.example.anafor.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class SocialJoinActivity extends AppCompatActivity {
    private static final String TAG = "소셜회원가입";

    TextInputLayout til_name;
    TextInputEditText tiedt_id, tiedt_name;
    RadioGroup radioGroup;
    RadioButton rdo_male, rdo_female;
    String nameInput, str_result;
    Button btn_join;
    Boolean nameChk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_join);

        Intent intent = getIntent();
        String id = intent.getStringExtra("user_id");

        //아이디 찾기
        til_name = findViewById(R.id.til_socialJoin_name);
        tiedt_id = findViewById(R.id.tiedt_socialJoin_email);
        tiedt_name = findViewById(R.id.tiedt_socialJoin_name);
        radioGroup = findViewById(R.id.radioGroup);
        rdo_male = findViewById(R.id.rdoBtn_join_male);
        rdo_female = findViewById(R.id.rdoBtn_join_female);
        btn_join = findViewById(R.id.btn_socialJoin_join);

        tiedt_id.setText(id);
        tiedt_id.setEnabled(false);

        //이름
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
        
        // 성별
        str_result = "남";
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdoBtn_socialJoin_male) {
                    str_result = "남";
                }else if (checkedId == R.id.rdoBtn_socialJoin_female){
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

                if(tiedt_name.getText().toString().length() < 1 || nameChk == false) {
                    Toast.makeText(SocialJoinActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    tiedt_name.requestFocus();
                    return;
                }

                AskTask task = new AskTask("socialJoin");
                task.addParam("user_id",tiedt_id.getText().toString());
                task.addParam("user_name",tiedt_name.getText().toString());
                task.addParam("user_gender","남");
                CommonMethod.executeAskGet(task);

                Toast.makeText(SocialJoinActivity.this, "아나포 회원가입을 축하합니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SocialJoinActivity.this,LoginActivity.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}