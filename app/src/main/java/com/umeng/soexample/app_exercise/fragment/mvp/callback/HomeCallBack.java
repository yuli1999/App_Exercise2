package com.umeng.soexample.app_exercise.fragment.mvp.callback;

import com.umeng.soexample.app_exercise.fragment.bean.ReOneBean;

import java.util.List;

/**
 * data:2018/12/7
 * author: HJL (磊)
 * function:
 */
public interface HomeCallBack {
    void onSuccess(List data);

    void onFaile(String msg);
}
