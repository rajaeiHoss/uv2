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
        void onPicked(RecyclerView recyclerView, int i);
    }

    private PickerLayoutManager(Context context, int i, boolean z, int i2, float f, boolean z2) {
        super(context, i, z);
        this.mLinearSnapHelper = new LinearSnapHelper();
        this.mMaxItem = i2;
        this.mOrientation = i;
        this.mAlpha = z2;
        this.mScale = f;
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

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        int chooseSize = RecyclerView.LayoutManager.chooseSize(i, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this.mRecyclerView));
        int chooseSize2 = RecyclerView.LayoutManager.chooseSize(i2, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this.mRecyclerView));
        if (!(state.getItemCount() == 0 || this.mMaxItem == 0)) {
            View viewForPosition = recycler.getViewForPosition(0);
            measureChildWithMargins(viewForPosition, i, i2);
            int i3 = this.mOrientation;
            if (i3 == 0) {
                int measuredWidth = viewForPosition.getMeasuredWidth();
                int i4 = ((this.mMaxItem - 1) / 2) * measuredWidth;
                this.mRecyclerView.setPadding(i4, 0, i4, 0);
                chooseSize = measuredWidth * this.mMaxItem;
            } else if (i3 == 1) {
                int measuredHeight = viewForPosition.getMeasuredHeight();
                int i5 = ((this.mMaxItem - 1) / 2) * measuredHeight;
                this.mRecyclerView.setPadding(0, i5, 0, i5);
                chooseSize2 = measuredHeight * this.mMaxItem;
            }
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    public void onScrollStateChanged(int i) {
        OnPickerListener onPickerListener;
        super.onScrollStateChanged(i);
        if (i == 0 && (onPickerListener = this.mListener) != null) {
            onPickerListener.onPicked(this.mRecyclerView, getPickedPosition());
        }
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (getItemCount() >= 0 && !state.isPreLayout()) {
            int i = this.mOrientation;
            if (i == 0) {
                scaleHorizontalChildView();
            } else if (i == 1) {
                scaleVerticalChildView();
            }
        }
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        scaleHorizontalChildView();
        return super.scrollHorizontallyBy(i, recycler, state);
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        scaleVerticalChildView();
        return super.scrollVerticallyBy(i, recycler, state);
    }

    private void scaleHorizontalChildView() {
        float width = ((float) getWidth()) / 2.0f;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                float min = ((((1.0f - this.mScale) * -1.0f) * Math.min(width, Math.abs(width - (((float) (getDecoratedLeft(childAt) + getDecoratedRight(childAt))) / 2.0f)))) / width) + 1.0f;
                childAt.setScaleX(min);
                childAt.setScaleY(min);
                if (this.mAlpha) {
                    childAt.setAlpha(min);
                }
            }
        }
    }

    private void scaleVerticalChildView() {
        float height = ((float) getHeight()) / 2.0f;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                float min = ((((1.0f - this.mScale) * -1.0f) * Math.min(height, Math.abs(height - (((float) (getDecoratedTop(childAt) + getDecoratedBottom(childAt))) / 2.0f)))) / height) + 1.0f;
                childAt.setScaleX(min);
                childAt.setScaleY(min);
                if (this.mAlpha) {
                    childAt.setAlpha(min);
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

        public Builder setOrientation(int i) {
            this.mOrientation = i;
            return this;
        }

        public Builder setReverseLayout(boolean z) {
            this.mReverseLayout = z;
            return this;
        }

        public Builder setMaxItem(int i) {
            this.mMaxItem = i;
            return this;
        }

        public Builder setScale(float f) {
            this.mScale = f;
            return this;
        }

        public Builder setAlpha(boolean z) {
            this.mAlpha = z;
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
