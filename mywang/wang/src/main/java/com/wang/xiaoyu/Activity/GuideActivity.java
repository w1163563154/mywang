package com.wang.xiaoyu.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.wang.xiaoyu.Activity.MainActivity;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Utils.SpUtils;

import java.util.ArrayList;







@SuppressLint("ResourceAsColor")
public class GuideActivity extends Activity {

private ViewPager vp;
private Button bt_guide;
private ArrayList<ImageView> guidePictureList;



@Override
protected void onCreate(Bundle savedInstanceState) {
	
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.activity_guide);
	
	initUI();
	initData();
	
	vp.setAdapter(new MyAdapter());
	
	
	vp.setOnPageChangeListener(new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int position) {
			if(position==guidePictureList.size()-1){
				bt_guide.setVisibility(View.VISIBLE);
			}else{
				bt_guide.setVisibility(View.INVISIBLE);
			}
			
		}
		
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			
			
		}
		
		@Override
		public void onPageScrollStateChanged(int state) {
			
			
		}
	});
	
	
}


class MyAdapter extends PagerAdapter{

	@Override
	public int getCount() {
		
		return guidePictureList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		
		return view==object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imageView = guidePictureList.get(position);
		container.addView(imageView);
		return imageView;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		
		container.removeView((View) object);
	}
	
	
}

private void initData() {
	guidePictureList = new ArrayList<ImageView>();
	
	int [] guidePicture=new int []{R.drawable.guide1,R.drawable.guide2,R.drawable.guide3};

	for(int i=0;i<guidePicture.length;i++){
	ImageView imageView = new ImageView(this);
	imageView.setBackgroundResource(guidePicture[i]);
	guidePictureList.add(imageView);
	}
	  
	
}




private void initUI() {
	vp = (ViewPager) findViewById(R.id.vp_guide);
	bt_guide = (Button) findViewById(R.id.bt_guide);
	
	bt_guide.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startActivity(new Intent(getApplicationContext(),MainActivity.class));
			SpUtils.putBoolean(getApplicationContext(), "is_first_enter", false);
			finish();
		}
	});
}

	


}
