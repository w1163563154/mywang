package com.wang.xiaoyu.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.mob.MobSDK;

import cn.jpush.android.api.JPushInterface;

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
        MobSDK.init(context, "2814ae25018a0", "d1da9b9679958fab3749b1518d58d4ff");
        //初始化sdk
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);
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
