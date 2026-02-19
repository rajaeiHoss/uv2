package com.hjq.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.LinearLayout;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;

public class NestedLinearLayout extends LinearLayout implements NestedScrollingChild, NestedScrollingParent {
    private static final int INVALID_POINTER = -1;
    private int mActivePointerId;
    private final NestedScrollingChildHelper mChildHelper;
    private boolean mIsBeingDragged;
    private int mLastMotionY;
    private final float mMaximumVelocity;
    private final float mMinimumVelocity;
    private final NestedScrollingParentHelper mParentHelper;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;
    private final float mTouchSlop;
    private VelocityTracker mVelocityTracker;

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (i & 2) != 0;
    }

    public NestedLinearLayout(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public NestedLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mScrollConsumed = new int[2];
        this.mScrollOffset = new int[2];
        setWillNotDraw(false);
        this.mChildHelper = new NestedScrollingChildHelper(this);
        this.mParentHelper = new NestedScrollingParentHelper(this);
        setNestedScrollingEnabled(true);
        this.mTouchSlop = (float) ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mMaximumVelocity = (float) ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
        this.mMinimumVelocity = (float) ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f;
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        initVelocityTrackerIfNotExists();
        int actionMasked = obtain.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int findPointerIndex = obtain.findPointerIndex(this.mActivePointerId);
                    if (findPointerIndex != -1) {
                        int y = (int) obtain.getY(findPointerIndex);
                        int i = this.mLastMotionY - y;
                        if (dispatchNestedPreScroll(0, i, this.mScrollConsumed, this.mScrollOffset)) {
                            i -= this.mScrollConsumed[1];
                            obtain.offsetLocation(0.0f, (float) this.mScrollOffset[1]);
                        }
                        if (!this.mIsBeingDragged && ((float) Math.abs(this.mLastMotionY - y)) > this.mTouchSlop) {
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                            this.mIsBeingDragged = true;
                            if (i > 0) {
                                f = ((float) i) - this.mTouchSlop;
                            } else {
                                f = ((float) i) + this.mTouchSlop;
                            }
                            i = (int) f;
                        }
                        int i2 = i;
                        if (this.mIsBeingDragged) {
                            this.mVelocityTracker.addMovement(motionEvent);
                            int[] iArr = this.mScrollOffset;
                            this.mLastMotionY = y - iArr[1];
                            if (dispatchNestedScroll(0, 0, 0, i2, iArr)) {
                                int i3 = this.mLastMotionY;
                                int[] iArr2 = this.mScrollOffset;
                                this.mLastMotionY = i3 - iArr2[1];
                                obtain.offsetLocation(0.0f, (float) iArr2[1]);
                            }
                        }
                    }
                } else if (actionMasked != 3) {
                    if (actionMasked == 5) {
                        int actionIndex = obtain.getActionIndex();
                        this.mLastMotionY = (int) obtain.getY(actionIndex);
                        this.mActivePointerId = obtain.getPointerId(actionIndex);
                    } else if (actionMasked == 6) {
                        onSecondaryPointerUp(obtain);
                        this.mLastMotionY = (int) obtain.getY(obtain.findPointerIndex(this.mActivePointerId));
                    }
                }
            }
            if (this.mIsBeingDragged) {
                this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                int yVelocity = (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId);
                if (((float) Math.abs(yVelocity)) > this.mMinimumVelocity) {
                    flingWithNestedDispatch(-yVelocity);
                }
            }
            this.mActivePointerId = -1;
            endDrag();
        } else {
            this.mVelocityTracker.addMovement(motionEvent);
            this.mLastMotionY = (int) obtain.getY();
            this.mActivePointerId = obtain.getPointerId(0);
            startNestedScroll(2);
        }
        obtain.recycle();
        return true;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (motionEvent.getPointerId(action) == this.mActivePointerId) {
            int i = action == 0 ? 1 : 0;
            this.mLastMotionY = (int) motionEvent.getY(i);
            this.mActivePointerId = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        recycleVelocityTracker();
        stopNestedScroll();
    }

    private void flingWithNestedDispatch(int i) {
        float f = (float) i;
        if (!dispatchNestedPreFling(0.0f, f)) {
            dispatchNestedFling(0.0f, f, true);
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            recycleVelocityTracker();
        }
        super.requestDisallowInterceptTouchEvent(z);
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

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.mParentHelper.onNestedScrollAccepted(view, view2, i);
        startNestedScroll(2);
    }

    public void onStopNestedScroll(View view) {
        this.mParentHelper.onStopNestedScroll(view);
        stopNestedScroll();
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        dispatchNestedScroll(i, i2, i3, i4, (int[]) null);
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        dispatchNestedPreScroll(i, i2, iArr, (int[]) null);
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return dispatchNestedFling(f, f2, z);
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }
}
