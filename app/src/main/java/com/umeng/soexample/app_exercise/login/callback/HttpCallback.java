package com.umeng.soexample.app_exercise.login.callback;

import java.util.List;

/**
 * data:2018/12/1
 * author: HJL (磊)
 * function:
 */
public interface HttpCallback {
    void OnSuccess(Object result);

    void OnFaile(String msg);
}
