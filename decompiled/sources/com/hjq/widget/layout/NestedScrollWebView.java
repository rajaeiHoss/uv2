package com.hjq.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;

public class NestedScrollWebView extends WebView implements NestedScrollingChild {
    private boolean mChange;
    private final NestedScrollingChildHelper mChildHelper = new NestedScrollingChildHelper(this);
    private int mLastMotionY;
    private int mNestedOffsetY;
    private final int[] mScrollConsumed = new int[2];
    private final int[] mScrollOffset = new int[2];

    public NestedScrollWebView(Context context) {
        super(context);
        setNestedScrollingEnabled(true);
    }

    public NestedScrollWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setNestedScrollingEnabled(true);
    }

    public NestedScrollWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setNestedScrollingEnabled(true);
    }

    public NestedScrollWebView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setNestedScrollingEnabled(true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        boolean z = false;
        if (actionMasked == 0) {
            this.mNestedOffsetY = 0;
        }
        int y = (int) motionEvent.getY();
        motionEvent.offsetLocation(0.0f, (float) this.mNestedOffsetY);
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int i = this.mLastMotionY - y;
                    if (dispatchNestedPreScroll(0, i, this.mScrollConsumed, this.mScrollOffset)) {
                        i -= this.mScrollConsumed[1];
                        obtain.offsetLocation(0.0f, (float) this.mScrollOffset[1]);
                        this.mNestedOffsetY += this.mScrollOffset[1];
                    }
                    this.mLastMotionY = y - this.mScrollOffset[1];
                    int scrollY = getScrollY();
                    int max = Math.max(0, scrollY + i) - scrollY;
                    if (dispatchNestedScroll(0, max, 0, i - max, this.mScrollOffset)) {
                        int i2 = this.mLastMotionY;
                        int[] iArr = this.mScrollOffset;
                        this.mLastMotionY = i2 - iArr[1];
                        obtain.offsetLocation(0.0f, (float) iArr[1]);
                        this.mNestedOffsetY += this.mScrollOffset[1];
                    }
                    if (this.mScrollConsumed[1] == 0 && this.mScrollOffset[1] == 0) {
                        if (this.mChange) {
                            this.mChange = false;
                            obtain.setAction(0);
                            super.onTouchEvent(obtain);
                        } else {
                            z = super.onTouchEvent(obtain);
                        }
                        obtain.recycle();
                        return z;
                    } else if (this.mChange) {
                        return false;
                    } else {
                        this.mChange = true;
                        super.onTouchEvent(MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0));
                        return false;
                    }
                } else if (!(actionMasked == 3 || actionMasked == 5 || actionMasked == 6)) {
                    return false;
                }
            }
            stopNestedScroll();
            return super.onTouchEvent(motionEvent);
        }
        this.mChange = false;
        this.mLastMotionY = y;
        startNestedScroll(2);
        return super.onTouchEvent(motionEvent);
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int i) {
        return this.mChildHelper.startNestedScroll(i);
    }

    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        return this.mChildHelper.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.mChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.mChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.mChildHelper.dispatchNestedPreFling(f, f2);
    }
}
