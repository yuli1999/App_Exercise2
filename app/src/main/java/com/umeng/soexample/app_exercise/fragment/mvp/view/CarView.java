package com.umeng.soexample.app_exercise.fragment.mvp.view;

import com.umeng.soexample.app_exercise.fragment.bean.CarBean;

/**
 * data:2018/12/13
 * author: HJL (磊)
 * function:
 */
public interface CarView {
    void onCar(CarBean data);

    void onCircleFaile(Exception e);
}
