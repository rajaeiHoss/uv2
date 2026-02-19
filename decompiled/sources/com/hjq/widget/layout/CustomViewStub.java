package com.hjq.widget.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.hjq.widget.R;

public final class CustomViewStub extends FrameLayout {
    private View mInflateView;
    private final int mLayoutResource;
    private OnViewStubListener mListener;

    public interface OnViewStubListener {
        void onInflate(CustomViewStub customViewStub, View view);

        void onVisibility(CustomViewStub customViewStub, int i);
    }

    public CustomViewStub(Context context) {
        this(context, (AttributeSet) null);
    }

    public CustomViewStub(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomViewStub(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CustomViewStub(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomViewStub);
        this.mLayoutResource = obtainStyledAttributes.getResourceId(R.styleable.CustomViewStub_android_layout, 0);
        obtainStyledAttributes.recycle();
        setVisibility(8);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (this.mInflateView == null && i != 8) {
            View inflate = LayoutInflater.from(getContext()).inflate(this.mLayoutResource, this, false);
            this.mInflateView = inflate;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) inflate.getLayoutParams();
            layoutParams.width = getLayoutParams().width;
            layoutParams.height = getLayoutParams().height;
            if (layoutParams.gravity == -1) {
                layoutParams.gravity = 17;
            }
            this.mInflateView.setLayoutParams(layoutParams);
            addView(this.mInflateView);
            OnViewStubListener onViewStubListener = this.mListener;
            if (onViewStubListener != null) {
                onViewStubListener.onInflate(this, this.mInflateView);
            }
        }
        OnViewStubListener onViewStubListener2 = this.mListener;
        if (onViewStubListener2 != null) {
            onViewStubListener2.onVisibility(this, i);
        }
    }

    public void setCustomVisibility(int i) {
        super.setVisibility(i);
    }

    public View getInflateView() {
        return this.mInflateView;
    }

    public void setOnViewStubListener(OnViewStubListener onViewStubListener) {
        this.mListener = onViewStubListener;
    }
}
