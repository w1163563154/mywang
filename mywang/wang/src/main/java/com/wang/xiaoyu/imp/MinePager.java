package com.wang.xiaoyu.imp;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.wang.xiaoyu.base.BasePager;

public class MinePager extends BasePager {

	public MinePager(Activity activity) {
		super(activity);
		
	}

	@Override
	public View initView() {
		TextView view = new TextView(mActivity);
		view.setText("我的");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		return view;
	}

}
