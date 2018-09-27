package com.wang.xiaoyu.Activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.location.LocationClientOption.LocationMode;

import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Utils.SpUtils;




public class SplashActivity extends Activity {

	public static double latitude;
	public static double longitude;
	private RelativeLayout rl_splash;
	public LocationClient mLocationClient;

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		mLocationClient = new LocationClient(getApplicationContext()); 	
		mLocationClient.registerLocationListener(new MyLocationListener());
		setContentView(R.layout.activity_splash);
		
		
		initUI();
		
		init();
		initAnim();
		
		
		
	}

	/**
	 * 开启定位
	 */
	private void init() {
		initLocation();
		mLocationClient.start();
		
	}

	/**
	 * 设置定位参数
	 */
	private void initLocation() {
		 LocationClientOption option = new LocationClientOption();
	        option.setLocationMode(LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
	        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
	        int span= 5000;
	        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
	        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
	        option.setOpenGps(true);//可选，默认false,设置是否使用gps
	        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
	        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
	        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
	        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
	        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
	        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
	        mLocationClient.setLocOption(option);
		
		
	}
	
public class MyLocationListener implements BDLocationListener {
		 
		

		// 在这个方法里面接收定位结果
        @Override
        public void onReceiveLocation(BDLocation location) {
        	      	
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            latitude = location.getLatitude();//获取纬度
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            longitude = location.getLongitude();//获取精度
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
            	sb.append("\nspeed : ");
            	sb.append(location.getSpeed());// 单位：公里每小时
            	sb.append("\nsatellite : ");
            	sb.append(location.getSatelliteNumber());
            	sb.append("\nheight : ");
            	sb.append(location.getAltitude());// 单位：米
            	sb.append("\ndirection : ");
            	sb.append(location.getDirection());// 单位度
            	sb.append("\naddr : ");
            	sb.append(location.getAddrStr());
            	sb.append("\ndescribe : ");
            	sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
            	sb.append("\naddr : ");
            	sb.append(location.getAddrStr());
            	//运营商信息
            	sb.append("\noperationers : ");
            	sb.append(location.getOperators());
            	sb.append("\ndescribe : ");
            	sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            	sb.append("\ndescribe : ");
            	sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
            	sb.append("\ndescribe : ");
            	sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            	sb.append("\ndescribe : ");
            	sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            	sb.append("\ndescribe : ");
            	sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
            	sb.append("\npoilist size = : ");
            	sb.append(list.size());
            	for (Poi p : list) {
            		sb.append("\npoi= : ");
            		sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            	}
            }
            //System.out.println(sb.toString());
        }
	}
 

	private void initAnim() {
		
		RotateAnimation rotateAnimation = new RotateAnimation(
				0f, 360f, 
				RotateAnimation.RELATIVE_TO_SELF, 0.5f, 
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		
		rotateAnimation.setDuration(1500);
		rotateAnimation.setFillAfter(true);
		
		
		ScaleAnimation scaleAnimation=new ScaleAnimation(
				0f, 1f, 
				0f, 1f, 
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(1500);
		scaleAnimation.setFillAfter(true);
		
		
		AlphaAnimation alphaAnimation=new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(1500);
		alphaAnimation.setFillAfter(true);
		
		
		AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(rotateAnimation);
		animationSet.addAnimation(scaleAnimation);
		animationSet.addAnimation(alphaAnimation);
		
		rl_splash.startAnimation(animationSet);
		
		animationSet.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				
				boolean first_enter = SpUtils.getBoolean(getApplicationContext(), "is_first_enter", true);
				
				if(first_enter){
					
					startActivity(new Intent(getApplicationContext(),GuideActivity.class));
					
					finish();
				}else{
					
					startActivity(new Intent(getApplicationContext(),MainActivity.class));
					finish();
				}
				
			}
		});
		
		
	}

	
	private void initUI() {
		rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
		
	}
}


