package com.hjq.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.hjq.widget.R;

public final class DrawableTextView extends AppCompatTextView {
    private int mDrawableHeight;
    private int mDrawableWidth;

    public DrawableTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public DrawableTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DrawableTextView);
        this.mDrawableWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DrawableTextView_drawableWidth, 0);
        this.mDrawableHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DrawableTextView_drawableHeight, 0);
        obtainStyledAttributes.recycle();
        refreshDrawablesSize();
    }

    public void setDrawableSize(int i, int i2) {
        this.mDrawableWidth = i;
        this.mDrawableHeight = i2;
        if (isAttachedToWindow()) {
            refreshDrawablesSize();
        }
    }

    public void setDrawableWidth(int i) {
        this.mDrawableWidth = i;
        if (isAttachedToWindow()) {
            refreshDrawablesSize();
        }
    }

    public void setDrawableHeight(int i) {
        this.mDrawableHeight = i;
        if (isAttachedToWindow()) {
            refreshDrawablesSize();
        }
    }

    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        if (isAttachedToWindow()) {
            refreshDrawablesSize();
        }
    }

    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        if (isAttachedToWindow()) {
            refreshDrawablesSize();
        }
    }

    private void refreshDrawablesSize() {
        if (this.mDrawableWidth != 0 && this.mDrawableHeight != 0) {
            Drawable[] compoundDrawables = getCompoundDrawables();
            if (compoundDrawables[0] == null && compoundDrawables[1] == null) {
                Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
                super.setCompoundDrawablesRelative(limitDrawableSize(compoundDrawablesRelative[0]), limitDrawableSize(compoundDrawablesRelative[1]), limitDrawableSize(compoundDrawablesRelative[2]), limitDrawableSize(compoundDrawablesRelative[3]));
                return;
            }
            super.setCompoundDrawables(limitDrawableSize(compoundDrawables[0]), limitDrawableSize(compoundDrawables[1]), limitDrawableSize(compoundDrawables[2]), limitDrawableSize(compoundDrawables[3]));
        }
    }

    private Drawable limitDrawableSize(Drawable drawable) {
        int i;
        if (drawable == null) {
            return null;
        }
        int i2 = this.mDrawableWidth;
        if (!(i2 == 0 || (i = this.mDrawableHeight) == 0)) {
            drawable.setBounds(0, 0, i2, i);
        }
        return drawable;
    }
}
