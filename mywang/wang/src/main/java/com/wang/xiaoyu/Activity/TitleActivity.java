package com.wang.xiaoyu.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.FrameLayout;

import com.wang.xiaoyu.Fragment.ConfirmOrderFragment;
import com.wang.xiaoyu.Fragment.ShopCarFragment;
import com.wang.xiaoyu.Fragment.VerificationFragmnet;
import com.wang.xiaoyu.Fragment.VerificationsecondFragmnet;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Utils.L;

public class TitleActivity extends FragmentActivity {
	private static final String TAG_HOME = "Verification";
	private static final String TAG_CONFIRMORDER = "ConfirmOrder";
	private String mStartName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题,
		setContentView(R.layout.activity_verification);
		
		FrameLayout fl_verification = (FrameLayout) findViewById(R.id.fl_verification);

		Intent intent = getIntent();
		mStartName = intent.getStringExtra("name");
		L.e("mStartName"+mStartName);
		initFragment(mStartName);
	}

	private void initFragment(String name) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		switch (name) {
				case "ConfirmOrderFragment": //确认订单fragment
					transaction.replace(R.id.fl_verification, new ConfirmOrderFragment(), "ConfirmOrderFragment");
					//transaction.addToBackStack(null);
					transaction.commit();
					break;
				case "VerificationFragmnet"://年审fragment
					transaction.replace(R.id.fl_verification, new VerificationFragmnet(), "VerificationFragmnet");
					transaction.commit();
					break;
				case "VerificationsecondFragmnet"://年审2fragment
					transaction.replace(R.id.fl_verification, new VerificationsecondFragmnet(), "VerificationsecondFragmnet");
					transaction.commit();
					break;
				case "ShopCarFragment"://购物车fragment
					transaction.replace(R.id.fl_verification, new ShopCarFragment(), "ShopCarFragment");
					transaction.commit();
					break;



				default:
					break;
				}




		/*if(mStartName==1){
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.replace(R.id.fl_verification, new VerificationFragmnet(), "Verification");
			//transaction.addToBackStack(null);
			transaction.commit();
		}else{

			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.replace(R.id.fl_verification, new VerificationsecondFragmnet(), "Verificationsecond");
			//transaction.addToBackStack(null);
			transaction.commit();
		}*/





	}
	
	
}
