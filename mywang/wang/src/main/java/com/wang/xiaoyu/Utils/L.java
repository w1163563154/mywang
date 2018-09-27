package com.wang.xiaoyu.Utils;

import android.util.Log;

/**
 * Created by 小 on 2018/9/13.
 */

public class L {

    private static final String TAG ="data";
    private static  boolean debug=true;

    public static void e(String msg){
        if(debug){
            Log.e(TAG,msg);
        }
    }
}
