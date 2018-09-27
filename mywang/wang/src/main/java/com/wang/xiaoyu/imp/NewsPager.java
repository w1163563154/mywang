package com.wang.xiaoyu.imp;


import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wang.xiaoyu.R;
import com.wang.xiaoyu.base.BasePager;

public class NewsPager extends BasePager {

	private ListView mListView;
	private TextView tv_title_bar;

	public NewsPager(Activity activity) {
		super(activity);
		
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.pager_news, null);
		tv_title_bar = (TextView) view.findViewById(R.id.tv_title_bar);
		mListView = (ListView) view.findViewById(R.id.lv_news);
		return view;
	}
	
	@Override
	public void initData() {
		tv_title_bar.setText("资讯");
		mListView.setAdapter(new MyAdapter());
		
		Event();//事件处理
	}
	
	private void Event() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("第"+position+"条目被点击了");
				
			}
		
		
		
		
		});
	}
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			
			return 20;
		}

		@Override
		public Object getItem(int position) {
			
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder;
			
			if(convertView==null){
				convertView=View.inflate(mActivity, R.layout.list_news_item, null);
				holder=new ViewHolder();
				holder.tv_title=(TextView) convertView.findViewById(R.id.tv_news_title);
				holder.tv_read=(TextView) convertView.findViewById(R.id.tv_news_read);
				holder.iv_pic=(ImageView) convertView.findViewById(R.id.iv_news_pic);
				convertView.setTag(holder);
			}else{
				holder=(ViewHolder) convertView.getTag();
			}
			
			
			return convertView;
		}
		
		
	}
	
	class ViewHolder{
		TextView tv_title;
		TextView tv_read;
		ImageView iv_pic;
	}

}
