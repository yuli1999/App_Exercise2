package com.umeng.soexample.app_exercise.user.footprint.mvp;

import com.umeng.soexample.app_exercise.user.footprint.MyFootBean;

/**
 * data:2018/12/16
 * author: HJL (磊)
 * function:
 */
public interface MyFootView {
    void onCircle(MyFootBean data);

    void OnFaile(Exception e);
}
