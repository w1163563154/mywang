package com.wang.xiaoyu.imp;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wang.xiaoyu.Activity.CommodityPageActivity;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.View.HomePagerListView;
import com.wang.xiaoyu.base.BasePager;
import com.wang.xiaoyu.domain.HomeData;
import com.wang.xiaoyu.domain.HomeData.appHomeActivity;
import com.wang.xiaoyu.global.GlobalConstants;




public class HomePager extends BasePager {

	private HomePagerListView zdListView;
	
	private HomeData mHomwData;
	

	public HomePager(Activity activity) {
		super(activity);
				
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.pager_home, null);
		zdListView = (HomePagerListView) view.findViewById(R.id.zd_lv_homepager);
		
		initdata();
		
		
		return view;
	}
	
	//初始化数据
	
	public void initdata() {
	//请求服务器	
     HttpUtils httpUtils = new HttpUtils();
		
		httpUtils.send(HttpMethod.GET, GlobalConstants.SERVER_URL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				String result = responseInfo.result;
				
				
				//解析数据
				processData(result);
				System.out.println("初始化首页数据了");
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// 请求失败
				error.printStackTrace();
				System.out.println("请求失败");
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				
			}
		
				
		
		});

	};
	
	//解析数据
	protected void processData(String json) {
		Gson gson = new Gson();
		mHomwData = gson.fromJson(json, HomeData.class);
		
		System.out.println("解析此数据了");
		
		
		zdListView.setAdapter(new MyAdapter());
		
	}

	class MyAdapter extends BaseAdapter{

		
		
		@Override
		public int getCount() {
			
			return (int) (mHomwData.data.appHomeActivity.size()/2+1.5);
		}
		
		@Override
		public int getItemViewType(int position) {
			if(position==0){
				//返回0代表为（喇叭加文字条目）
				return 0;
			}else{
				//返回0代表为（图片+文字)
				return 1;
			}
			
		}
		
		@Override
		public int getViewTypeCount() {
			
			return super.getViewTypeCount()+1;
		}

		@Override
		public appHomeActivity getItem(int position) {
			
			if(position==0){
				return null;
				
			}else{		
			return mHomwData.data.appHomeActivity.get(position-1);
			
			}
		}

		@Override
		public long getItemId(int position) {
			
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			int type = getItemViewType(position);
			if(type==0){
				//展示喇叭条目
				ViewHolder viewHolder;
				if(convertView==null){				
					convertView=View.inflate(mActivity, R.layout.list_home_title, null);
					viewHolder = new ViewHolder();
					viewHolder.wang_view_Loop=(TextView) convertView.findViewById(R.id.wang_view_Loop);	
					convertView.setTag(viewHolder);			
				}else{
					
					viewHolder = (ViewHolder) convertView.getTag();
				}
				return convertView;
				
			}else{
				//展示文字+图片条目
				ViewHolder viewHolder;
				if(convertView==null){
					convertView=View.inflate(mActivity, R.layout.list_home_ite, null);
					viewHolder = new ViewHolder();
					viewHolder.ListItemGridView=(GridView) convertView.findViewById(R.id.gv_home_list_item);
					convertView.setTag(viewHolder);
				}else{
					viewHolder = (ViewHolder) convertView.getTag();
				}
		    
				//设置数据适配器
				viewHolder.ListItemGridView.setAdapter(new ListGridViewAdapter());
				
				//ListItemGridView点击事件
				viewHolder.ListItemGridView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						
						System.out.println("第"+position+"被点击了");
						
						Intent intent = new Intent(mActivity,CommodityPageActivity.class);
						intent.putExtra("id",mHomwData.data.appHomeActivity.get(position).id);
						System.out.println("id="+mHomwData.data.appHomeActivity.get(position).id);
						
						mActivity.startActivity(intent);
					}
					
				});
				
			
			return convertView;
		}
		
		}
	}

	class ViewHolder{
		ImageView iv_home_Bitmap;
		TextView wang_view_Loop,tv_home_Bitmap_String,tv_home_Bitmap_price;
		GridView ListItemGridView;
	}
	
	class ListGridViewAdapter extends BaseAdapter{
     
		private BitmapUtils mBitmapUtils;
		
		public ListGridViewAdapter(){
			mBitmapUtils= new BitmapUtils(mActivity);
			//mBitmapUtils.configDefaultLoadingImage(R.drawable.text);
		}
		

		@Override
		public int getCount() {
			
			return mHomwData.data.appHomeActivity.size();
		}

		@Override
		public appHomeActivity getItem(int position) {
			
			return mHomwData.data.appHomeActivity.get(position);
		}

		@Override
		public long getItemId(int position) {
			
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if(convertView==null){
				viewHolder=new ViewHolder();
				convertView=View.inflate(mActivity, R.layout.gv_list_item, null);
				viewHolder.iv_home_Bitmap=(ImageView) convertView.findViewById(R.id.iv_home_Bitmap);
				viewHolder.tv_home_Bitmap_String=(TextView) convertView.findViewById(R.id.tv_home_Bitmap_String);
				viewHolder.tv_home_Bitmap_price=(TextView) convertView.findViewById(R.id.tv_home_Bitmap_price);
				convertView.setTag(viewHolder);
			}else{
				viewHolder=(ViewHolder) convertView.getTag();
			}
			
			appHomeActivity item = getItem(position);
			
			mBitmapUtils.display(viewHolder.iv_home_Bitmap, item.imageUrl);
			viewHolder.tv_home_Bitmap_String.setText(item.stdPartName);
			viewHolder.tv_home_Bitmap_price.setText(""+item.retailPrice);
			return convertView;
		}
		
	}
	
	
}
