package com.umeng.soexample.app_exercise.user.footprint.mvp;

import android.annotation.SuppressLint;

import com.umeng.soexample.app_exercise.interFace.Util;
import com.umeng.soexample.app_exercise.net.RetrofitUtil;
import com.umeng.soexample.app_exercise.user.footprint.MyFootBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * data:2018/12/16
 * author: HJL (磊)
 * function:
 */
public class MyFootModel {

    @SuppressLint("CheckResult")
    public void myFoot(int page, int count, final MyFootCallBack callBack) {
        Util create = RetrofitUtil.getInsetance().Create(Util.class);

        create.getmyfoot(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyFootBean>() {
                    @Override
                    public void accept(MyFootBean myFootBean) throws Exception {
                        if (myFootBean != null) {
                            callBack.OnfootSuccess(myFootBean);
                        } else {
                            callBack.OnFaile(new Exception(myFootBean.getMessage()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (throwable!=null){
                        callBack.OnFaile((Exception) throwable);
                        }
                    }
                });
    }

}
