package com.umeng.soexample.app_exercise.login.view;

import com.umeng.soexample.app_exercise.login.bean.LoginBean;

import java.util.List;

/**
 * data:2018/12/1
 * author: HJL (磊)
 * function:
 */
public interface Login_View {
    void OnSuccess(LoginBean result);

    void OnFaile(String msg);
}
