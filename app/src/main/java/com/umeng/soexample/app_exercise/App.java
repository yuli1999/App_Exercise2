package com.umeng.soexample.app_exercise;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.soexample.app_exercise.net.HttpNet;
import com.umeng.soexample.app_exercise.net.LoggerUtil;

import java.util.HashMap;

/**
 * data:2018/12/3
 * author: HJL (磊)
 * function:
 */
public class App extends Application {

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        LoggerUtil.init();

        Fresco.initialize(this);//Fresco初始化

        HttpNet.init(); //Okhttp3 初始化

        sContext = this;


    }

}
