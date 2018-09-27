package com.wang.xiaoyu.Activity;

import android.animation.FloatEvaluator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wang.xiaoyu.Adapter.MyAadapter;
import com.wang.xiaoyu.Holder.BaseHolder;
import com.wang.xiaoyu.Holder.ShopDetallGridViewHolder;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Utils.L;
import com.wang.xiaoyu.Utils.OkHttphelper;
import com.wang.xiaoyu.Utils.UIUtils;
import com.wang.xiaoyu.View.MyGridview;
import com.wang.xiaoyu.View.MyScrollView;
import com.wang.xiaoyu.View.NoScrollListView;
import com.wang.xiaoyu.domain.ShopDetallData;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

;

public class ShopListItemActivity extends Activity implements MyScrollView.ScrollViewListener {

    private MyScrollView mShopListScrollView;
    private MyGridview mShopListGridview;
    private NoScrollListView mShopNoScrollListView;
    private TextView mShopTitleName;
    private ImageButton mShopTitleBack;
    private ShopDetallData mShopDetallData;
    private LinearLayout mAddcarlogo;
    private LinearLayout mMAddcarlogo;
    private RelativeLayout mTitleback;
    private TextView mCardViewName;
    private TextView mCardViewFen;
    private TextView mCardViewaddress;
    private FloatEvaluator floatEvaluator;
    private ImageView mShopListphoto;
    private int mDistanceY;
    private TextView mCardViewtime;
    private TextView mShopdescribe;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shop_list_item);

        //初始化控件
        initUI();
        //初始化数据
        initData();
    }

    private void initData() {
        //得到门店条目传过来的id
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        //访问服务器获取数据
        //OkHttpClient okHttpClient = new OkHttpClient();

        //单例模式
        OkHttpClient okHttpClient = OkHttphelper.getOkHttpClient();
        //2.构造request
        Request.Builder builder = new Request.Builder();

        //3.将request封装为call
        Request build = builder.get()
                .url("http://user.zglcfn.com:8763/store/getServiceStoreById/" + id)
                .build();
        //4.执行call
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String shopDetallstring = response.body().string();
                L.e("门店详情数据："+shopDetallstring);

                //解析数据
                processData(shopDetallstring);


            }
        });


    }



    /**
     * json解析数据
     * @param shopDetallstring
     */
    private void processData(String shopDetallstring) {
        Gson gson = new Gson();
        mShopDetallData = gson.fromJson(shopDetallstring, ShopDetallData.class);

        //在主线程中更新UI
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                refreshView();
                mShopListGridview.setAdapter(new ShopListDatellAdapter((ArrayList<ShopDetallData.DataBean.StoreRecommendVoBean>) mShopDetallData.getData().getStoreRecommendVo()));
            }
        });
        mShopListGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplication(),CommodityPageActivity.class);
                intent.putExtra("id",mShopDetallData.getData().getStoreRecommendVo().get(position).getGoodsId());
                //System.out.println("id="+mHomwData.data.appHomeActivity.get(position).id);

                startActivity(intent);
            }
        });

    }

    class ShopListDatellAdapter extends MyAadapter<ShopDetallData.DataBean.StoreRecommendVoBean>{

        public ShopListDatellAdapter(ArrayList<ShopDetallData.DataBean.StoreRecommendVoBean> data) {
            super(data);
        }

        @Override
        public BaseHolder getHolder() {
            return new ShopDetallGridViewHolder();
        }

        @Override
        public boolean hasMore() {
            return false;
        }

        @Override
        public ArrayList<ShopDetallData.DataBean.StoreRecommendVoBean> onLoadingMore() {
            return null;
        }
    }
    /**
     * 给控件赋值
     */
    private void refreshView() {

        Picasso.with(UIUtils.getContext()).load(mShopDetallData.getData().getStoreInfo().getPhoto());
        mCardViewaddress.setText(mShopDetallData.getData().getStoreInfo().getAddress());
        mCardViewName.setText(mShopDetallData.getData().getStoreInfo().getName());
        mCardViewFen.setText(mShopDetallData.getData().getStoreInfo().getAppraisal()+".0分");
        mCardViewtime.setText(mShopDetallData.getData().getStoreInfo().getStoreTime()
                            +"——"+mShopDetallData.getData().getStoreInfo().getClosedShopTime());

        //动态添加imageview
       for(int i=0;i<mShopDetallData.getData().getBrandurls().size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
           LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
           param.leftMargin=UIUtils.dip2px(10);
            Picasso.with(UIUtils.getContext()).load(mShopDetallData.getData().getBrandurls().get(i)).into(imageView);
            mMAddcarlogo.addView(imageView,param);
        }


    }



    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void initUI() {
        mShopListScrollView = (MyScrollView) findViewById(R.id.sv_shop_list_item_detall);
        mShopListGridview = (MyGridview) findViewById(R.id.gv_shop_list_item_detall);
        mShopNoScrollListView = (NoScrollListView) findViewById(R.id.lv_shop_list_item_detall);

        mMAddcarlogo = (LinearLayout) findViewById(R.id.ll_carlogo);
        mTitleback = (RelativeLayout) findViewById(R.id.rl_title_back);
        //将标题背景透明
        mTitleback.getBackground().setAlpha(1);

        mCardViewName = (TextView) findViewById(R.id.tv_shop_list_item_detall_name);
        mCardViewFen = (TextView) findViewById(R.id.tv_shop_list_item_details_fen);
        mCardViewaddress = (TextView) findViewById(R.id.tv_shop_list_item_details_address);
        mCardViewtime = (TextView) findViewById(R.id.tv_shop_list_item_time);
        mShopListphoto = (ImageView) findViewById(R.id.iv_shop_list_item_photo);

        mShopdescribe = (TextView) findViewById(R.id.tv_shop_list_item_describe);

        mShopTitleName = (TextView) findViewById(R.id.tv_title_back);
        mShopTitleName.setText("门店详情");
        mShopTitleName.setVisibility(View.INVISIBLE);//题目出来不可见

        mShopTitleBack = (ImageButton) findViewById(R.id.ib_title_back);
        mShopTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mShopTitleBack.setVisibility(View.INVISIBLE);//返回按钮出来不可见
        mShopListScrollView.setScrollViewListener(this);

        floatEvaluator =new FloatEvaluator();
    }

    @Override
    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
        L.e("mDistanceY"+y);
        L.e("高度"+mShopListphoto.getHeight());
        int height = mShopListphoto.getHeight();

        //实行头标题动画效果
        if (y < height) {
            //滑动距离小于上方图片的1/2时，设置标题隐藏，透明度从0-255
            if (y < height / 2) {

                mShopTitleBack.setVisibility(View.GONE);
                mShopTitleName.setVisibility(View.GONE);
                float scale = (float) y / (height / 2);
                L.e("Y"+scale);
                float alpha = scale * 255;
                mTitleback.getBackground().setAlpha((int) alpha);
            } else {//滑动距离大于上方图片的1/2并小于上方图片时,标题显示，透明度从0-255
                mShopTitleBack.setVisibility(View.VISIBLE);
                mShopTitleName.setVisibility(View.VISIBLE);
                float scale = (float) (y - height / 2) / (height / 2);
                L.e("scale"+scale);
                float alpha = scale * 255;

                mTitleback.getBackground().setAlpha((int) alpha);
            }
        } else {
            //当快速往下滑时，llSearch最后设置的alpha不约等于255，测试的为132,所以要再设置
            mTitleback.getBackground().setAlpha(255);
        }
    }

}

