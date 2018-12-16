package com.umeng.soexample.app_exercise.user.circle.mvp;

import com.umeng.soexample.app_exercise.fragment.mvp.callback.CircleCallBack;
import com.umeng.soexample.app_exercise.user.circle.MyCircleBean;

/**
 * data:2018/12/16
 * author: HJL (磊)
 * function:
 */
public class MyCirclePresenter {
    private MyCircleView circleView;
    private MyCircleModel circleModel;

    public MyCirclePresenter(MyCircleView circleView) {
        this.circleView = circleView;
        circleModel = new MyCircleModel();
    }

    public void mycircle(int page, int count) {
        circleModel.myCircle(page, count, new MyCircleCallBack() {
            @Override
            public void OnCircleSuccess(Object result) {
                MyCircleBean myCircleBean = (MyCircleBean) result;
                if (myCircleBean != null) {
                    circleView.onCircle(myCircleBean);
                }
            }

            @Override
            public void OnFaile(Exception e) {
                circleView.OnFaile(e);
            }
        });
    }

}
