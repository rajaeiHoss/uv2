package com.hjq.toast.style;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hjq.toast.config.IToastStyle;

public class ViewToastStyle implements IToastStyle<View> {
    private final int mLayoutId;
    private final IToastStyle<?> mStyle;

    public ViewToastStyle(int i, IToastStyle<?> iToastStyle) {
        this.mLayoutId = i;
        this.mStyle = iToastStyle;
    }

    public View createView(Context context) {
        return LayoutInflater.from(context).inflate(this.mLayoutId, (ViewGroup) null);
    }

    public int getGravity() {
        IToastStyle<?> iToastStyle = this.mStyle;
        if (iToastStyle == null) {
            return 17;
        }
        return iToastStyle.getGravity();
    }

    public int getXOffset() {
        IToastStyle<?> iToastStyle = this.mStyle;
        if (iToastStyle == null) {
            return 0;
        }
        return iToastStyle.getXOffset();
    }

    public int getYOffset() {
        IToastStyle<?> iToastStyle = this.mStyle;
        if (iToastStyle == null) {
            return 0;
        }
        return iToastStyle.getYOffset();
    }

    public float getHorizontalMargin() {
        IToastStyle<?> iToastStyle = this.mStyle;
        if (iToastStyle == null) {
            return 0.0f;
        }
        return iToastStyle.getHorizontalMargin();
    }

    public float getVerticalMargin() {
        IToastStyle<?> iToastStyle = this.mStyle;
        if (iToastStyle == null) {
            return 0.0f;
        }
        return iToastStyle.getVerticalMargin();
    }
}
