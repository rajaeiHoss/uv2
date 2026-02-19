package com.streamax.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.streamax.utils.AppProxy;
import com.streamax.utils.StringUtils;

public class VsTextView extends TextView {
    public boolean mIsSelected;
    public Resources mResources;

    public VsTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public VsTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mResources = context.getResources();
    }

    public VsTextView SetClickable(boolean z) {
        setClickable(z);
        return this;
    }

    public VsTextView SetEnabled(boolean z) {
        setEnabled(z);
        return this;
    }

    public VsTextView SetBackgroundColor(int i) {
        setBackgroundColor(this.mResources.getColor(i));
        return this;
    }

    public VsTextView SetTextColor(int i) {
        setTextColor(i);
        return this;
    }

    public VsTextView SetTextSize(float f) {
        setTextSize(f);
        return this;
    }

    public VsTextView SetText(String str) {
        setText(str);
        return this;
    }

    public VsTextView SetText(int i) {
        setText(StringUtils.getString(Integer.valueOf(i)));
        return this;
    }

    public VsTextView IsClickable(boolean z, boolean z2, int i) {
        setClickable(z);
        setEnabled(z2);
        setBackgroundResource(i);
        return this;
    }

    public VsTextView IsClickable(boolean z, boolean z2) {
        setClickable(z);
        setEnabled(z2);
        return this;
    }

    public VsTextView SetOnClickListener(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
        return this;
    }

    public VsTextView SetSelected(boolean z) {
        setSelected(z);
        return this;
    }

    public boolean IsSelected() {
        return this.mIsSelected;
    }

    public VsTextView SetDrawableRight(int i) {
        setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, AppProxy.getDrawable(i), (Drawable) null);
        return this;
    }
}
