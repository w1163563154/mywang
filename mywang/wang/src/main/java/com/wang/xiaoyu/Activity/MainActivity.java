package com.wang.xiaoyu.Activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Fragment.HomeFragment;

public class MainActivity extends FragmentActivity {

	private static final String TAG_HOME = "TAG_HOME";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题,
		setContentView(R.layout.activity_main);
		
		initFragment();
	}

	/**
	 * 初始化Fragment
	 */
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.fl_main, new HomeFragment(), TAG_HOME);
		transaction.commit();
	}
}
