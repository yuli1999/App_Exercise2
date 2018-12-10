package com.umeng.soexample.app_exercise.net;

import android.text.TextUtils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * data:2018/12/5
 * author: HJL (磊)
 * function:  日志工具类
 */
public class LoggerUtil {

    public static void init() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(7)
                .logStrategy(null)
                .tag("hjl")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public static void json(String json) {
        if (!TextUtils.isEmpty(json)) {
            Logger.json(json);
        }
    }

    public static void json(String tag, String json) {
        if (!TextUtils.isEmpty(json)) {
            Logger.t(tag).json(json);
        }
    }

    public static void d(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Logger.d(msg);
        }
    }

    public static void d(String tag, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Logger.t(tag).d(msg);
        }
    }

    public static void e(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Logger.e(msg);
        }
    }

    public static void w(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Logger.w(msg);
        }
    }

    public static void v(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Logger.v(msg);
        }
    }

    public static void i(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Logger.i(msg);
        }
    }

}
