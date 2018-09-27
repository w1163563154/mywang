package com.wang.xiaoyu.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.View.MyDialog;
import com.wang.xiaoyu.domain.PartsData;

import uk.co.senab.photoview.PhotoView;

public class CommodityPageActivity extends Activity implements View.OnClickListener {

	private ViewPager mViewPager;
	private Button addshopping,shoppicture,shopcar;
	public String[] imageUrls;
	public PartsData mPartsData;
	private TextView name;
	private TextView price;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_commoditypage);
		
		initUI();
		initData();
		initAdapet();
	}

	/**
	 * 设置适配器
	 */
	private void initAdapet() {
		System.out.println("3");

		//mViewPager.setCurrentItem(imageUrls.length * 1000);
	}

	/**
	 *初始化数据 
	 */
	private void initData() {
		System.out.println("1");
		Intent intent = getIntent();
		int id = intent.getIntExtra("id",0);
		System.out.println("id"+id);
		
		//访问配件详细网络
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, "http://user.zglcfn.com:8763/parts/getParts/"+id+"/1", new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				//System.out.println("配件详细="+result);
				//解析此数据
				processData(result);
				
				mViewPager.setAdapter(new MyAdapter());

				mViewPager.setCurrentItem(imageUrls.length*10000);
				name.setText(mPartsData.data.stdPartName);
				price.setText(""+mPartsData.data.retailPrice);
				
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
	protected PartsData processData(String result) {
		System.out.println("2");
		Gson gson = new Gson();
		mPartsData = gson.fromJson(result, PartsData.class);
		System.out.println(mPartsData);
		imageUrls = mPartsData.data.imageUrl.split(",");
		System.out.println("imageUrls"+imageUrls[0]);
		System.out.println("imageUrls"+imageUrls.length);
		return mPartsData;
		
		
	}

	/**
	 * 初始化数据
	 */
	private void initUI() {
		mViewPager = (ViewPager) findViewById(R.id.vp_commodity);
		addshopping = (Button) findViewById(R.id.bu_addshopping);//添加购物车

		name = (TextView) findViewById(R.id.tv_commodityap_name);
		price = (TextView) findViewById(R.id.tv_commodityap_price);

		findViewById(R.id.bu_buy).setOnClickListener(this); //购买按钮
		findViewById(R.id.bu_shopcar).setOnClickListener(this); //购物车
		
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
				case R.id.bu_buy:
					showMyDialog();
					break;
				case R.id.bu_shopcar:
					Intent intent = new Intent(getApplication(), TitleActivity.class);
					intent.putExtra("name","ShopCarFragment");
					startActivity(intent);
					break;

				default:
					break;
				}
	}

	//购买对话框
	private void showMyDialog() {
		 MyDialog dialog = new MyDialog(
				CommodityPageActivity.this);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();

	}

	class MyAdapter extends PagerAdapter{
		
		public BitmapUtils bitmapUtils;

		private MyAdapter(){
			//bitmapUtils = BitmapHelper.getBitmapUtils(getApplicationContext());
		  bitmapUtils = new BitmapUtils(getApplicationContext());

		}

		@Override
		public int getCount() {
			
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			
			return view==object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			position=position % imageUrls.length;

			PhotoView photoView = new PhotoView(getApplicationContext());
			photoView.setScaleType(PhotoView.ScaleType.FIT_XY);
			//ImageView imageView = new ImageView(getApplicationContext());
			//imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			bitmapUtils.display(photoView,imageUrls[position]);
			container.addView(photoView);
			return photoView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			container.removeView((View) object);
		}
		
	}
}
