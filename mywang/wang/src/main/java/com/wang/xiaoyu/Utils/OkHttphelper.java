package com.wang.xiaoyu.Utils;

import okhttp3.OkHttpClient;

/**
 * Created by 小 on 2018/9/22.
 */

public class OkHttphelper {
    private static OkHttpClient okHttpClient=null;

    public static OkHttpClient getOkHttpClient(){
        if(okHttpClient==null){
            synchronized (OkHttphelper.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }
        return okHttpClient;
    }

}
