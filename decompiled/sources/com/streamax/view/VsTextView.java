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

    public VsTextView SetClickable(boolean clickable) {
        setClickable(clickable);
        return this;
    }

    public VsTextView SetEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    public VsTextView SetBackgroundColor(int colorResId) {
        setBackgroundColor(this.mResources.getColor(colorResId));
        return this;
    }

    public VsTextView SetTextColor(int color) {
        setTextColor(color);
        return this;
    }

    public VsTextView SetTextSize(float f) {
        setTextSize(f);
        return this;
    }

    public VsTextView SetText(String text) {
        setText(text);
        return this;
    }

    public VsTextView SetText(int stringResId) {
        setText(StringUtils.getString(Integer.valueOf(stringResId)));
        return this;
    }

    public VsTextView IsClickable(boolean clickable, boolean enabled, int backgroundResId) {
        setClickable(clickable);
        setEnabled(enabled);
        setBackgroundResource(backgroundResId);
        return this;
    }

    public VsTextView IsClickable(boolean clickable, boolean enabled) {
        setClickable(clickable);
        setEnabled(enabled);
        return this;
    }

    public VsTextView SetOnClickListener(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
        return this;
    }

    public VsTextView SetSelected(boolean selected) {
        this.mIsSelected = selected;
        setSelected(selected);
        return this;
    }

    public boolean IsSelected() {
        return this.mIsSelected;
    }

    public VsTextView SetDrawableRight(int drawableResId) {
        setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, AppProxy.getDrawable(drawableResId), (Drawable) null);
        return this;
    }
}
