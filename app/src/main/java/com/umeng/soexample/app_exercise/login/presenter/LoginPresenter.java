package com.umeng.soexample.app_exercise.login.presenter;

import com.umeng.soexample.app_exercise.login.callback.HttpCallback;
import com.umeng.soexample.app_exercise.login.model.Login_Model;
import com.umeng.soexample.app_exercise.login.view.Login_View;
import com.umeng.soexample.app_exercise.net.RequestGet;

/**
 * data:2018/12/1
 * author: HJL (磊)
 * function:
 */
public class LoginPresenter {
    private Login_Model login_model;
    private Login_View login_view;

    public LoginPresenter(Login_View login_view) {
        this.login_view = login_view;
        login_model = new Login_Model();
    }

    public void login(String phone, String pwd) {
        login_model.login(phone, pwd, new HttpCallback() {
            @Override
            public void OnSuccess(String result) {
                login_view.OnSuccess(result);
            }

            @Override
            public void OnFaile(String msg) {
                login_view.OnFaile(msg);
            }
        });
    }

}
