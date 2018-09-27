package com.wang.xiaoyu.Activity;

import com.wang.xiaoyu.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsActivity extends Activity {

	
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news);
		
		
		initUI();
	}

	private void initUI() {
		mWebView = (WebView) findViewById(R.id.wv_news);		
		WebSettings settings = mWebView.getSettings();
		settings.setBuiltInZoomControls(true);//显示缩放按钮
		settings.setUseWideViewPort(true);//支持双击缩放
		settings.setJavaScriptEnabled(true);//支持js功能
		
		mWebView.setWebViewClient(new WebViewClient(){
			// 开始加载网页
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
			}
			// 网页加载结束
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}
			// 所有链接跳转会走此方法
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);// 在跳转链接时强制在当前webview中加载
				return true;
			}
		});
	}
}
