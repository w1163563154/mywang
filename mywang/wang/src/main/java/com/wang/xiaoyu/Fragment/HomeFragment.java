package com.wang.xiaoyu.Fragment;


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.wang.xiaoyu.R;
import com.wang.xiaoyu.View.NoScrollViewpager;
import com.wang.xiaoyu.base.BasePager;
import com.wang.xiaoyu.imp.HomePager;
import com.wang.xiaoyu.imp.MinePager;
import com.wang.xiaoyu.imp.NewsPager;
import com.wang.xiaoyu.imp.ShopPager;
import com.wang.xiaoyu.imp.StorePager;

import java.util.ArrayList;

/**
 * 主页面Fragment
 * @author 小
 *
 */
public class HomeFragment extends BaseFragment {

	private NoScrollViewpager mNoScrollViewpager;
	private RadioGroup mRadioGroup;
	private ArrayList<BasePager> mBasePagerList;

	@Override
	public View initView() {
		
		View view = View.inflate(mActivity, R.layout.fragment_home, null);
		mNoScrollViewpager = (NoScrollViewpager) view.findViewById(R.id.vp_noscroll_home);
		mRadioGroup = (RadioGroup) view.findViewById(R.id.rg_group);
		
		return view;
	}

	@Override
	public void initData() {
		//添加五个标签页
		mBasePagerList = new ArrayList<BasePager>();
		mBasePagerList.add(new HomePager(mActivity));
		mBasePagerList.add(new ShopPager(mActivity));
		mBasePagerList.add(new StorePager(mActivity));
		mBasePagerList.add(new NewsPager(mActivity));
		mBasePagerList.add(new MinePager(mActivity));
		
		//设置数据适配器
		mNoScrollViewpager.setAdapter(new MyAdapter());
		//设置RadioGroup的监听事件
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					mNoScrollViewpager.setCurrentItem(0, false);
					break;
				case R.id.rb_shop:
					mNoScrollViewpager.setCurrentItem(1, false);
					break;
				case R.id.rb_store:
					mNoScrollViewpager.setCurrentItem(2, false);
					break;
				case R.id.rb_news:
					mNoScrollViewpager.setCurrentItem(3, false);
					break;
				case R.id.rb_mine:
					mNoScrollViewpager.setCurrentItem(4, false);
					break;

				default:
					break;
				}
				
			}
		});
		mNoScrollViewpager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				BasePager Pager = mBasePagerList.get(position);
				Pager.initData();
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
			
			return mBasePagerList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			
			
			return view==object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager basePager = mBasePagerList.get(position);
			View view = basePager.initView();// 获取当前页面对象的布局
			container.addView(view);
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			container.removeView((View) object);
		}
		
	}

}
