package com.umeng.soexample.app_exercise.user.circle.mvp;

import com.umeng.soexample.app_exercise.user.circle.MyCircleBean;

/**
 * data:2018/12/16
 * author: HJL (磊)
 * function:
 */
public interface MyCircleView {
    void onCircle(MyCircleBean data);

    void OnFaile(Exception e);
}
