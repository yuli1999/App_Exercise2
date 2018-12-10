package com.umeng.soexample.app_exercise.fragment.mvp.model;

import android.net.Uri;
import android.os.Handler;

import com.google.gson.Gson;
import com.umeng.soexample.app_exercise.fragment.bean.ReOneBean;
import com.umeng.soexample.app_exercise.fragment.mvp.callback.HomeCallBack;
import com.umeng.soexample.app_exercise.interFace.Util;
import com.umeng.soexample.app_exercise.net.HttpNet;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * data:2018/12/7
 * author: HJL (磊)
 * function:
 */
public class HomeModel {
    Handler handler = new Handler();


    //一
    public void reone(final HomeCallBack callBack) {
        HttpNet.EnestenGet(Util.HOME_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFaile("加载失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Gson gson = new Gson();
                ReOneBean reOneBean = gson.fromJson(data, ReOneBean.class);
                final List<ReOneBean.ResultBean.RxxpBean.CommodityListBean> rxxp = reOneBean.getResult().getRxxp().get(0).getCommodityList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(rxxp);
                    }
                });
            }
        });
    }

    //二
    public void retwo(final HomeCallBack callBack) {
        HttpNet.EnestenGet(Util.HOME_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFaile("加载失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data2 = response.body().string();
                Gson gson = new Gson();
                ReOneBean reOneBean = gson.fromJson(data2, ReOneBean.class);
                final List<ReOneBean.ResultBean.PzshBean.CommodityListBeanX> pzsh = reOneBean.getResult().getPzsh().get(0).getCommodityList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(pzsh);
                    }
                });

            }
        });
    }

    //三
    public void rethree(final HomeCallBack callBack) {
        HttpNet.EnestenGet(Util.HOME_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFaile("加载失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data3 = response.body().string();
                Gson gson = new Gson();
                ReOneBean reOneBean = gson.fromJson(data3, ReOneBean.class);
                final List<ReOneBean.ResultBean.MlssBean.CommodityListBeanXX> mlss = reOneBean.getResult().getMlss().get(0).getCommodityList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(mlss);
                    }
                });
            }
        });
    }

}
