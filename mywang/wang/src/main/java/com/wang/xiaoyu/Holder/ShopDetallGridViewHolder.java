package com.wang.xiaoyu.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Utils.UIUtils;
import com.wang.xiaoyu.domain.ShopDetallData;

/**
 * Created by 小 on 2018/9/19.
 */

public class ShopDetallGridViewHolder extends BaseHolder<ShopDetallData.DataBean.StoreRecommendVoBean> {

    private ImageView mShopbitmap;
    private TextView mShopString,mShopPrice;

    @Override
    public View initView() {
        // 1.加载布局
        View view = UIUtils.inflate(R.layout.gv_list_item);

        // 2. 初始化控件
        mShopbitmap = (ImageView) view.findViewById(R.id.iv_home_Bitmap);
        mShopString = (TextView) view.findViewById(R.id.tv_home_Bitmap_String);
        mShopPrice = (TextView) view.findViewById(R.id.tv_home_Bitmap_price);

        return view;
    }

    @Override
    public void refreshView(ShopDetallData.DataBean.StoreRecommendVoBean data) {
        Picasso.with(UIUtils.getContext()).load(data.getFireUrl()).into(mShopbitmap);
        mShopString.setText(data.getFireName());

        float price = data.getFirePrice();
        float prices=(float)(Math.round(price))/100;
        mShopPrice.setText("¥"+prices);



    }
}
