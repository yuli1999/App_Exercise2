package com.umeng.soexample.app_exercise.net;

/**
 * data:2018/12/5
 * author: HJL (磊)
 * function:
 */
public interface HttpDataListener {
    void onSuccess(String data);

    void onFile(String error);
}
