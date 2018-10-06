package com.wang.xiaoyu.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.mob.MobSDK;

/**
 * Created by 小 on 2018/9/17.
 */

public class GooglePlayApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
        MobSDK.init(this, this.getAppkey(), this.getAppSecret());


    }

    protected String getAppkey() {
        return "2814ae25018a0";
    }

    protected String getAppSecret() {
        return "d1da9b9679958fab3749b1518d58d4ff";
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }
}
