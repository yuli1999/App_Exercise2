package com.umeng.soexample.app_exercise.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.login.LoginActivity;
import com.umeng.soexample.app_exercise.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DropActivity extends AppCompatActivity {

    @BindView(R.id.login_bt)
    Button mLoginBt;
    @BindView(R.id.register_bt)
    Button mRegisterBt;
    @BindView(R.id.drop)
    Button mDrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_bt, R.id.register_bt, R.id.drop})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_bt:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register_bt:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.drop:
                SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor edit = user.edit();
                edit.clear();
                edit.commit();
                finish();
                break;
        }
    }
}
