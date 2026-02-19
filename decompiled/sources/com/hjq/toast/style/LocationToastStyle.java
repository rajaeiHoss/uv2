package com.hjq.toast.style;

import android.content.Context;
import android.view.View;
import com.hjq.toast.config.IToastStyle;

public class LocationToastStyle implements IToastStyle<View> {
    private final int mGravity;
    private final float mHorizontalMargin;
    private final IToastStyle<?> mStyle;
    private final float mVerticalMargin;
    private final int mXOffset;
    private final int mYOffset;

    public LocationToastStyle(IToastStyle<?> iToastStyle, int i) {
        this(iToastStyle, i, 0, 0, 0.0f, 0.0f);
    }

    public LocationToastStyle(IToastStyle<?> iToastStyle, int i, int i2, int i3, float f, float f2) {
        this.mStyle = iToastStyle;
        this.mGravity = i;
        this.mXOffset = i2;
        this.mYOffset = i3;
        this.mHorizontalMargin = f;
        this.mVerticalMargin = f2;
    }

    public View createView(Context context) {
        return this.mStyle.createView(context);
    }

    public int getGravity() {
        return this.mGravity;
    }

    public int getXOffset() {
        return this.mXOffset;
    }

    public int getYOffset() {
        return this.mYOffset;
    }

    public float getHorizontalMargin() {
        return this.mHorizontalMargin;
    }

    public float getVerticalMargin() {
        return this.mVerticalMargin;
    }
}
