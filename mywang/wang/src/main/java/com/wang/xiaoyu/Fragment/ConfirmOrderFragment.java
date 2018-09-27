package com.wang.xiaoyu.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.wang.xiaoyu.Activity.PayActivity;
import com.wang.xiaoyu.R;

/**
 * Created by 小 on 2018/9/23.
 */

public class ConfirmOrderFragment extends BaseFragment {

    private Button mConfirmVutton;

    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.fragment_title, null);

        mConfirmVutton = (Button) view.findViewById(R.id.bt_confirmorder_submit);
        mConfirmVutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mActivity, PayActivity.class);
                //intent.putExtra("name","ConfirmPaymentFragment");
                mActivity.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {

    }
}
