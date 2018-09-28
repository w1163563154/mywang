package com.wang.xiaoyu.View;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.wang.xiaoyu.Utils.SwipelayoutManager;

/**
 * Created by 小 on 2018/9/25.
 */

public class SwipeLayout extends FrameLayout {

    private View mContenView;
    private View mDeleteView;
    private int mDeleteHeight; //删除按钮高度
    private int mDeleteWidth; //删除按钮宽度
    private int mContenViewHeight;
    private int mContenViewWidth;
    private ViewDragHelper viewDragHelper;
    private float mDownX;
    private float mDownY;

    public SwipeLayout(@NonNull Context context) {
        super(context);
        init();

    }

    public SwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //定义有开和关状态的枚举
    enum SwipeState{
        Open,Close;
    }

    private SwipeState currenState=SwipeState.Close; //默认为关的状态


    private void init() {
        viewDragHelper=ViewDragHelper.create(this,Callback);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mContenView = getChildAt(0);
        mDeleteView = getChildAt(1);

    }
    //获取控件狂傲


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDeleteHeight = mDeleteView.getMeasuredHeight();
        mContenViewHeight = mContenView.getMeasuredHeight();
        mDeleteWidth = mDeleteView.getMeasuredWidth();
        mContenViewWidth = mContenView.getMeasuredWidth();


    }

    //重新摆放位置
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //super.onLayout(changed, left, top, right, bottom);
        mContenView.layout(0,0,mContenViewWidth,mContenViewHeight);
        mDeleteView.layout(mContenViewWidth,0,mContenViewWidth+mDeleteWidth,mDeleteHeight);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = viewDragHelper.shouldInterceptTouchEvent(ev);

        //如果当前有打开的，则需要直接拦截，交给onTouch处理
        if(!SwipelayoutManager.getInstance().isShouldSwipe(this)){
            //先关闭已经打开的layout
            SwipelayoutManager.getInstance().closeCurrentLayout();

            result = true;
        }

        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //如果当前有打开的，则下面的逻辑不能执行
        if(!SwipelayoutManager.getInstance().isShouldSwipe(this)){
            requestDisallowInterceptTouchEvent(true);
            //return true;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //1.获取x和y方向移动的距离
                float moveX = event.getX();
                float moveY = event.getY();
                float delatX = moveX - mDownX;//x方向移动的距离
                float delatY = moveY - mDownY;//y方向移动的距离
                if(Math.abs(delatX)>Math.abs(delatY)){
                    //表示移动是偏向于水平方向，那么应该SwipeLayout应该处理，请求listview不要拦截
                    requestDisallowInterceptTouchEvent(true);
                }
                //更新downX，downY
                mDownX = moveX;
                mDownY = moveY;
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback Callback=new ViewDragHelper.Callback() {

        /**
         * 用于判断是否捕获chiled的触摸事件
         * @param child 当前触摸的子View
         * @param pointerId
         * @return true 捕获并解析
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
         return child==mContenView || child==mDeleteView;
        }

        /**
         * 当View被开始捕获的回调
         * @param capturedChild 捕获的子view
         * @param activePointerId
         */
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        /**
         * 获取view在水平方向的拖拽范围
         * @param child
         * @return
         */
        @Override
        public int getViewHorizontalDragRange(View child) {
            return mDeleteWidth;
        }

        /**
         * 控制child在水平方向的移动
         * 表示ViewDragHelper认为你想让当前child的left改变的值,left=chile.getLeft()+dx dx:
         * 本次child水平方向移动的距离 return: 表示你真正想让child的left变成的值
         * @param child
         * @param left
         * @param dx
         * @return
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            if(child==mContenView){
                if(left>0)left = 0;
                if(left<-mDeleteWidth)left = -mDeleteWidth;
            }else if (child==mDeleteView) {
                if(left>mContenViewWidth)left = mContenViewWidth;
                if(left<(mContenViewWidth-mDeleteWidth))left = mContenViewWidth-mDeleteWidth;
            }

            return left;
        }

        /**
         * 一般做伴随动画
         * 控制child在垂直方向的移动 top
         * 表示ViewDragHelper认为你想让当前child的top改变的值,top=chile.getTop()+dy dy:
         * 本次child垂直方向移动的距离 return: 表示你真正想让child的top变成的值
         * @param changedView 位置改变的child
         * @param left child当前最新left的值
         * @param top
         * @param dx 本次水平移动的值
         * @param dy
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);

            if(changedView==mContenView){
                //手动移动mContenView
                mDeleteView.layout(mDeleteView.getLeft()+dx,mDeleteView.getTop()+dy,
                        mDeleteView.getRight()+dx,mDeleteView.getBottom()+dy);
            }else if(changedView==mDeleteView){
                //手动移动mDeleteView
                mContenView.layout(mContenView.getLeft()+dx,mContenView.getTop()+dy,
                        mContenView.getRight()+dx,mContenView.getBottom()+dy);
            }

            if(mContenView.getLeft()==0 && currenState!=SwipeState.Close){
                //此时为关闭状态
                currenState=SwipeState.Close;

                //说明当前的SwipeLayout已经关闭，需要让Manager清空一下
                SwipelayoutManager.getInstance().clearCurrentLayout();
            }else if(mContenView.getLeft() == -mDeleteWidth && currenState!=SwipeState.Open){
                //此时为开的状态
                currenState=SwipeState.Open;

                //当前的Swipelayout已经打开，需要让Manager记录一下下
                SwipelayoutManager.getInstance().setSwipelayout(SwipeLayout.this);
            }

        }

        /**
         * 手指抬起走此方法
         * @param releasedChild
         * @param xvel
         * @param yvel
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            if(mContenView.getLeft()<-mDeleteWidth/2){
                //打开删除
                open();

            }
            if(mContenView.getLeft()>-mDeleteWidth){
                close();
            }


        }
    };

    @Override
    public void computeScroll() {
        if(viewDragHelper.continueSettling(true)){

            ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
        }
    }

    //关闭方法
    public void close() {
        viewDragHelper.smoothSlideViewTo(mContenView,0,mContenView.getTop());
        ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
    }


    //打开方法
    public void open() {
        viewDragHelper.smoothSlideViewTo(mContenView,-mDeleteWidth,mContenView.getTop());
        ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
        }

}
