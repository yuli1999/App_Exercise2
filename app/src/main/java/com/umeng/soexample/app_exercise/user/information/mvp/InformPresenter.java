package com.umeng.soexample.app_exercise.user.information.mvp;

import com.umeng.soexample.app_exercise.user.information.InformBean;

/**
 * data:2018/12/13
 * author: HJL (磊)
 * function:
 */
public class InformPresenter {
    private InformView informView;
    private InformModel informModel;

    public InformPresenter(InformView informView) {
        this.informView = informView;
        informModel = new InformModel();
    }

    public void inform() {
        informModel.inform(new InformCallBack() {
            @Override
            public void OnInformSuccess(Object result) {
                InformBean informBean = (InformBean) result;
                if (informBean!=null & "0000".equals(informBean.getStatus())) {
                    informView.OnInformSuccess(informBean);
                }
            }

            @Override
            public void OnFaile(Exception e) {
                informView.OnFaile(e);
            }
        });
    }


}
