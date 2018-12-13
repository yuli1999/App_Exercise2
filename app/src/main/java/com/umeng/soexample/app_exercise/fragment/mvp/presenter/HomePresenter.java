package com.umeng.soexample.app_exercise.fragment.mvp.presenter;

import com.umeng.soexample.app_exercise.fragment.mvp.callback.HomeCallBack;
import com.umeng.soexample.app_exercise.fragment.mvp.model.HomeModel;
import com.umeng.soexample.app_exercise.fragment.mvp.view.HomeView;

import java.util.List;

/**
 * data:2018/12/7
 * author: HJL (磊)
 * function:
 */
public class HomePresenter {

    private HomeView homeView;
    private HomeModel homeModel;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        homeModel = new HomeModel();
    }


    //轮播图
    public void bann() {
        homeModel.bann(new HomeCallBack() {
            @Override
            public void onSuccess(List data) {
                homeView.bann(data);
            }

            @Override
            public void onFaile(String msg) {
                homeView.onFaile(msg);
            }
        });
    }


    //第一个
    public void reone() {
        homeModel.reone(new HomeCallBack() {
            @Override
            public void onSuccess(List data) {
                homeView.onOne(data);
            }

            @Override
            public void onFaile(String msg) {
                homeView.onFaile(msg);
            }
        });
    }

    //第二个
    public void retwo() {
        homeModel.retwo(new HomeCallBack() {
            @Override
            public void onSuccess(List data) {
                homeView.onTwo(data);
            }

            @Override
            public void onFaile(String msg) {
                homeView.onFaile(msg);
            }
        });
    }

    //第三个
    public void rethree() {
        homeModel.rethree(new HomeCallBack() {
            @Override
            public void onSuccess(List data) {
                homeView.onThree(data);
            }

            @Override
            public void onFaile(String msg) {
                homeView.onFaile(msg);
            }
        });
    }


}
