package com.umeng.soexample.app_exercise.fragment.mvp.model;

import android.annotation.SuppressLint;

import com.umeng.soexample.app_exercise.fragment.bean.CircleBean;
import com.umeng.soexample.app_exercise.fragment.mvp.callback.CircleCallBack;
import com.umeng.soexample.app_exercise.interFace.Util;
import com.umeng.soexample.app_exercise.net.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * data:2018/12/13
 * author: HJL (磊)
 * function:
 */
public class CircleModel {


    @SuppressLint("CheckResult")
    public void circle(int page, int count, final CircleCallBack callBack) {
        Util create = RetrofitUtil.getInsetance().Create(Util.class);

        create.getcircle(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CircleBean>() {
                    @Override
                    public void accept(CircleBean circleBean) throws Exception {
                        callBack.circleSuccess(circleBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.circleFaile("失败");
                    }
                });
    }

}
