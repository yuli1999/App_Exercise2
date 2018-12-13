package com.umeng.soexample.app_exercise.user.information.mvp;

import com.umeng.soexample.app_exercise.user.information.InformBean;

/**
 * data:2018/12/13
 * author: HJL (磊)
 * function:
 */
public interface InformView {
    void OnInformSuccess(InformBean result);

    void OnFaile(Exception e);
}
