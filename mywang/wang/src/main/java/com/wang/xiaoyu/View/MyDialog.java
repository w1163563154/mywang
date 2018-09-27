package com.wang.xiaoyu.View;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wang.xiaoyu.Activity.TitleActivity;
import com.wang.xiaoyu.R;

import static com.wang.xiaoyu.R.id.bt_dialog_confirm;
import static com.wang.xiaoyu.R.id.iv_dialog_close;
import static com.wang.xiaoyu.R.id.rb_dialog_shop;
import static com.wang.xiaoyu.R.id.tv_dialog_add;
import static com.wang.xiaoyu.R.id.tv_dialog_subtract;

/**
 * Created by 小 on 2018/9/20.
 */

public class MyDialog extends Dialog implements View.OnClickListener{

    private TextView mShopamount;
    private RadioButton mRadiohome;
    private RadioButton mRadioshop;
    private TextView mShopName;
    private TextView mShopPrice;
    private ImageView mShopPhoto;
    private int count=1;
    private TextView mServicecharge;
    private RadioGroup mRadioGroup;

    public MyDialog(@NonNull Context context) {

        this(context,R.style.quick_option_dialog);
    }

    public MyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);

        View contentView = getLayoutInflater().inflate(
                R.layout.dialog_my, null);
        contentView.findViewById(tv_dialog_add).setOnClickListener(this); // 加商品
        contentView.findViewById(tv_dialog_subtract).setOnClickListener(this); //减商品
        contentView.findViewById(iv_dialog_close).setOnClickListener(this); //关闭
        contentView.findViewById(bt_dialog_confirm).setOnClickListener(this); //确认
        //商品数量
        mShopamount = (TextView) contentView.findViewById(R.id.tv_dialog_amount);
        mServicecharge = (TextView) contentView.findViewById(R.id.tv_dialog_servicecharge);//服务费

        mShopPhoto = (ImageView)contentView.findViewById(R.id.iv_dialog_photo);
        mShopName = (TextView) contentView.findViewById(R.id.tv_dialog_name);
        mShopPrice = (TextView) contentView.findViewById(R.id.tv_dialong_price);


        mRadioGroup = (RadioGroup) contentView.findViewById(R.id.rg_shop_list_item_detall);
        mRadiohome = (RadioButton) contentView.findViewById(R.id.rb_dialog_home);//门店安装
        mRadioshop = (RadioButton) contentView.findViewById(rb_dialog_shop);//送货到家

        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MyDialog.this.dismiss();
                return true;
            }
        });


        super.setContentView(contentView);


    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setGravity(Gravity.BOTTOM);//出现在底部
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);

         mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                 switch (i) {
                 		case R.id.rb_dialog_shop:
                            mServicecharge.setVisibility(View.VISIBLE);
                         break;
                        case R.id.rb_dialog_home:
                            mServicecharge.setVisibility(View.INVISIBLE);
                         break;

                 		default:
                 			break;
                 		}
             }
         });

    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
        		case tv_dialog_add: //增加商品数量
                    count++;

                    if(mRadioshop.isChecked()==true){
                        mServicecharge.setText("服务费：¥"+count*9+".00");
                    }

                    mShopamount.setText(""+count);

        			break;
                case tv_dialog_subtract: //减少商品数量
                    count--;
                    if(count<1){
                        count=1;
                    }

                   if(mRadioshop.isChecked()==true){
                       mServicecharge.setText("服务费：¥"+count*9+".00");
                   }


                    mShopamount.setText(""+count);
        			break;
                case iv_dialog_close:

                    dismiss();
                     break;
                case bt_dialog_confirm:

                    Intent intent=new Intent(getContext(), TitleActivity.class);
                    intent.putExtra("name","ConfirmOrderFragment");
                    getContext().startActivity(intent);
                break;

        		default:
        			break;
        		}
    }
}
