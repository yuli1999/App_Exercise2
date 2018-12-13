package com.umeng.soexample.app_exercise.login.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.umeng.soexample.app_exercise.interFace.Util;
import com.umeng.soexample.app_exercise.login.bean.LoginBean;
import com.umeng.soexample.app_exercise.login.callback.HttpCallback;
import com.umeng.soexample.app_exercise.net.HttpNet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * data:2018/12/1
 * author: HJL (磊)
 * function:
 */
public class Login_Model {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    public void login(String phone, String pwd, final HttpCallback httpCallback) {
        Map<String, String> map = new HashMap();
        map.put("phone", phone);
        map.put("pwd", pwd);

        HttpNet.EnestenPost(Util.LOGIN_URL, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final LoginBean login_bean = gson.fromJson(string, LoginBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (login_bean.getStatus().equals("0000")) {
//                            Log.i("userid",""+login_bean.getResult().getUserId());
                            httpCallback.OnSuccess(login_bean);
                        } else {
                            httpCallback.OnFaile(login_bean.getMessage());
                        }

                    }
                });
            }
        });

    }

}
