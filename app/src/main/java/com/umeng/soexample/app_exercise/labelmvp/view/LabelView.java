package com.umeng.soexample.app_exercise.labelmvp.view;

import com.umeng.soexample.app_exercise.fragment.bean.LabelBean;

/**
 * data:2018/12/12
 * author: HJL (磊)
 * function:
 */
public interface LabelView {
    void labelSuccess(LabelBean labelBean);

    void onFaile(Exception msg);

}
