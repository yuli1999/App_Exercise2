package com.umeng.soexample.app_exercise.fragment.mvp.presenter;

import com.umeng.soexample.app_exercise.fragment.bean.CircleBean;
import com.umeng.soexample.app_exercise.fragment.mvp.callback.CircleCallBack;
import com.umeng.soexample.app_exercise.fragment.mvp.model.CircleModel;
import com.umeng.soexample.app_exercise.fragment.mvp.view.CircleView;

/**
 * data:2018/12/13
 * author: HJL (磊)
 * function:
 */
public class CirclePresenter {
    private CircleView circleView;
    private CircleModel circleModel;

    public CirclePresenter(CircleView circleView) {
        this.circleView = circleView;
        circleModel = new CircleModel();
    }

    public void circle(int page, int count) {
        circleModel.circle(page, count, new CircleCallBack() {
            @Override
            public void circleSuccess(Object data) {
                CircleBean circleBean = (CircleBean) data;
                if (circleBean != null) {
                    circleView.onCircle(circleBean);
                }
            }

            @Override
            public void circleFaile(String data) {
                circleView.onCircleFaile(data);
            }
        });
    }

}
