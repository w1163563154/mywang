package com.wang.xiaoyu.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Utils.UIUtils;

/**
 * Created by 小 on 2018/9/17.
 */

public class popuListHolder extends BaseHolder<String> {

    public  TextView rb_list_popu;
    public  ImageView iv_true;
    @Override
    public View initView() {
        // 1.初始化控件
        View convertView = UIUtils.inflate(R.layout.list_popu_item);
        // 2.初始化布局
        iv_true= (ImageView) convertView.findViewById(R.id.iv_true);
        rb_list_popu= (TextView) convertView.findViewById(R.id.rb_list_popu);
        return convertView;
    }

    @Override
    public void refreshView(String data) {

        rb_list_popu.setText(data);
    }
}
