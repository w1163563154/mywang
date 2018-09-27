package com.wang.xiaoyu.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;
import android.graphics.Shader;

@SuppressLint("DrawAllocation")
public class MyTextView extends TextView {
	
	 private LinearGradient mLinearGradient;
	    private Paint mPaint;
	    private int mViewWidth = 0;
	    private Rect mTextBound = new Rect();

	public MyTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		 mViewWidth = getMeasuredWidth();
	        mPaint = getPaint();
	        String mTipText = getText().toString();
	        mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);
	        mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
	                new int[] {  0xFF429321, 0xFFB4EC51 },
	                null, Shader.TileMode.REPEAT);
	        mPaint.setShader(mLinearGradient);
	        canvas.drawText(mTipText, getMeasuredWidth() / 2 - mTextBound.width() / 2, getMeasuredHeight() / 2 +   mTextBound.height()/2, mPaint);
	    
		
	}

}
