package com.wang.xiaoyu.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewpager extends ViewPager {

	public NoScrollViewpager(Context context) {
		super(context);
		
	}

	public NoScrollViewpager(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	// 重写此方法, 触摸时什么都不做, 从而实现对滑动事件的禁用
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
		return true;
	}
	
	// 事件拦截
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		
		return false;// 不拦截子控件的事件
	}

}
