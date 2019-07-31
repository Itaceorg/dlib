package com.android.zolarrobot.dlib.base;

import android.app.Application;
import android.content.Context;

import com.flowerfat.volleyutil.main.VolleyUtils;

import org.xutils.x;

import timber.log.BuildConfig;
import timber.log.Timber;

/**
 * Created by DuanYuntian on 2019/7/30.
 */

public class BaseApplication extends Application {
    public static Context mContext;//全局上下文对象
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
//        VolleyUtils.getInstance().init(this);
        mContext = getApplicationContext();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.DebugTree());
        }
    }
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {

        }
    }
}
