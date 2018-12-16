package com.umeng.soexample.app_exercise.labelmvp.model;

import android.annotation.SuppressLint;

import com.umeng.soexample.app_exercise.fragment.bean.LabelBean;
import com.umeng.soexample.app_exercise.interFace.Util;
import com.umeng.soexample.app_exercise.labelmvp.LabelCallBack;
import com.umeng.soexample.app_exercise.net.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * data:2018/12/12
 * author: HJL (磊)
 * function:
 */
public class LabelModel {

    @SuppressLint("CheckResult")
    public void labelshow(final String labelId, int page, int count, final LabelCallBack callBack) {
        Util create = RetrofitUtil.getInsetance().Create(Util.class);

        create.getlabel(labelId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LabelBean>() {
                    @Override
                    public void accept(LabelBean labelBean) throws Exception {
                        if (labelBean != null) {
                            callBack.OnLabelSuccess(labelBean);
                        } else {
                            callBack.OnFaile(new Exception(labelBean.getMessage()));
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
