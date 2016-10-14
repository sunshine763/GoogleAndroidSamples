package com.xing.sample.activityscenetransitionbasic;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by lenovo on 2016/10/14.
 */
public class SquareFrameLayout extends FrameLayout {
    public SquareFrameLayout(Context context) {
        super(context);
    }

    public SquareFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthSize==0&&heightSize==0){
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
            int minSize = Math.min(getMeasuredWidth(), getMeasuredHeight());
            setMeasuredDimension(minSize,minSize);
            return;
        }
        int size;
        if(widthSize==0||heightSize==0){
            size=Math.max(widthSize,heightSize);
        }else {
            size=Math.min(widthSize, heightSize);
        }
        int newMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        super.onMeasure(newMeasureSpec, newMeasureSpec);
    }
}
