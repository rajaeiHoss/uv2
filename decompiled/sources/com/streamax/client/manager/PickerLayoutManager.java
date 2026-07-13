package com.streamax.client.manager;

import android.content.Context;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public final class PickerLayoutManager extends LinearLayoutManager {
    private final boolean mAlpha;
    private final LinearSnapHelper mLinearSnapHelper;
    private OnPickerListener mListener;
    private final int mMaxItem;
    private final int mOrientation;
    private RecyclerView mRecyclerView;
    private final float mScale;

    public interface OnPickerListener {
        void onPicked(RecyclerView recyclerView, int position);
    }

    private PickerLayoutManager(Context context, int orientation, boolean reverseLayout, int maxItem, float scale, boolean alphaEnabled) {
        super(context, orientation, reverseLayout);
        this.mLinearSnapHelper = new LinearSnapHelper();
        this.mMaxItem = maxItem;
        this.mOrientation = orientation;
        this.mAlpha = alphaEnabled;
        this.mScale = scale;
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mRecyclerView = recyclerView;
        recyclerView.setClipToPadding(false);
        this.mLinearSnapHelper.attachToRecyclerView(this.mRecyclerView);
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        this.mRecyclerView = null;
    }

    public boolean isAutoMeasureEnabled() {
        return this.mMaxItem == 0;
    }

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int measuredWidth = RecyclerView.LayoutManager.chooseSize(widthSpec, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this.mRecyclerView));
        int measuredHeight = RecyclerView.LayoutManager.chooseSize(heightSpec, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this.mRecyclerView));
        if (!(state.getItemCount() == 0 || this.mMaxItem == 0)) {
            View viewForPosition = recycler.getViewForPosition(0);
            measureChildWithMargins(viewForPosition, widthSpec, heightSpec);
            int orientation = this.mOrientation;
            if (orientation == 0) {
                int itemWidth = viewForPosition.getMeasuredWidth();
                int horizontalPadding = ((this.mMaxItem - 1) / 2) * itemWidth;
                this.mRecyclerView.setPadding(horizontalPadding, 0, horizontalPadding, 0);
                measuredWidth = itemWidth * this.mMaxItem;
            } else if (orientation == 1) {
                int itemHeight = viewForPosition.getMeasuredHeight();
                int verticalPadding = ((this.mMaxItem - 1) / 2) * itemHeight;
                this.mRecyclerView.setPadding(0, verticalPadding, 0, verticalPadding);
                measuredHeight = itemHeight * this.mMaxItem;
            }
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    public void onScrollStateChanged(int newState) {
        OnPickerListener onPickerListener;
        super.onScrollStateChanged(newState);
        if (newState == 0 && (onPickerListener = this.mListener) != null) {
            onPickerListener.onPicked(this.mRecyclerView, getPickedPosition());
        }
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (getItemCount() >= 0 && !state.isPreLayout()) {
            int orientation = this.mOrientation;
            if (orientation == 0) {
                scaleHorizontalChildView();
            } else if (orientation == 1) {
                scaleVerticalChildView();
            }
        }
    }

    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        scaleHorizontalChildView();
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        scaleVerticalChildView();
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    private void scaleHorizontalChildView() {
        float horizontalCenter = ((float) getWidth()) / 2.0f;
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
            View childView = getChildAt(childIndex);
            if (childView != null) {
                float childScale = ((((1.0f - this.mScale) * -1.0f) * Math.min(horizontalCenter, Math.abs(horizontalCenter - (((float) (getDecoratedLeft(childView) + getDecoratedRight(childView))) / 2.0f)))) / horizontalCenter) + 1.0f;
                childView.setScaleX(childScale);
                childView.setScaleY(childScale);
                if (this.mAlpha) {
                    childView.setAlpha(childScale);
                }
            }
        }
    }

    private void scaleVerticalChildView() {
        float verticalCenter = ((float) getHeight()) / 2.0f;
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
            View childView = getChildAt(childIndex);
            if (childView != null) {
                float childScale = ((((1.0f - this.mScale) * -1.0f) * Math.min(verticalCenter, Math.abs(verticalCenter - (((float) (getDecoratedTop(childView) + getDecoratedBottom(childView))) / 2.0f)))) / verticalCenter) + 1.0f;
                childView.setScaleX(childScale);
                childView.setScaleY(childScale);
                if (this.mAlpha) {
                    childView.setAlpha(childScale);
                }
            }
        }
    }

    public int getPickedPosition() {
        View findSnapView = this.mLinearSnapHelper.findSnapView(this);
        if (findSnapView == null) {
            return 0;
        }
        return getPosition(findSnapView);
    }

    public void setOnPickerListener(OnPickerListener onPickerListener) {
        this.mListener = onPickerListener;
    }

    public static final class Builder {
        private boolean mAlpha = true;
        private final Context mContext;
        private OnPickerListener mListener;
        private int mMaxItem = 3;
        private int mOrientation = 1;
        private boolean mReverseLayout;
        private float mScale = 0.6f;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setOrientation(int orientation) {
            this.mOrientation = orientation;
            return this;
        }

        public Builder setReverseLayout(boolean reverseLayout) {
            this.mReverseLayout = reverseLayout;
            return this;
        }

        public Builder setMaxItem(int maxItem) {
            this.mMaxItem = maxItem;
            return this;
        }

        public Builder setScale(float scale) {
            this.mScale = scale;
            return this;
        }

        public Builder setAlpha(boolean alphaEnabled) {
            this.mAlpha = alphaEnabled;
            return this;
        }

        public Builder setOnPickerListener(OnPickerListener onPickerListener) {
            this.mListener = onPickerListener;
            return this;
        }

        public PickerLayoutManager build() {
            PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(this.mContext, this.mOrientation, this.mReverseLayout, this.mMaxItem, this.mScale, this.mAlpha);
            pickerLayoutManager.setOnPickerListener(this.mListener);
            return pickerLayoutManager;
        }

        public void into(RecyclerView recyclerView) {
            recyclerView.setLayoutManager(build());
        }
    }
}
