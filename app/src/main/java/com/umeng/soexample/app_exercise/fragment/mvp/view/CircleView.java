package com.umeng.soexample.app_exercise.fragment.mvp.view;

import com.umeng.soexample.app_exercise.fragment.bean.CircleBean;

/**
 * data:2018/12/13
 * author: HJL (磊)
 * function:
 */
public interface CircleView {
    void onCircle(CircleBean data);

    void onCircleFaile(String msg);
}
