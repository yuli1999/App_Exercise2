package com.umeng.soexample.app_exercise.user.information;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.user.information.mvp.InformPresenter;
import com.umeng.soexample.app_exercise.user.information.mvp.InformView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformActivity extends AppCompatActivity implements InformView {

    @BindView(R.id.inform_pic)
    ImageView mInformPic;
    @BindView(R.id.inform_nam)
    TextView mInformNam;
    @BindView(R.id.inform_pwd)
    TextView mInformPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        ButterKnife.bind(this);

        InformPresenter informPresenter = new InformPresenter(this);
        informPresenter.inform();

    }

    @Override
    public void OnInformSuccess(InformBean result) {
        Log.i("123", "OnInformSuccess: "+result);
        InformBean.ResultBean resultBean = result.getResult();
        Glide.with(this).load(resultBean.getHeadPic()).into(mInformPic);
        mInformNam.setText(resultBean.getNickName());
        mInformPwd.setText(resultBean.getPassword());
    }

    @Override
    public void OnFaile(Exception e) {
        Toast.makeText(this, ""+e.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
