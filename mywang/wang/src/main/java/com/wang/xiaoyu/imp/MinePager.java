package com.wang.xiaoyu.imp;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.mob.MobSDK;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.base.BasePager;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class MinePager extends BasePager {

	public MinePager(Activity activity) {
		super(activity);
		
	}

	@Override
	public View initView() {
		Button view = new Button(mActivity);
		view.setText("我的");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				share();
			}


		});
		return view;
	}

	private void share() {
		MobSDK.init(mActivity);
		OnekeyShare oks = new OnekeyShare();
		//关闭sso授权
		oks.disableSSOWhenAuthorize();
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
		oks.setTitle("标题");
		// titleUrl是标题的网络链接，QQ和QQ空间等使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("Testing message");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(mActivity.getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(mActivity);
	}

}
