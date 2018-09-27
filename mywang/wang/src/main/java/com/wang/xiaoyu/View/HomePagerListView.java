package com.wang.xiaoyu.View;



import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;
import com.wang.xiaoyu.Activity.TitleActivity;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.domain.HomePagerListData;
import com.wang.xiaoyu.domain.HomePagerListData.HomePagerListBaner;
import com.wang.xiaoyu.global.GlobalConstants;

import java.util.ArrayList;



public class HomePagerListView extends ListView {

	private View mHomeListHead;
	private HomeViewPager mViewPager;
	private MyGridview mGridView;
	private ArrayList<ImageView> mHeadimageViewsList;
	private String [] mTitleStrs;
	private int [] mDrawableIds;
	private CirclePageIndicator indicator;
	private HomePagerListData mHomePagerListData;
	private Handler mHandler;

	public HomePagerListView(Context context) {
		super(context);
		initHead();
	}

	public HomePagerListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHead();
	}

	public HomePagerListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initHead();
	}

	/**
	 * 初始化头布局
	 */
	private void initHead() {
		mHomeListHead = View.inflate(getContext(), R.layout.list_home_head, null);
		mViewPager = (HomeViewPager) mHomeListHead.findViewById(R.id.vp_list_home_head);
		mGridView = (MyGridview) mHomeListHead.findViewById(R.id.gv_home_head);
		indicator = (CirclePageIndicator) mHomeListHead.findViewById(R.id.indicator);
		
		//添加头布局
		addHeaderView(mHomeListHead);
		
		initData();
		
	}
	
	private void initData() {
				
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, GlobalConstants.SERVER_URL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				ParseJson(result);
			}
			@Override
			public void onFailure(HttpException error, String msg) {
				
			}

		});
		
		
									
	}
	
	/**
	 * 解析数据
	 * @param result 
	 */
	private void ParseJson(String result) {
		Gson gson = new Gson();
		mHomePagerListData = gson.fromJson(result, HomePagerListData.class);		
		System.out.println("解析结果"+mHomePagerListData);
		
		
		mTitleStrs = new String[]{"在线技师","美容","保养"
				,"维修","违规查询","保险代缴","道路救援","年审"};
		
		mDrawableIds = new int[]{
				R.drawable.home_btn_technician,R.drawable.home_btn_beauty,
				R.drawable.home_btn_maintain,R.drawable.home_btn_service,
				R.drawable.home_btn_break_rules,R.drawable.home_btn_insurance,
				R.drawable.home_btn_help,R.drawable.home_btn_exam};

		//设置数据适配器
		mViewPager.setAdapter(new ViewPagerAdapter());
		mGridView.setAdapter(new mGridViewAdapter());
		
		indicator.setViewPager(mViewPager);
        indicator.setSnap(false);
		
		//mGridView监听事件
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					//getContext().startActivity(new Intent(getContext(),textActivity.class));
					break;
				case 6:
					//getContext().startActivity(new Intent(getContext(),textActivity.class));
					break;
				case 7:
					Intent intent=new Intent(getContext(),TitleActivity.class);
					intent.putExtra("name","VerificationFragmnet");
					getContext().startActivity(intent);
					break;

				default:
					break;
				}
				
			}
			
		});
		
		
		if(mHandler==null){
			mHandler=new Handler(){
				@Override
				public void handleMessage(Message msg) {
					int currentItem = mViewPager.getCurrentItem();
					currentItem++;
					if(currentItem > mHomePagerListData.data.appHomeBaner.size()-1){
						currentItem=0; // 如果已经到了最后一个页面,跳到第一页
					}
					
					mViewPager.setCurrentItem(currentItem);
					
					mHandler.sendEmptyMessageDelayed(0,3000); // 继续发送延时3秒的消息,形成内循环
					
				}
			};
			
			mHandler.sendEmptyMessageDelayed(0,3000);
		}
		
	}

	class mGridViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			
			return mTitleStrs.length;
		}

		@Override
		public Object getItem(int position) {
			
			return mTitleStrs[position];
		}

		@Override
		public long getItemId(int position) {
			
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getContext(), R.layout.gridview_item, null);
			ImageView im_home_head_gv = (ImageView) view.findViewById(R.id.im_home_head_gv);
			TextView tv_homew_head_gv = (TextView) view.findViewById(R.id.tv_homew_head_gv);
			
			im_home_head_gv.setBackgroundResource(mDrawableIds[position]);
			
			tv_homew_head_gv.setText(mTitleStrs[position]);
			
			return view;
		}
		
	}
	
	class ViewPagerAdapter extends PagerAdapter{

		public BitmapUtils mBitmapUtils;
		public ViewPagerAdapter(){
			mBitmapUtils=new BitmapUtils(getContext());
		}
		
		@Override
		public int getCount() {
			
			return mHomePagerListData.data.appHomeBaner.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			
			return view==object;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
		    ImageView imageView = new ImageView(getContext());
		    imageView.setScaleType(ScaleType.FIT_XY);
		    
		    
			HomePagerListBaner homePagerListBaner = mHomePagerListData.data.appHomeBaner.get(position);
			
			mBitmapUtils.display(imageView,homePagerListBaner.photo);
			
			container.addView(imageView);
			
			return imageView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			container.removeView((View) object);
		}
		
	}

}
