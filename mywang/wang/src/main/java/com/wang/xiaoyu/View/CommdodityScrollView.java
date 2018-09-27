package com.wang.xiaoyu.View;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by 小 on 2018/9/22.
 */

public class CommdodityScrollView extends ScrollView {

    private float mDownX;
    private float mDownY;

    public CommdodityScrollView(Context context) {
        super(context);
    }

    public CommdodityScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommdodityScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CommdodityScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //拦截判断
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
        		case MotionEvent.ACTION_DOWN:
                    mDownX = ev.getX();
                    mDownY = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:

                    float offestX= Math.abs(ev.getX()- mDownX);
                    float offestY= Math.abs(ev.getY()- mDownY);

                    if(offestX<offestY){
                        return true; //拦截此事件
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    break;

        		default:
        			break;
        		}

        return super.onInterceptTouchEvent(ev);
    }
}
