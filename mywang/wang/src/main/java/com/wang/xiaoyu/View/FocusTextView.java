package com.wang.xiaoyu.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class FocusTextView extends TextView {

	public FocusTextView(Context context) {
		super(context);
		
	}

	public FocusTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	public FocusTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
	}
	//��д��ȡ����ķ���,��ϵͳ����,���õ�ʱ��Ĭ�Ͼ��ܻ�ȡ����
	@Override
	public boolean isFocused() {
		
		return true;
	}

}
