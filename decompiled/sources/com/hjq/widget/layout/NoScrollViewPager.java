package com.hjq.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public final class NoScrollViewPager extends ViewPager {
    public boolean executeKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setCurrentItem(int i) {
        boolean z = true;
        if (Math.abs(getCurrentItem() - i) != 1) {
            z = false;
        }
        super.setCurrentItem(i, z);
    }
}
