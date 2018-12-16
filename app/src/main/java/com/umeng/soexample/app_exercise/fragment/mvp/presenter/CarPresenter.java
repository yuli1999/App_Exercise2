package com.umeng.soexample.app_exercise.fragment.mvp.presenter;

import com.umeng.soexample.app_exercise.fragment.bean.CarBean;
import com.umeng.soexample.app_exercise.fragment.mvp.callback.CarCallBack;
import com.umeng.soexample.app_exercise.fragment.mvp.model.CarModel;
import com.umeng.soexample.app_exercise.fragment.mvp.view.CarView;

/**
 * data:2018/12/13
 * author: HJL (磊)
 * function:
 */
public class CarPresenter {
    private CarView carView;
    private CarModel carModel;

    public CarPresenter(CarView carView) {
        this.carView = carView;
        carModel = new CarModel();
    }

    public void car() {
        carModel.car(new CarCallBack() {
            @Override
            public void carSuccess(Object data) {
                CarBean carBean = (CarBean) data;
                if (carBean != null) {
                    carView.onCar(carBean);
                }
            }

            @Override
            public void circleFaile(Exception e) {
                carView.onCircleFaile(e);
            }
        });
    }
}
