package com.wang.xiaoyu.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Utils.L;
import com.wang.xiaoyu.Utils.UIUtils;
import com.wang.xiaoyu.domain.ShopData;

/**
 * Created by 小 on 2018/9/17.
 */

public class shopListHolder extends BaseHolder<ShopData.BenData> {

    private ImageView iv_shop_list_phone,iv_shop_list_brandurls_one,iv_shop_list_brandurls_two;
    private TextView tv_shop_list_name,tv_shop_list_appraisal,tv_shop_list_address,tv_shop_list_distance;


    @Override
    public View initView() {

        // 1.加载布局
        View convertView = UIUtils.inflate(R.layout.list_shop_item);

        // 2. 初始化控件
        iv_shop_list_phone= (ImageView) convertView.findViewById(R.id.iv_shop_list_item_phone);
        iv_shop_list_brandurls_one= (ImageView) convertView.findViewById(R.id.iv_shop_list_item_one);
        iv_shop_list_brandurls_two= (ImageView) convertView.findViewById(R.id.iv_shop_list_item_two);
        tv_shop_list_name= (TextView) convertView.findViewById(R.id.tv_shop_list_item_name);
        tv_shop_list_appraisal= (TextView) convertView.findViewById(R.id.tv_shop_list_item_appraisal);
        tv_shop_list_address= (TextView) convertView.findViewById(R.id.tv_shop_list_item_address);
        tv_shop_list_distance= (TextView) convertView.findViewById(R.id.tv_shop_list_item_distance);

        return convertView;
    }

    @Override
    public void refreshView(ShopData.BenData data) {
        String brandur = data.brandurl;
        String[] brandurs = brandur.split(",");
        Picasso.with(UIUtils.getContext()).load(data.photo).into(iv_shop_list_phone);
        L.e("phone"+brandur);
        Picasso.with(UIUtils.getContext()).load(brandurs[0]).into(iv_shop_list_brandurls_one);
       // Picasso.with(UIUtils.getContext()).load(brandurs[1]).into(iv_shop_list_brandurls_two);
        tv_shop_list_name.setText(data.name);
        tv_shop_list_appraisal.setText(data.appraisal+".0分");
        tv_shop_list_address.setText(data.address);

        float a = Integer.parseInt(data.distance)/23222200f;
        float b = (float)(Math.round(a*1000))/1000;

        tv_shop_list_distance.setText(b+"km");
    }

}
