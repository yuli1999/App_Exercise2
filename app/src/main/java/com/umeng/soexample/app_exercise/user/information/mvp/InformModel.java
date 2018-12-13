package com.umeng.soexample.app_exercise.user.information.mvp;

import android.annotation.SuppressLint;

import com.umeng.soexample.app_exercise.interFace.Util;
import com.umeng.soexample.app_exercise.net.RetrofitUtil;
import com.umeng.soexample.app_exercise.user.information.InformBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * data:2018/12/13
 * author: HJL (磊)
 * function:
 */
public class InformModel {

    @SuppressLint("CheckResult")
    public void inform(final InformCallBack callBack) {
        Util create = RetrofitUtil.getInsetance().Create(Util.class);

        create.getInform()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InformBean>() {
                    @Override
                    public void accept(InformBean informBean) throws Exception {
                        if (informBean.getStatus().equals("0000")) {
                            callBack.OnInformSuccess(informBean);
                        } else {
                            callBack.OnFaile(new Exception(informBean.getMessage()));
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
