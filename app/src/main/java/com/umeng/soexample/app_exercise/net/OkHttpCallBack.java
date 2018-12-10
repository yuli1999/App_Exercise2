package com.umeng.soexample.app_exercise.net;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * data:2018/12/5
 * author: HJL (磊)
 * function:
 */
public class OkHttpCallBack implements Callback {

    HttpDataListener httpDataListener;

    public OkHttpCallBack(HttpDataListener httpDataListener) {
        this.httpDataListener = httpDataListener;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private Gson gson = new Gson();


    @Override
    public void onFailure(Call call, IOException e) {
        //处理网络异常
        call.cancel();
        handler.post(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(getClass(), "444", Toast.LENGTH_SHORT).show();
                httpDataListener.onFile("网络异常");
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        //获取Http响应码
        int code = response.code();

        //打印请求结果
        //根据网络状态码分别处理不同的逻辑
        switch (code) {
            case 200:
                deal200(call, response);

                break;
            case 307:
                //读取缓存
                break;
            case 400:
                //bad request
                deal400(call, response);
                break;
            case 404:
                //资源不存在
                deal404(call, response);
                break;
            default:
                //服务器错误
                dealDefault(call, response);
                break;
        }

    }


    private void deal200(Call call, Response response) throws IOException {
        //获取服务器放回的json数据
        final String data = response.body().string();
        handler.post(new Runnable() {
            @Override
            public void run() {
                httpDataListener.onSuccess(data);
            }
        });
    }

    private void deal400(Call call, Response response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                httpDataListener.onFile("请求失败");
            }
        });
    }

    private void deal404(Call call, Response response) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    httpDataListener.onFile("资源不存在");
                }
            });
    }

    private void dealDefault(Call call, Response response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                httpDataListener.onFile("未知名错误");
            }
        });
    }
}
