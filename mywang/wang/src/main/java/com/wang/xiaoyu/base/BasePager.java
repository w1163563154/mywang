package com.wang.xiaoyu.base;

import android.app.Activity;
import android.view.View;

public abstract class BasePager {

	public Activity mActivity;
	public View mRootView;// 菜单详情页根布局
	public BasePager(Activity activity){
		mActivity = activity;
		mRootView = initView();
	}
	
	//初始化布局，由子类实现
	public abstract View initView();
	//初始化数据，由子类实现
	public void initData(){
		
	}
		
}
