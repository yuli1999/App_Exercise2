package com.umeng.soexample.app_exercise.register.model;

import android.os.Handler;

import com.google.gson.Gson;
import com.umeng.soexample.app_exercise.interFace.Util;
import com.umeng.soexample.app_exercise.net.HttpNet;
import com.umeng.soexample.app_exercise.register.bean.RegisterBean;
import com.umeng.soexample.app_exercise.register.callback.HttpCallBackRegis;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * data:2018/12/6
 * author: HJL (磊)
 * function:
 */
public class RegisterModel {

    private Handler handler = new Handler();

    public void register(String phone, String pwd, final HttpCallBackRegis callBackRegis) {
        HashMap<String, String> map = new HashMap();
        map.put("phone", phone);
        map.put("pwd", pwd);

        HttpNet.EnestenPost(Util.REGISTER_URL, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                Gson gson = new Gson();
                final RegisterBean registerBean = gson.fromJson(data, RegisterBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (registerBean.getStatus().equals("0000")) {
                            callBackRegis.onSuccess(registerBean.getMessage());
                        } else {
                            callBackRegis.onFaile(registerBean.getMessage());
                        }
                    }
                });
            }
        });
    }

}
