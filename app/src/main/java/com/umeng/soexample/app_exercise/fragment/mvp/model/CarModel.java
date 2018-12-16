package com.umeng.soexample.app_exercise.fragment.mvp.model;

import android.annotation.SuppressLint;

import com.umeng.soexample.app_exercise.fragment.bean.CarBean;
import com.umeng.soexample.app_exercise.fragment.mvp.callback.CarCallBack;
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
public class CarModel {

    @SuppressLint("CheckResult")
    public void car(final CarCallBack carCallBack) {
        Util create = RetrofitUtil.getInsetance().Create(Util.class);

        create.getcar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarBean>() {
                    @Override
                    public void accept(CarBean carBean) throws Exception {
                         if (carBean!=null){
                             carCallBack.carSuccess(carBean);
                         }else {
                             carCallBack.circleFaile(new Exception(carBean.getMessage()));
                         }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        carCallBack.circleFaile((Exception) throwable);
                    }
                });


    }


}
