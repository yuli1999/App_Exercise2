package com.umeng.soexample.app_exercise.register.presenter;

import android.support.v7.widget.RecyclerView;

import com.umeng.soexample.app_exercise.register.callback.HttpCallBackRegis;
import com.umeng.soexample.app_exercise.register.model.RegisterModel;
import com.umeng.soexample.app_exercise.register.view.RegisterView;

/**
 * data:2018/12/6
 * author: HJL (磊)
 * function:
 */
public class RegisterPresenter {

    private RegisterView registerView;
    private RegisterModel registerModel;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModel();
    }

    public void register(String phone, String pwd) {
        registerModel.register(phone, pwd, new HttpCallBackRegis() {
            @Override
            public void onSuccess(String data) {
                registerView.onSuccess(data);
            }

            @Override
            public void onFaile(String msg) {
                registerView.onFaile(msg);
            }
        });
    }

}
