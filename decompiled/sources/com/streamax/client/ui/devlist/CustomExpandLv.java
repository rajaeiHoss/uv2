package com.streamax.client.ui.devlist;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class CustomExpandLv extends ExpandableListView implements AbsListView.OnScrollListener, ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener {
    private static final int MAX_ALPHA = 255;
    private static final String TAG = "001";
    private HeaderAdapter mAdapter;
    private float mDownX;
    private float mDownY;
    private View mHeaderView;
    public int mHeaderViewHeight;
    public boolean mHeaderViewVisible;
    public int mHeaderViewWidth;
    private int mOldState;
    public int mT;

    public interface HeaderAdapter {
        public static final int HEADER_GONE = 0;
        public static final int HEADER_PUSHED_UP = 2;
        public static final int HEADER_VISIBLE = 1;

        void configHeader(View view, int i, int i2, int i3);

        int getGroupClickState(int i);

        int getHeaderState(int i, int i2);

        void setGroupClickState(int i, int i2);
    }

    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        return true;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public CustomExpandLv(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOldState = -1;
        registerListener();
    }

    public CustomExpandLv(Context context) {
        this(context, (AttributeSet) null);
    }

    public void setHeaderView(View view) {
        this.mHeaderView = view;
        view.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        if (this.mHeaderView != null) {
            setFadingEdgeLength(0);
        }
        requestLayout();
    }

    private void registerListener() {
        setOnScrollListener(this);
        setOnGroupClickListener(this);
        setOnChildClickListener(this);
    }

    private void headerViewClick() {
        int packedPositionGroup = ExpandableListView.getPackedPositionGroup(getExpandableListPosition(getFirstVisiblePosition()));
        if (this.mAdapter.getGroupClickState(packedPositionGroup) == 1) {
            collapseGroup(packedPositionGroup);
            this.mAdapter.setGroupClickState(packedPositionGroup, 0);
        } else {
            expandGroup(packedPositionGroup);
            this.mAdapter.setGroupClickState(packedPositionGroup, 1);
        }
        setSelectedGroup(packedPositionGroup);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mHeaderViewVisible) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.mDownX = motionEvent.getX();
                float y = motionEvent.getY();
                this.mDownY = y;
                if (this.mDownX <= ((float) this.mHeaderViewWidth) && y <= ((float) this.mHeaderViewHeight)) {
                    return true;
                }
            } else if (action == 1) {
                float x = motionEvent.getX();
                float y2 = motionEvent.getY();
                float abs = Math.abs(x - this.mDownX);
                float abs2 = Math.abs(y2 - this.mDownY);
                int i = this.mHeaderViewWidth;
                if (x <= ((float) (i - 63))) {
                    int i2 = this.mHeaderViewHeight;
                    if (y2 <= ((float) i2) && abs <= ((float) (i - 63)) && abs2 <= ((float) i2)) {
                        if (this.mHeaderView != null) {
                            headerViewClick();
                        }
                        return true;
                    }
                }
                if (x > ((float) (i - 63))) {
                    int i3 = this.mHeaderViewHeight;
                    if (y2 <= ((float) i3) && abs > ((float) (i - 63)) && abs2 <= ((float) i3)) {
                        return true;
                    }
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setAdapter(ExpandableListAdapter expandableListAdapter) {
        super.setAdapter(expandableListAdapter);
        this.mAdapter = (HeaderAdapter) expandableListAdapter;
    }

    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
        if (this.mAdapter.getGroupClickState(i) == 0) {
            this.mAdapter.setGroupClickState(i, 1);
            expandableListView.expandGroup(i);
        } else if (this.mAdapter.getGroupClickState(i) == 1) {
            this.mAdapter.setGroupClickState(i, 0);
            expandableListView.collapseGroup(i);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        View view = this.mHeaderView;
        if (view != null) {
            measureChild(view, i, i2);
            this.mHeaderViewWidth = this.mHeaderView.getMeasuredWidth();
            this.mHeaderViewHeight = this.mHeaderView.getMeasuredHeight();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        long expandableListPosition = getExpandableListPosition(getFirstVisiblePosition());
        int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
        int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
        int headerState = this.mAdapter.getHeaderState(packedPositionGroup, packedPositionChild);
        View view = this.mHeaderView;
        if (!(view == null || this.mAdapter == null || headerState == this.mOldState)) {
            this.mOldState = headerState;
            view.layout(0, 0, this.mHeaderViewWidth, this.mHeaderViewHeight);
            this.mT = (i2 * 2) + this.mHeaderViewHeight;
        }
        configHeaderView(packedPositionGroup, packedPositionChild);
    }

    public void configHeaderView(int i, int i2) {
        HeaderAdapter headerAdapter;
        int i3;
        if (this.mHeaderView != null && (headerAdapter = this.mAdapter) != null && ((ExpandableListAdapter) headerAdapter).getGroupCount() != 0) {
            int headerState = this.mAdapter.getHeaderState(i, i2);
            if (headerState != 0) {
                int i4 = 255;
                if (headerState == 1) {
                    this.mAdapter.configHeader(this.mHeaderView, i, i2, 255);
                    if (this.mHeaderView.getTop() != 0) {
                        this.mHeaderView.layout(0, 0, this.mHeaderViewWidth, this.mHeaderViewHeight);
                    }
                    this.mHeaderViewVisible = true;
                } else if (headerState == 2) {
                    int bottom = getChildAt(0).getBottom();
                    int height = this.mHeaderView.getHeight();
                    if (bottom < height) {
                        i3 = bottom - height;
                        i4 = ((height + i3) * 255) / height;
                    } else {
                        i3 = 0;
                    }
                    this.mAdapter.configHeader(this.mHeaderView, i, i2, i4);
                    if (this.mHeaderView.getTop() != i3) {
                        this.mHeaderView.layout(0, i3, this.mHeaderViewWidth, this.mHeaderViewHeight + i3);
                    }
                    this.mHeaderViewVisible = true;
                }
            } else {
                this.mHeaderViewVisible = false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mHeaderViewVisible) {
            drawChild(canvas, this.mHeaderView, getDrawingTime());
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        long expandableListPosition = getExpandableListPosition(i);
        configHeaderView(ExpandableListView.getPackedPositionGroup(expandableListPosition), ExpandableListView.getPackedPositionChild(expandableListPosition));
    }
}
