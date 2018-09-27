package com.wang.xiaoyu.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

	private static SharedPreferences sp;
	//存,取Boolean类型的值
	public static boolean getBoolean(Context cxt,String key,boolean defValue){
		if(sp==null){
			sp = cxt.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
			
		return sp.getBoolean(key, defValue);
		
	}
	
	public static void putBoolean(Context cxt,String key,boolean defValue){
		if(sp==null){
			sp = cxt.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
			
		sp.edit().putBoolean(key, defValue).commit();
		
	}
	//存,取String类型的值
	public static String getString(Context cxt,String key,String defValue){
		if(sp==null){
			sp = cxt.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
			
		return sp.getString(key, defValue);
		
	}
	
	public static void putString(Context cxt,String key,String defValue){
		if(sp==null){
			sp = cxt.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
			
		sp.edit().putString(key, defValue).commit();
		
	}
	//存,取int类型的值
	public static int getInt(Context cxt,String key,int defValue){
		if(sp==null){
			sp = cxt.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		
		return sp.getInt(key, defValue);
		
	}
	
	public static void putInt(Context cxt,String key,int defValue){
		if(sp==null){
			sp = cxt.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		
		sp.edit().putInt(key, defValue).commit();
		
	}
}
