package com.umeng.soexample.app_exercise.login.callback;

/**
 * data:2018/12/1
 * author: HJL (磊)
 * function:
 */
public interface HttpCallback {
    void OnSuccess(String result);

    void OnFaile(String msg);
}
