package com.wang.xiaoyu.View;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by hucanhui on 16/7/28.
 */
public class MyScrollView extends ScrollView{
    private ScrollViewListener scrollViewListener = null;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    public interface ScrollViewListener{
        void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy);
    }

}