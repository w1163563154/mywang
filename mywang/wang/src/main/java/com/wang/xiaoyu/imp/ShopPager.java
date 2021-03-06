package com.wang.xiaoyu.imp;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wang.xiaoyu.Activity.ShopListItemActivity;
import com.wang.xiaoyu.Activity.SplashActivity;
import com.wang.xiaoyu.Adapter.MyAadapter;
import com.wang.xiaoyu.Holder.BaseHolder;
import com.wang.xiaoyu.Holder.popuListHolder;
import com.wang.xiaoyu.Holder.shopListHolder;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Utils.L;
import com.wang.xiaoyu.Utils.OkHttphelper;
import com.wang.xiaoyu.Utils.UIUtils;
import com.wang.xiaoyu.base.BasePager;
import com.wang.xiaoyu.domain.ShopData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShopPager extends BasePager implements CompoundButton.OnCheckedChangeListener{

	private ListView shopListView;
	private CheckBox selectarea;
	private CheckBox selectcarnumber;
	private CheckBox selectdistance;
	private TextView tv_title_bar;
	private PopupWindow mPopupWindow;
	private ListView mPopulistView;
	private ArrayList<String> mData;
	private ShopData mShopData;

	public int count=0;
	private SwipeRefreshLayout mShopRefresh;


	public ShopPager(Activity activity) {
		super(activity);
		
	}

	@Override
	public View initView() {		
		View view = View.inflate(mActivity, R.layout.paper_shop, null);		
		shopListView = (ListView) view.findViewById(R.id.lv_shop);
		selectarea = (CheckBox) view.findViewById(R.id.cb_shop_selectarea);
		selectcarnumber = (CheckBox) view.findViewById(R.id.cb_shop_selectcarnumber);
		selectdistance = (CheckBox) view.findViewById(R.id.cb_shop_selectdistance);
		tv_title_bar = (TextView) view.findViewById(R.id.tv_title_bar);
		//下拉刷新
		mShopRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srf_shoprefresh);
		mShopRefresh.setColorSchemeResources(R.color.cardview_shadow_start_color);
		mShopRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				initData();

				UIUtils.runOnUIThread(new Runnable() {
					@Override
					public void run() {
						mShopRefresh.setRefreshing(false);
					}
				});
			}
		});

		//CheckBox设置点击事件
		selectarea.setOnCheckedChangeListener(this);
		selectcarnumber.setOnCheckedChangeListener(this);
		selectdistance.setOnCheckedChangeListener(this);
		

		return view;
	}
	@Override
	public void initData() {

		tv_title_bar.setText("门店");

		Map<String, String> params = new HashMap<String, String>();
		//访问服务器
		//1.拿到okhttp对象
		//OkHttpClient okHttpClient = new OkHttpClient();


		OkHttpClient okHttpClient = OkHttphelper.getOkHttpClient();

		//放入键值对
		params.put("latitude",""+SplashActivity.latitude);
		params.put("longitude",""+SplashActivity.longitude);
		params.put("page",""+1);
		params.put("pageSize",""+3);

		Gson gson = new Gson();
		String jsonData = gson.toJson(params);

		MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，

		RequestBody body = RequestBody.create(JSON, jsonData);

		//2.构造request
		Request.Builder request = new Request.Builder();

		//3.将request封装为call
		Request builder = request
				.url("http://user.zglcfn.com:8763/store/getServiceStore")
                .post(body)
				.build();

		//4.执行call
		Call call = okHttpClient.newCall(builder);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
				L.e("请求失败ee"+e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String string = response.body().string();
				//L.e("数据"+string);
				//解析数据
				processData(string);

			}
		});

	}

	/**
	 * 解析json数据
	 * @param string json数据
	 */
	private void processData(String string) {

		Gson gson = new Gson();
		mShopData = gson.fromJson(string, ShopData.class);

        //在主线程中更新UI
		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				shopListView.setAdapter(new shopListViewAdapter(mShopData.data));
			}
		});
		//shopListView条目监听
		shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

				Intent intent = new Intent(mActivity,ShopListItemActivity.class);
				intent.putExtra("id",mShopData.data.get(position).id);
				mActivity.startActivity(intent);

				}
		});
	}

	public void showPopuWindow(final CheckBox v){
		//initListView(v);
		mPopulistView = new ListView(mActivity);

		mData = new ArrayList<String>();
		for(int i=0;i<10;i++){
			mData.add("你好"+i);
		}
		/*if(v==selectarea){
			mData = new ArrayList<String>();
			for(int i=0;i<10;i++){
				mData.add("你好"+i);
			}
		}*/
		mPopulistView.setAdapter(new popuListAdapter(mData));

		mPopulistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				L.e("第"+position+"被点击了");
				String s = mData.get(position);
				L.e(s);
				v.setText(s);

			}
		});

		// 显示下拉选择框
		mPopupWindow = new PopupWindow(mPopulistView, tv_title_bar.getWidth(), 1000);
		// 设置点击外部区域, 自动隐藏
        mPopupWindow.setWidth(tv_title_bar.getWidth());
        mPopupWindow.setHeight(700);
		mPopupWindow.setOutsideTouchable(true); // 外部可触摸
		mPopupWindow.setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.shape_popu)); // 设置空的背景, 响应点击事件
		mPopupWindow.setFocusable(true); //设置可获取焦点
		// 显示在指定控件下
		mPopupWindow.showAsDropDown(v, 10, 10);

	}

	public class popuListAdapter extends MyAadapter<String>{


        public popuListAdapter(ArrayList<String> data) {
            super(data);
        }

		@Override
		public BaseHolder getHolder() {
			return new popuListHolder();
		}

		@Override
		public boolean hasMore() {
			return false;
		}

		@Override
		public ArrayList<String> onLoadingMore() {
		/*	ArrayList<String> moreData = new ArrayList<String>();
			for(int i=0;i<15;i++){
				moreData.add("还有很多数据"+i);
			}*/
			return null;
		}

		/*@Override
		public boolean hasMore() {
			return false;
		}*/
	}

	@Override
	public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

		switch (compoundButton.getId()) {
			case R.id.cb_shop_selectarea:
				if(isChecked){
					showPopuWindow(selectarea);
					selectcarnumber.setChecked(false);
					selectdistance.setChecked(false);
                    selectarea.setChecked(false);
				}else{
					mPopupWindow.dismiss();
                    selectarea.setChecked(false);
				}

				break;
			case R.id.cb_shop_selectcarnumber:
				if(isChecked){
					showPopuWindow(selectcarnumber);
					selectarea.setChecked(false);
					selectdistance.setChecked(false);
                    selectcarnumber.setChecked(false);
				}else{
					mPopupWindow.dismiss();
                    selectarea.setChecked(false);
				}
				break;
			case R.id.cb_shop_selectdistance:
				if(isChecked){
					showPopuWindow(selectdistance);
                    selectdistance.setChecked(false);
					selectcarnumber.setChecked(false);
					selectarea.setChecked(false);
				}else{
					mPopupWindow.dismiss();
                    selectarea.setChecked(false);
				}
				break;

			default:
				break;
		}

	}

	class shopListViewAdapter extends MyAadapter<ShopData.BenData>{


        public shopListViewAdapter(ArrayList<ShopData.BenData> data) {
            super(data);
        }

		@Override
		public BaseHolder getHolder() {

			return new shopListHolder();
		}

		/**
		 * 控制是否有加载跟多
		 *
		 * @return true 有加载跟多  false没有加载更多
		 */
		@Override
		public boolean hasMore() {
			return false;
		}

		//实现加载更多方法
		@Override
		public ArrayList<ShopData.BenData> onLoadingMore() {
			return null;
		}

	}


}
