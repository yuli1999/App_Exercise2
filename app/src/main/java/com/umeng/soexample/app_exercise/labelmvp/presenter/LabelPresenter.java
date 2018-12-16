package com.umeng.soexample.app_exercise.labelmvp.presenter;

import com.umeng.soexample.app_exercise.fragment.bean.LabelBean;
import com.umeng.soexample.app_exercise.labelmvp.LabelCallBack;
import com.umeng.soexample.app_exercise.labelmvp.model.LabelModel;
import com.umeng.soexample.app_exercise.labelmvp.view.LabelView;

/**
 * data:2018/12/12
 * author: HJL (磊)
 * function:
 */
public class LabelPresenter {
    private LabelView labelView;
    private LabelModel labelModel;

    public LabelPresenter(LabelView labelView) {
        this.labelView = labelView;
        labelModel = new LabelModel();
    }

    public void label(String labelId, int page, int count) {
        labelModel.labelshow(labelId, page, count, new LabelCallBack() {
            @Override
            public void OnLabelSuccess(Object result) {
                LabelBean labelBean = (LabelBean) result;
                labelView.labelSuccess(labelBean);
            }

            @Override
            public void OnFaile(Exception e) {
                labelView.onFaile(e);
            }
        });
    }
}
