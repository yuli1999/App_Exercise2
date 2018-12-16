package com.umeng.soexample.app_exercise.user.footprint.mvp;

import com.umeng.soexample.app_exercise.user.footprint.MyFootBean;

/**
 * data:2018/12/16
 * author: HJL (磊)
 * function:
 */
public class MyFootPresenter {
    private MyFootView footView;
    private MyFootModel footModel;

    public MyFootPresenter(MyFootView footView) {
        this.footView = footView;
        footModel = new MyFootModel();
    }

    public void myfoot(int page, int count) {
        footModel.myFoot(page, count, new MyFootCallBack() {
            @Override
            public void OnfootSuccess(Object result) {
                MyFootBean myCircleBean = (MyFootBean) result;
                if (myCircleBean != null) {
                    footView.onCircle(myCircleBean);
                }
            }

            @Override
            public void OnFaile(Exception e) {
                footView.OnFaile(e);
            }
        });
    }

}
