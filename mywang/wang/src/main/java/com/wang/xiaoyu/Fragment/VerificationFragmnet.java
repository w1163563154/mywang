package com.wang.xiaoyu.Fragment;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.wang.xiaoyu.Activity.TitleActivity;
import com.wang.xiaoyu.R;

public class VerificationFragmnet extends BaseFragment {

	private TextView tv_left;
	private TextView tv_right;

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragmnet_verification, null);
		tv_left = (TextView) view.findViewById(R.id.tv_left);
		tv_right = (TextView) view.findViewById(R.id.tv_right);
		TextView tv_title_bar = (TextView) view.findViewById(R.id.tv_title_bar);
		Button bu_order = (Button) view.findViewById(R.id.bu_verification_order);
		tv_title_bar.setText("年审");
		
		bu_order.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TitleActivity activity = (TitleActivity) getActivity();
				/*FragmentManager fm = activity.getSupportFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.replace(R.id.fl_verification, new VerificationsecondFragmnet(), "Verification");
				transaction.addToBackStack(null);
				transaction.commit();*/
				Intent intent=new Intent(activity,TitleActivity.class);
				intent.putExtra("name","VerificationsecondFragmnet");
				activity.startActivity(intent);
				
			}
		});
		
		return view;
	}

	@Override
	public void initData() {
		String lefttext = "<font color = '#0000ff'> 个人：</font>\n"+"1.行驶证原件\n" +
				"2.交强险副本\n"+"3.身份证复印件";
		 tv_left.setText(Html.fromHtml(lefttext));
		 String righttext = "<font color = '#0000ff'> 单位：</font>\n"
				 + "1.行驶证原件\n"+"2.交强险副本\n"
				 +"中华人名共和国组织机构代码证(盖鲜章)\n"+"4.委托书(盖鲜章)\n"+"5.代理人身份复印件";
		 tv_right.setText(Html.fromHtml(righttext));

	}

}
