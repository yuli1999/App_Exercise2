package com.umeng.soexample.app_exercise.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.app_exercise.MainActivity;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.interFace.Util;
import com.umeng.soexample.app_exercise.login.bean.LoginBean;
import com.umeng.soexample.app_exercise.login.presenter.LoginPresenter;
import com.umeng.soexample.app_exercise.login.view.Login_View;
import com.umeng.soexample.app_exercise.net.RequestGet;
import com.umeng.soexample.app_exercise.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements Login_View {
    private ImageView mPhone;
    private EditText mLoginPhone;
    private EditText mLoginPwd;
    private CheckBox mCheck;
    private TextView mRegisterJump;
    private Button mLoginBt;
    private LoginPresenter presenter;
    private SharedPreferences config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();//初始化控件
        presenter = new LoginPresenter(this);//实例化P层
        initData();//点击登录
        initSp(); //初始化sp
    }

    //初始化sp
    private void initSp() {
        config = getSharedPreferences("config", MODE_PRIVATE);
        if (config.getString("phone", "") != null) {
            String phone = config.getString("phone", "");
            String pwd = config.getString("pwd", "");
            boolean box = config.getBoolean("box", false);
            mLoginPhone.setText(phone);
            mLoginPwd.setText(pwd);
            mCheck.setChecked(box);
        }
    }

    private void initData() {
        mLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mLoginPhone.getText().toString().trim();
                String pwd = mLoginPwd.getText().toString().trim();
                presenter.login(phone, pwd);

                //判断是否记住密码
                if (mCheck.isChecked()) {
                    SharedPreferences.Editor edit = config.edit();
                    edit.putString("phone", phone);
                    edit.putString("pwd", pwd);
                    edit.putBoolean("box", true);
                    edit.commit();
                } else {
                    SharedPreferences.Editor edit = config.edit();
                    edit.clear();
                    edit.commit();
                }

            }
        });

        mRegisterJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        mPhone = findViewById(R.id.phone);
        mLoginPhone = findViewById(R.id.login_phone);
        mLoginPwd = findViewById(R.id.login_pwd);
        mCheck = findViewById(R.id.check);
        mRegisterJump = findViewById(R.id.register_jump);
        mLoginBt = findViewById(R.id.login_bt);
    }


    @SuppressLint("ApplySharedPref")
    @Override
    public void OnSuccess(LoginBean result) {
        Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();

        edit.putString("headPic", result.getResult().getHeadPic())
                .putString("nickName", result.getResult().getNickName())
                .putString("sessionId", result.getResult().getSessionId())
                .putInt("userId", result.getResult().getUserId())
                .putBoolean("login", false)
                .commit();
        finish();
    }

    @Override
    public void OnFaile(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
