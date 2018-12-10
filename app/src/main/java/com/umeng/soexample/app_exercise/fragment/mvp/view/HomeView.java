package com.umeng.soexample.app_exercise.fragment.mvp.view;

import com.umeng.soexample.app_exercise.fragment.bean.ReOneBean;

import java.util.List;

/**
 * data:2018/12/7
 * author: HJL (磊)
 * function:
 */
public interface HomeView {
    void onSuccess(List data);

    void onTwo(List data);

    void onThree(List data);

    void onFaile(String msg);
}
