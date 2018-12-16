package com.umeng.soexample.app_exercise.user.circle.mvp;

import android.annotation.SuppressLint;

import com.umeng.soexample.app_exercise.fragment.mvp.callback.CircleCallBack;
import com.umeng.soexample.app_exercise.interFace.Util;
import com.umeng.soexample.app_exercise.net.RetrofitUtil;
import com.umeng.soexample.app_exercise.user.circle.MyCircleBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * data:2018/12/16
 * author: HJL (磊)
 * function:
 */
public class MyCircleModel {

    @SuppressLint("CheckResult")
    public void myCircle(int page, int count, final MyCircleCallBack callBack) {
        Util create = RetrofitUtil.getInsetance().Create(Util.class);

        create.getmycircle(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyCircleBean>() {
                    @Override
                    public void accept(MyCircleBean myCircleBean) throws Exception {
                        if (myCircleBean != null) {
                            callBack.OnCircleSuccess(myCircleBean);
                        } else {
                            callBack.OnFaile(new Exception(myCircleBean.getMessage()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.OnFaile((Exception) throwable);
                    }
                });
    }

}
