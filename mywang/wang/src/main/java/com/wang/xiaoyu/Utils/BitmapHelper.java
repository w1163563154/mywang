package com.wang.xiaoyu.Utils;


import android.content.Context;

import com.lidroid.xutils.BitmapUtils;

public class BitmapHelper {

	private static BitmapUtils bitmapUtils=null;
	
	public static BitmapUtils getBitmapUtils(Context cxt){
		if(bitmapUtils==null){
			synchronized (BitmapHelper.class) {
				if (bitmapUtils == null) {
					bitmapUtils = new BitmapUtils(cxt);
				}
			}
		}
		return bitmapUtils;
	}
}
